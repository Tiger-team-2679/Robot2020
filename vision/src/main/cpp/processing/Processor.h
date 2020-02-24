#pragma once

#include <iostream>
#include <stdexcept>
#include "thread"
#include "CameraFetcher.h"
#include "VideoStreamer.h"
#include "./utils/Command.h"
#include "../networking/Server.h"
#include "./utils/ObjectDetection.h"

enum dataType{0 = ball};

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
    std::shared_ptr<std::vector<std::pair<cv::Point<int>, cv::Point<int>>>> processCargo();
    void sendCargoResults(std::vector<std::pair<cv::Point<int>, cv::Point<int>>>& cargos);

    Command _command;
    Server _server{SERVER_PORT};
    std::thread _serverThread;
    bool _serverEndSignal = false;

    VideoStreamer
    {"10.26.79.175", 5805};

    cv::VideoCapture * _cap;
    CameraFetcher * _cameraFetcher;
    Frame * _inputFrame;
    Frame * _outputFrame;
};