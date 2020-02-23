#include "ObjectDetection.h"

/*
 *   #convert BGR to HSV
    imgHSV= cv2.cvtColor(img,cv2.COLOR_BGR2HSV)
    # create the Mask
    mask=cv2.inRange(imgHSV,lowerBound,upperBound)
    #morphology
    maskOpen=cv2.morphologyEx(mask,cv2.MORPH_OPEN,kernelOpen)
    maskClose=cv2.morphologyEx(maskOpen,cv2.MORPH_CLOSE,kernelClose)

    maskFinal=mask
    conts,h=cv2.findContours(maskFinal.copy(),cv2.RETR_EXTERNAL,cv2.CHAIN_APPROX_NONE)
 */*
 std::shared_ptr<std::vector<std::vector<Point>>>& colorObjectDetection(cv::Mat & frame, unsigned int mode)
 {
     cv::Mat frameHsv;
     cv::Mat frameThreshold;
     cv::cvtColor(frame,frameHsv,CV_RGB2HSV);
     if(mode = Modes::reflectors)
        cv::inRange(frameHsv, cv::Scalar(LowerBoundReflectors::H, LowerBoundReflectors::S, LowerBoundReflectors::V), cv::Scalar(UpperBoundReflectors::H, UpperBoundReflectors::S, UpperBoundReflectors::V), frameThreshold);
     else if(mode = Modes::balls)
        cv::inRange(frameHsv, cv::Scalar(LowerBoundBalls::H, LowerBoundBalls::S, LowerBoundBalls::V), cv::Scalar(UpperBounBalls::H, UpperBounBalls::S, UpperBounBalls::V), frameThreshold);
     std::vector<std::vector<Point>> contours;
     cv::findContours(InputArray frameThreshold, contours, cv::RETR_EXTERNAL, cv::CV_CHAIN_APPROX_NONE, Point offset); //RETR_EXTERNAL = 0,
     return std::make_shared<std::vector<std::vector<Point>>>(contours);
 }
