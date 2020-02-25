#include "ObjectDetection.h"

//#include "ObjectDetection.h"
//
///*
// *   #convert BGR to HSV
//    imgHSV= cv2.cvtColor(img,cv2.COLOR_BGR2HSV)
//    # create the Mask
//    mask=cv2.inRange(imgHSV,lowerBound,upperBound)
//    #morphology
//    maskOpen=cv2.morphologyEx(mask,cv2.MORPH_OPEN,kernelOpen)
//    maskClose=cv2.morphologyEx(maskOpen,cv2.MORPH_CLOSE,kernelClose)
//
//    maskFinal=mask
//    conts,h=cv2.findContours(maskFinal.copy(),cv2.RETR_EXTERNAL,cv2.CHAIN_APPROX_NONE)
//
Result getEdgesPoints(std::vector<cv::Point>& contour)
{
    int maxX = 0, maxY = 0, minX = 1000, minY = 1000;
    for(auto & point : contour)
    {
        if(point.x > maxX)
            maxX = point.x;
        else if(point.x < minX)
            minX = point.x;
        if(point.y > maxY)
            maxY = point.y;
        else if(point.y < minY)
            minY = point.y;
    }
    Result res = {.values = {minX, minY, maxX, maxY}};
    return res;
}

Results colorObjectDetection(cv::Mat & frame, unsigned int mode)
 {
    cv::Mat frameHsv;
    cv::Mat frameThreshold;
    cv::cvtColor(frame,frameHsv,cv::COLOR_BGR2HSV);
    cv::Scalar ais(1,2,3);
    if(mode = Modes::reflectors)
        cv::inRange(frameHsv, cv::Scalar((double)LowerBoundReflectors::H, (double)LowerBoundReflectors::S, (double)LowerBoundReflectors::V), cv::Scalar((double)UpperBoundReflectors::H, (double)UpperBoundReflectors::S, (double)UpperBoundReflectors::V), frameThreshold);
    else if(mode = Modes::balls)
        cv::inRange(frameHsv, cv::Scalar((double)LowerBoundBalls::H, (double)LowerBoundBalls::S, (double)LowerBoundBalls::V), cv::Scalar((double)UpperBoundBalls::H, (double)UpperBoundBalls::S, (double)UpperBoundBalls::V), frameThreshold);
    std::vector<std::vector<cv::Point>> contours;
    cv::findContours(frameThreshold, contours, cv::RETR_EXTERNAL, cv::CHAIN_APPROX_NONE); //RETR_EXTERNAL = 0,
    std::vector<std::pair<cv::Point, cv::Point>> newContoursInterface;
    Results results;
    for(auto& contour : contours)
    {
        Result res = getEdgesPoints(contour);
        (results.values).push_back(res);
    }
    return results;
 }
double distanceBetweenPoints(int x1, int y1, int x2, int y2)
{
    double dx = x1 - x2;
    double dy = y1 - y2;
    return sqrt(pow(dx, 2) + pow(dy, 2));
}


std::vector<cv::Vec4i> getLines(cv::Mat & frame)
{
    cv::Mat frameHsv, frameThreshold, frameEdges;
    cvtColor(frame, frameHsv, cv::COLOR_BGR2HSV);
    cv::inRange(frameHsv, cv::Scalar((double)LowerRed::H, (double)LowerRed::S, (double)LowerRed::V), cv::Scalar((double)UpperRed::H, (double)UpperRed::S, (double)UpperRed::V), frameThreshold);
    cv::Canny(frameThreshold, frameEdges, 50, 200);
    std::vector<cv::Vec4i> lines;
    HoughLinesP(frameEdges, lines, 1, CV_PI / 180, 50, 70, 5 );
    return lines;
}

std::vector<std::array<double, 3>> detectPointsCloseToCorner(cv::Mat & frame, std::vector<cv::Vec4i>& lines)
{
    std::vector<std::array<double, 3>> points; //0 - abs(m1 * m2 + 1), 1 - x, 2 - y
    for(int i = 0; i < lines.size(); i++ )
    {
        if(lines[i][0] == lines[i][2]) //check if x values are the same
            continue;
        cv::Vec4i lineI = lines[i];
        for(int j = 0; j < lines.size(); j++)
        {
            if (j != i && lines[i][0] != lines[i][2])
                continue;
            cv::Vec4i lineJ = lines[j];
            double m1 = (lineI[1] - lineI[3]) / (lineI[0] - lineI[2]);
            double m2 = (lineJ[1] - lineJ[3]) / (lineJ[0] - lineJ[2]);
            if(m1 == m2)
                continue;
            double b1 = lineI[1] - m1 * lineI[0];
            double b2 = lineJ[1] - m2 * lineJ[0];
            double x = (b2 - b1) / (m1 - m2);
            double y = m1 * x + b1;
            double slopeMultiplied = abs(m1 * m2 + 1);
            if(points.size() < 4)
            {
                points.push_back({slopeMultiplied, x, y});
            }
            else
            {
                for(int k = 0; k < points.size(); k++)
                {
                    auto values = points[k];
                    if(values[0] > slopeMultiplied)
                    {
                        points.at(k) = {slopeMultiplied, x, y};
                    }
                }
            }
        }
    }
    return points;
}

Results detectCorner(cv::Mat & frame)
{
    std::vector<Vec4i>& lines = getLines(frame);
    std::vector<std::array<double, 3>> points = detectPointsCloseToCorner(frame, lines);
    int xAvg = 0, yAvg = 0;
    for(auto & point : points)
    {
        xAvg += point[1];
        yAvg += point[2];
    }
    xAvg /= points.size();
    yAvg /= points.size();
    int xs = 0, ys = 0;
    std::Vec4i chosenLine;
    int chosenPoint = 0;
    for (auto & currLine : lines) {
        for (int i = 0; i < 4; i += 2) {
            if (distanceBetweenPoints(currLine[j], currLine[j + 1], xAvg, yAvg) <
                distanceBetweenPoints(xs, ys, xAvg, yAvg)) {
                xs = currLine[j];
                ys = currLine[j + 1];
                chosenLine = currLine;
                chosenPoint = j;
            }
        }
    }
    Result twoPoints = {.values = {xAvg, yAvg, 0, 0};
    if(j)
    {
        twoPoints.values[2] = chosenLine[2];
        twoPoints.values[3] = chosenLine[3];
    }
    else
    {
        twoPoints.values[2] = chosenLine[0];
        twoPoints.values[3] = chosenLine[1];
    }
    Results res;
    (res.values).push_back(twoPoints);
    return res;
}
