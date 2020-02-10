#pragma once

struct CameraProperties
{
    int cam_angle = 0; //angle of camera on robot
    unsigned int cam_angle_view_x = 78;
    unsigned int cam_angle_view_y = 43.3;
    unsigned int height; //height of camera on robot
    unsigned int frame_width;
    unsigned int frame_height;

};


class quick_maths
{
    

private:
    CameraProperties cameraProperties = {101, 640, 480};
};