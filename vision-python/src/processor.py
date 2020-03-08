import pickle
import struct
import numpy as np
import cv2


def prepare_frame_for_sending(frame):
    data = pickle.dumps(frame)
    # Send message length first
    message_size = struct.pack("L", len(data))  ### CHANGED
    return message_size + data


def detect_reflectors(img, low=np.array([26, 120, 90]), high=np.array([0,41,142]), draw=True):
    blur = cv2.blur(img, (3, 3))
    img_hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)
    mask = cv2.inRange(img_hsv, low, high)
    contours, h = cv2.findContours(mask.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_NONE)
    biggest_rect_index = 0
    for i in range(1, len(contours)):
        x1, y1, w1, h1 =  cv2.boundingRect(contours[i])
        x2, y2, w2, h2 = cv2.boundingRect(contours[biggest_rect_index])
        if w1 * h1 > w2 * h2:
            biggest_rect_index = i
    if len(contours) != 0:
        x, y, w, h = cv2.boundingRect(contours[biggest_rect_index])
        if draw:
            cv2.drawContours(img, contours, -1, (255, 255, 255), 3)
            cv2.rectangle(img, (x, y), (x + w, y + h), (255, 0, 0), 2)
        return [x, y, w, h]
    return [-1]