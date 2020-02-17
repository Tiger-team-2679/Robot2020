#pragma once

#include <stdexcept>
#include <iostream>
#include <thread>
#include "../processing/utils/Command.h"

#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <unistd.h>

#define SOCKET int

/**
 * an incoming message will look like this:
 *  "<VALIDATION_CODE><TARGET CODE><RETURN_METHOD>"
 *  each parameter should be one char
 */

/**
 * an outgoing message will look like this:
 *  ";<TARGET CODE>;<DISTANCE>;<ANGLE>;<ETC>;..."
 *  target code should be char, all other are numbers
 */

/**
 * the validation code in both is just a stupid char to tell if a mmessage has began
 */

class Server {
public:
    Server(unsigned short port);
    ~Server();
    void sendMessage(std::string message);
    Command recvMessage();
private:
    unsigned short _port;
    bool _running = false;
    SOCKET _serverSocket;
    SOCKET _clientSocket;
    std::thread _clientAcceptorThread;

    void start();
    void acceptClients();
    int closeSocket(SOCKET sock);
};