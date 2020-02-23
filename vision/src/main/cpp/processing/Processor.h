#pragma once

#include <iostream>
#include <stdexcept>
#include "thread"
#include "CameraFetcher.h"
#include "VideoStreamer.h"
#include "./utils/Command.h"
#include "../networking/Server.h"

#define SERVER_PORT 2679

class Processor {
public: 
    explicit Processor();
    ~Processor();
    void process();
private:
    std::string getGstreamerPipe();
    void updateCommand();

    // processing methods
    int processCargo();

    Command _command;
    Server _server{SERVER_PORT};
    std::thread _serverThread;
    bool _serverEndSignal = false;

    VideoStreamer stream{"10.26.79.175", 5805};

    cv::VideoCapture * _cap;
    CameraFetcher * _cameraFetcher;
    Frame * _inputFrame;
    Frame * _outputFrame;
};