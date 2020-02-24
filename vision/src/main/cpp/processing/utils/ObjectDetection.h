#pragma once
#include <opencv2/opencv.hpp>
#include <memory>
#include <vector>
#include <utility>
//
enum Modes{balls = 0, reflectors};
//color object detection
enum LowerBoundReflectors{H =26, S = 120, V = 90};
enum UpperBoundReflectors{H = 0, S = 41, V = 142)};
//ball detection
enum LowerBoundBalls{H =20, S = 100, V = 100};
enum UpperBounBdalls{H = 30, S = 255, V = 255)};
//red detection
enum LowerRed{H = 0, S = 111, V = 94};
enum UpperRed{H = 193, S = 255, V = 255};

class ObjectDetection {
public:
    static std::shared_ptr<std::vector<std::pair<cv::Point<int>, cv::Point<int>>>>& colorObjectDetection(cv::Mat & frame, unsigned int mode = Modes::balls);
    static detectCorner(cv::Mat & frame);
private:
private:
    std::vector<Vec4i> getLines(cv::Mat & frame) const;
    std::vector<double[3]> detectPointsCloseToCorner(cv::Mat & frame, std::vector<Vec4i>& lines) const;
    double distanceBetweenPoints(cv::Point<double> p1, cv::Point<double> p2);
    std::pair<std::Point<int>, std::Point<int>> getEdgesPoints(std::vector<cv::Point<int>>& contour) const;
};

