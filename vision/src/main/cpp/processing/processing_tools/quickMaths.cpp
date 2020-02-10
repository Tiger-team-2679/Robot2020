import math


"""
@param: pixel_x, pixel_y
@param: height: distance from ground
@param: cam_angle: angle of camera relatively to ground
@param: frame: image
@param: cam_angle_view_x, cam_angle_view_y: 
"""
def get_distance_from_pixel(pixel_x, pixel_y, height, cam_angle, frame, cam_angle_view_x, cam_angle_view_y):
    frame_width = frame[0]
    frame_height = frame[1]
    #get distance of robot by Y axis
    unseen_angle = 90 - (cam_angle_view_y / 2) - cam_angle
    angle_per_pixel_y = cam_angle_view_y / frame_height
    pixel_angle_from_buttom_frame = (frame_height - pixel_y) * angle_per_pixel_y
    angle_robot_y = pixel_angle_from_buttom_frame + unseen_angle
    ground_distance = height * math.tan(math.radians(angle_robot_y))
    distance_Y_axis = math.sqrt(math.pow(height, 2) + math.pow(ground_distance, 2))
     #get distance of robot by X axis + Y axis
    angle_per_pixel_x = cam_angle_view_x / frame_width
    angle_middle_frame_x =  angle_per_pixel_x * abs(pixel_x - (frame_width / 2))
    distance_from_cam = distance_Y_axis / math.cos(math.radians(angle_middle_frame_x))
    return math.sin(math.radians(angle_robot_y)) * distance_from_cam

def get_angle_from_line(pixel1_x, pixel1_y, pixel2_x, pixel2_y, height, cam_angle, frame, cam_angle_view_x, cam_angle_view_y):
    pixel_1_distance = get_distance_from_pixel(pixel1_x, pixel1_y, height, cam_angle, frame, cam_angle_view_x, cam_angle_view_y)
    pixel_2_distance = get_distance_from_pixel(pixel2_x, pixel2_y, height, cam_angle, frame, cam_angle_view_x, cam_angle_view_y)
    frame_width = frame[0]
    angle_per_pixel_x = cam_angle_view_x / frame_width
    angle_from_x_end_1 = (frame_width - pixel1_x) * angle_per_pixel_x
    angle_from_x_end_2 = (frame_width - pixel2_x) * angle_per_pixel_x
    angle_between_distances = angle_from_x_end_2 - angle_from_x_end_1
    third_rib = math.sqrt(math.pow(pixel_1_distance, 2) + math.pow(pixel_2_distance, 2) - 2 * pixel_2_distance * pixel_1_distance * math.cos(math.radians(angle_between_distances)))
    second_angle = math.sin(math.radians(angle_between_distances)) * pixel_1_distance / third_rib # out of names
    return 180 - (angle_from_x_end_2 + (180 - cam_angle_view_x / 2) - second_angle)

