#pragma once
#include <math.h>
#include <iostream>

#define PI 3.14159265

struct CameraProperties
{
    unsigned int height; //height of camera on robot
    unsigned int frameWidth;
    unsigned int frameHeight;
    double camAngle; //angle of camera on robot
    double camAngleViewX;
    double camAngleViewY;
};


class QuickMaths
{
public:
    static double getDistanceFromPixel(const unsigned int pixelX, const unsigned int pixelY, CameraProperties & cameraProperties);
    static double getAngleFromLine(const unsigned int pixelX1, const unsigned int pixelY1, const unsigned int pixelX2, const unsigned int pixelY2, CameraProperties &cameraProperties);
private:
//CameraProperties cameraProperties = {101, 640, 480};
};