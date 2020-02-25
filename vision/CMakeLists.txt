cmake_minimum_required(VERSION 3.5)
project(vision)

find_package(Threads)
find_package( OpenCV REQUIRED )
set(CMAKE_CXX_STANDARD 11)
set(WITH_CUDA ON)

include_directories( ${OpenCV_INCLUDE_DIRS} )

file(GLOB vision_SRC
        "src/main/cpp/*.hpp"
        "src/main/cpp/*.cpp"
        "src/main/cpp/*.h"
        "src/main/cpp/**/*.hpp"
        "src/main/cpp/**/*.cpp"
        "src/main/cpp/**/*.h"

        "src/main/cpp/processing/utils/QuickMaths.cpp"
        "src/main/cpp/processing/utils/QuickMaths.h"

        "src/main/cpp/processing/utils/ObjectDetection.cpp"
        "src/main/cpp/processing/utils/ObjectDetection.h"

        "src/main/cpp/processing/VideoStreamer.cpp"
        "src/main/cpp/processing/VideoStreamer.h"

        "lib/jetsonTX2GPIO/jetsonGPIO.c"
        "lib/jetsonTX2GPIO/jetsonGPIO.h"
        )

add_executable(vision ${vision_SRC} src/main/cpp/processing/VideoStreamer.cpp src/main/cpp/processing/VideoStreamer.h src/main/cpp/processing/utils/ObjectDetection.cpp src/main/cpp/processing/utils/ObjectDetection.h)

target_link_libraries(vision opencv_core opencv_highgui opencv_videoio ${CMAKE_THREAD_LIBS_INIT})