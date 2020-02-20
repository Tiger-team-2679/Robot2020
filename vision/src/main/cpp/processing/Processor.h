#pragma once

#include <iostream>
#include <stdexcept>
#include "thread"
#include "CameraFetcher.h"
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

    cv::VideoCapture * _cap;
    CameraFetcher * _cameraFetcher;
    Frame * _frame;
};