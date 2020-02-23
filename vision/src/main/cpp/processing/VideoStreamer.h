#pragma once

#include <opencv2/videoio/videoio.hpp>


#include <iostream>
#include <string>
#include <mutex>
#include <condition_variable>
#include <atomic>
#include <opencv2/opencv.hpp>
#include <thread>
#include "./utils/Frame.h"

class VideoStreamer {
public :
    VideoStreamer(std::string target, int port);
    ~VideoStreamer();
    /**
     * updates a pointer to a frame, with a new one
     * when used, the frames are locked by a mutex,
     * don't deal with it
     * @param lastFrame
     */
    void refresh_frame(Frame ** lastFrame);
    void fast(cv::Mat& mat);
private:
    void send();

    Frame _sets[3];
    cv::VideoWriter _pipeline_out;
};


