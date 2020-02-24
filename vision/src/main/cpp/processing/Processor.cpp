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

void sendCargoResults(std::vector<std::pair<cv::Point<int>, cv::Point<int>>>& cargos)
{
    std::string data = "1 ";
    int width, height;
    for(auto& cargo : cargos)
    {
        width = (cargo.second).x - (cargo.first).x;
        height = (cargo.second).y - (cargo.first).y;
        data += ' ';
        data = data + std::to_string((cargo.first).x) + " " + std::to_string((cargo.first).y) + " " + width + " " + height + " ";
    }
    _server.sendMessage(data);
}


std::string Processor::getGstreamerPipe() {
    return "v4l2src device=/dev/video0 ! video/x-raw, width=(int)640, height=(int)480, format=(string)BGR ! videoconvert ! appsink";
}

void Processor::updateCommand() {
    while(!this->_serverEndSignal){
        this->_command = this->_server.recvMessage();
    }
}

void Processor::process() {
    while(true){
        this->_cameraFetcher->refresh_frame(&this->_inputFrame);
        if(!(this->_inputFrame->mat.empty() || this->_inputFrame->lastFetcher == std::this_thread::get_id())) {
            if (_command.method == INFINITE_RETURN_METHOD) {
                if (_command.target == CARGO_TARGET_CODE) {
                    auto contours = this->processCargo();
                }
                this->sendCargoResults(contours.get())
            } else if (_command.method == SINGLE_RETURN_METHOD) {
                if (_command.target == CARGO_TARGET_CODE) {
                    while (!this->processCargo()) {};
                }
                this->_command = Command{0, 0};
                //this->_server.sendMessage("cool and good");
                // TODO send data
            }
            stream.fast(this->_inputFrame->mat);
        }
//        this->_outputFrame->mat = this->_inputFrame->mat.clone();
//        stream.refresh_frame(&this->_outputFrame);
    }
}

inline int Processor::processCargo() {

    return true;
}

