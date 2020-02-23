#pragma once
#include "./utils/Frame.h"

/**
 * A camera fetcher is a class responsible for keeping a buffer of frames and update the frames in
 * the buffer. thus allowing for the capturing of frames while the processing code is running
 */
class CameraFetcher {
public:
    /**
     * Initializes a new camera fetcher with a given capture device
     * @param cap the capture device to fetch from
     */
    explicit CameraFetcher(cv::VideoCapture * cap);
    /**
     * starts the camera fetcher in a new thread
     * doesn't do nothing if the thread is already running
     */
    void start();
    /**
     * tells the thread to stop after waiting for the iteration to finish
     */
    void stop();
    /**
     * updates a frame in one of the sets from the capture device
     */
    void on_update();
    /**
     * updates a pointer to a frame, with a new one
     * when used, the frames are locked by a mutex,
     * don't deal with it
     * @param lastFrame
     */
    void refresh_frame(Frame ** lastFrame);
private:
    cv::VideoCapture * _cap;
    std::thread _thread;
    Frame _sets[3];
    bool _running;
};

