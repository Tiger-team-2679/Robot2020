#include <iostream>
#include "./processing/Processor.h"
#include "./processing/utils/QuickMaths.h"


int main (int argc, char *argv[]) {
    CameraProperties camera = { .height = 100, .frameWidth = 640, .frameHeight = 480, .camAngle = 0, .camAngleViewX = 78, .camAngleViewY = 43.3};
    std::cout << QuickMaths::getDistanceFromPixel(300, 300, camera);
    std::cout << QuickMaths::getAngleFromLine(300, 300,300, 300, camera);
    Processor Processor;
    Processor.process();
}