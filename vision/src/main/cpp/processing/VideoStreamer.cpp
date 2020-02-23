
#include "VideoStreamer.h"

VideoStreamer::VideoStreamer(std::string target, int port) {
    std::string pipe_args = "appsrc ! video/x-raw, format=(string)BGR, " \
    " width=(int)640," \
    " height=(int)480," \
    " framerate=(fraction)30/1" \
    " ! videoconvert" \
    //" ! queue ! omxh264enc control-rate=2 bitrate=2000"
    " ! x264enc bitrate=2000 speed-preset=ultrafast tune=zerolatency sliced-threads=true byte-stream=true threads=1 key-int-max=1000 intra-refresh=true" \
    " ! video/x-h264, stream-format=(string)byte-stream, width=(int)160, height=(int)120"
    " ! h264parse ! rtph264pay pt=96  mtu=1400 ! udpsink" \
    " host=" + target + \
    " port=" + std::to_string(port) + " ";
    this->_pipeline_out.open(pipe_args, 0, 30, cv::Size(640, 480), true);
    std::cout << std::endl << this->_pipeline_out.isOpened() << std::endl;

    std::thread sendLoopThread(&VideoStreamer::send, this);
    sendLoopThread.detach();
}

VideoStreamer::~VideoStreamer() {
    this->_pipeline_out.release();
}

inline void VideoStreamer::refresh_frame(Frame ** lastFrame){
    for (auto &_set : _sets) {
        if(*lastFrame == &_set) {
            (*lastFrame)->lastFetcher = std::this_thread::get_id();
            _set.mutex.unlock();
            break;
        }
    }
    for (auto &_set : _sets) {
        if(*lastFrame != &_set) {
            if(_set.mutex.try_lock()){
                *lastFrame = &_set;
                break;
            }
        }
    }
}

void VideoStreamer::send() {
    Frame * frame;
    while(true){
        refresh_frame(&frame);
        //this->_pipeline_out.write(frame->mat);
    }
}

void VideoStreamer::fast(cv::Mat &mat) {
    this->_pipeline_out.write(mat);
}
