#include "Server.h"

Server::Server(unsigned short port) {
    this->_port = port;
    this->_serverSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (this->_serverSocket < 0) {
        closeSocket(this->_serverSocket);
        throw std::runtime_error("Networking Error: server socket creation failed");
    }
    int n = 1;
    if(setsockopt(this->_serverSocket, SOL_SOCKET, SO_REUSEADDR, &n, sizeof(n)) == -1){
        closeSocket(this->_serverSocket);
        throw std::runtime_error("Networking Error: changing socket settings failed");
    }
    start();
}

Server::~Server() {
    this->_running = false;
    closeSocket(this->_serverSocket);
    closeSocket(this->_clientSocket);
    if(this->_clientAcceptorThread.joinable()){
        this->_clientAcceptorThread.detach();
    }
}

void Server::start() {
    this->_running = true;
    struct sockaddr_in serv_addr {};
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = INADDR_ANY;
    serv_addr.sin_port = htons(this->_port);
    if (bind(this->_serverSocket, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0) {
        throw std::runtime_error("Networking Error: server socket binding failed");
    }
    this->_clientAcceptorThread = std::thread(&Server::acceptClients, this);
}

void Server::acceptClients() {
    while(this->_running) {
        struct sockaddr_in cli_addr{};
        socklen_t clilen = sizeof(cli_addr);
        listen(this->_serverSocket, 1);
        SOCKET temp = accept(this->_serverSocket,
                                     (struct sockaddr *) &cli_addr, &clilen);
        if(this->_clientSocket >= 0){
            this->closeSocket(this->_clientSocket);
        }
        this->_clientSocket = temp;
        if (this->_clientSocket < 0) {
            throw std::runtime_error("Networking Error: client socket acceptance failed");
        }
        std::cout << "New client connected" << std::endl;
    }
}

int Server::closeSocket(SOCKET sock) {
    close(sock);
    int status = shutdown(sock, SHUT_RDWR);
    return status;
}

void Server::sendMessage(std::string message) {
    if(send(this->_clientSocket, message.c_str(), static_cast<int>(message.length()), 0) == -1){
        throw std::runtime_error("Networking Error: sending data failed");
    }
}

Command Server::recvMessage() {
    char buf[2];
    recv(this->_clientSocket, buf, sizeof(buf), 0);
    return Command {buf[0],buf[1]};
}

