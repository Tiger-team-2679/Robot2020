#pragma once
#include <opencv2/opencv.hpp>
#include <memory>
#include <vector>
#include <utility>
#include <string>
#include <array>

//
#define NUM_OF_RESULTS 4
#define SEPERATOR ' '
enum Modes{balls = 0, reflectors};
//color object detection
enum class LowerBoundReflectors{H =26, S = 120, V = 90};
enum class UpperBoundReflectors{H = 0, S = 41, V = 142};
//ball detection
enum class LowerBoundBalls{H =20, S = 100, V = 100};
enum class UpperBoundBalls{H = 30, S = 255, V = 255};
//red detection
enum class LowerRed{H = 0, S = 111, V = 94};
enum class UpperRed{H = 193, S = 255, V = 255};

struct Result{
    int values[NUM_OF_RESULTS];
    std::string getResultString()
    {
        std::string ans = "";
        for (int i = 0; i < NUM_OF_RESULTS ; ++i) {
            ans += std::to_string(values[i]) + SEPERATOR;
        }
        return ans;
    }
};

struct Results
{
    std::vector<Result> values;
    std::string getResultsString()
    {
        std::string ans = "";
        for(auto & res : values)
        {
            ans += res.getResultString();
        }
        return ans;
    }
};

class ObjectDetection {
public:
    static Results colorObjectDetection(cv::Mat & frame, unsigned int mode = Modes::balls);
    static Results detectCorner(cv::Mat & frame);
private:
private:
    std::vector<cv::Vec4i> getLines(cv::Mat & frame) const;
    std::vector<std::array<double, 3>> detectPointsCloseToCorner(cv::Mat & frame, std::vector<cv::Vec4i>& lines) const;
    double distanceBetweenPoints(int x1, int y1, int x2, int y2) const;
    Result getEdgesPoints(std::vector<cv::Point<int>>& contour) const;
};

