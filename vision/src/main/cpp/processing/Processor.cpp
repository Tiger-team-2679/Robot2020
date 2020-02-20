#include "Processor.h"

Processor::Processor() {
    this->_cap = new cv::VideoCapture(getGstreamerPipe());
    if(!_cap->isOpened()) {
        throw std::runtime_error("Gstreamer Error: Can't create gstreamer reader");
        return;
    }
    this->_cameraFetcher = new CameraFetcher(this->_cap);
    this->_cameraFetcher->start();
    this->_serverThread = std::thread(&Processor::updateCommand, this);
}

Processor::~Processor() {
    this->_serverEndSignal = true;
    if(this->_serverThread.joinable()){
        this->_serverThread.join();
    }
    this->_cameraFetcher->stop();
    this->_cap->release();
    delete this->_cameraFetcher;
}

std::string Processor::getGstreamerPipe() {
    return "v4l2src device=/dev/video1 ! video/x-raw, width=(int)640, height=(int)480, format=(string)BGR ! videoconvert ! appsink";
}

void Processor::updateCommand() {
    while(!this->_serverEndSignal){
        this->_command = this->_server.recvMessage();
    }
}

void Processor::process() {
    while(true){
        if(_command.method == INFINITE_RETURN_METHOD){
            if(_command.target == CARGO_TARGET_CODE){
                this->processCargo();
            }
            // TODO send data
        }
        else if(_command.method == SINGLE_RETURN_METHOD){
            if(_command.target == CARGO_TARGET_CODE){
                while(!this->processCargo()){};
            }
            this->_command = Command{0,0};
            this->_server.sendMessage("cool and good");
            // TODO send data
        }
    }
}

inline int Processor::processCargo() {
    this->_cameraFetcher->refresh_frame(&this->_frame);
    if(this->_frame->mat.empty() || this->_frame->lastFetcher == std::this_thread::get_id()){
        return 0;
    }
    // TODO implement processing code here
    return true;
}

