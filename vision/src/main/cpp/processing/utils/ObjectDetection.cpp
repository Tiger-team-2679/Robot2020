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
std::pair<std::Point<int>, std::Point<int>> getEdgesPoints(std::vector<cv::Point<int>>& contour)
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
    cv::Point min(minX, minY);
    cv::Point max(maxX, maxY);
    return std::make_pair(min, max);
}

std::shared_ptr<std::vector<std::pair<cv::Point<int>, cv::Point<int>>>>& colorObjectDetection(cv::Mat & frame, unsigned int mode)
 {
    cv::Mat frameHsv;
    cv::Mat frameThreshold;
    cv::cvtColor(frame,frameHsv,cv::COLOR_BGR2HSV);
    if(mode = Modes::reflectors)
        cv::inRange(frameHsv, cv::Scalar(LowerBoundReflectors::H, LowerBoundReflectors::S, LowerBoundReflectors::V), cv::Scalar(UpperBoundReflectors::H, UpperBoundReflectors::S, UpperBoundReflectors::V), frameThreshold);
    else if(mode = Modes::balls)
        cv::inRange(frameHsv, cv::Scalar(LowerBoundBalls::H, LowerBoundBalls::S, LowerBoundBalls::V), cv::Scalar(UpperBounBalls::H, UpperBounBalls::S, UpperBounBalls::V), frameThreshold);
    std::vector<std::vector<Point>> contours;
    cv::findContours(InputArray frameThreshold, contours, cv::RETR_EXTERNAL, cv::CV_CHAIN_APPROX_NONE, Point offset); //RETR_EXTERNAL = 0,
    std::vector<std::pair<cv::Point<int>, cv::Point<int>>> newContoursInterface;
    for(auto& contour : contours)
    {
        newContoursInterface.insert(getEdgesPoints(contour));
    }
    return std::make_shared<std::vector<std::pair<cv::Point<int>, cv::Point<int>>>>(newContoursInterface);
 }
double distanceBetweenPoints(cv::Point<double> p1, cv::Point<double> p2)
{
    double dx = p1.x - p2.x
    double dy = p1.y - p2.y
    return sqrt(pow(dx, 2) + pow(dy, 2))
}


std::vector<Vec4i> getLines(cv::Mat & frame)
{
    cv::Mat frameHsv, frameThreshold, frameEdges;
    cvtColor(frame, frameHsv, cv::COLOR_BGR2HSV);
    cv::inRange(frameHsv, cv::Scalar(LowerRed::H, LowerRed::S, LowerRed::V), cv::Scalar(UpperRed::H, UpperRed::S, UpperRed::V), frameThreshold);
    cv::Canny(frameThreshold, frameEdges, 50, 200);
    vector<Vec4i> lines;
    HoughLinesP(frameEdges, lines, 1, cv::CV_PI / 180, 50, 70, 5 );
    return lines;
}

std::vector<double[3]> detectPointsCloseToCorner(cv::Mat & frame, std::vector<Vec4i>& lines) const
{
    std::vector<double[3]> points; //0 - abs(m1 * m2 + 1), 1 - x, 2 - y
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
            double slopeMultiplied = abs(m1 * m2 + 1)
            if(points.size() < 4)
            {
                points.push_back({slopeMultiplied, x, y});
            }
            else
            {
                for(int k = 0; k < points.size(); k++)
                {
                    double[3] values = points[k];
                    if(values[0] > d)
                    {
                        points[k] = {d, x, y};
                    }
                }
            }
        }
    }
    return points;
}

void detectCorner(cv::Mat & frame)
{
    std::vector<Vec4i>& lines = getLines(frame);
    std::vector<double[3]> points = detectPointsCloseToCorner(frame, lines);
    int xAvg = 0, yAvg = 0;
    for(auto & point : points)
    {
        xAvg += point[1];
        yAvg += point[2];
    }
    xAvg /= points.size();
    yAvg /= points.size();
    xs = 0
    ys = 0
    index = (0, 0)
    for i in range(len(linesP)):
    l = linesP[i][0]
    for j in range(0, len(l), 2):
    if dis(l[j], l[j + 1], x_avg, y_avg) < dis(xs, ys, x_avg, y_avg):
    xs = l[j]
    ys = l[j + 1]
    index = (i, j)
    break
    i, j = index
    l = linesP[i][0]
    if j == 0:
    cv2.circle(line_on, (int(l[2]), int(l[3])), 5, (0, 255, 255), -1, 8)
    else:
    cv2.circle(line_on, (int(l[0]), int(l[1])), 5, (0, 255, 255), -1, 8)
}
