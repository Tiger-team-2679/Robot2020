# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.5

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /tmp/tmp.tTdhs4Uvpv

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /tmp/tmp.tTdhs4Uvpv/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/vision.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/vision.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/vision.dir/flags.make

CMakeFiles/vision.dir/src/main/cpp/main.cpp.o: CMakeFiles/vision.dir/flags.make
CMakeFiles/vision.dir/src/main/cpp/main.cpp.o: ../src/main/cpp/main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/tmp/tmp.tTdhs4Uvpv/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/vision.dir/src/main/cpp/main.cpp.o"
	/usr/bin/c++   $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/vision.dir/src/main/cpp/main.cpp.o -c /tmp/tmp.tTdhs4Uvpv/src/main/cpp/main.cpp

CMakeFiles/vision.dir/src/main/cpp/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/vision.dir/src/main/cpp/main.cpp.i"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /tmp/tmp.tTdhs4Uvpv/src/main/cpp/main.cpp > CMakeFiles/vision.dir/src/main/cpp/main.cpp.i

CMakeFiles/vision.dir/src/main/cpp/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/vision.dir/src/main/cpp/main.cpp.s"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /tmp/tmp.tTdhs4Uvpv/src/main/cpp/main.cpp -o CMakeFiles/vision.dir/src/main/cpp/main.cpp.s

CMakeFiles/vision.dir/src/main/cpp/main.cpp.o.requires:

.PHONY : CMakeFiles/vision.dir/src/main/cpp/main.cpp.o.requires

CMakeFiles/vision.dir/src/main/cpp/main.cpp.o.provides: CMakeFiles/vision.dir/src/main/cpp/main.cpp.o.requires
	$(MAKE) -f CMakeFiles/vision.dir/build.make CMakeFiles/vision.dir/src/main/cpp/main.cpp.o.provides.build
.PHONY : CMakeFiles/vision.dir/src/main/cpp/main.cpp.o.provides

CMakeFiles/vision.dir/src/main/cpp/main.cpp.o.provides.build: CMakeFiles/vision.dir/src/main/cpp/main.cpp.o


CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.o: CMakeFiles/vision.dir/flags.make
CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.o: ../src/main/cpp/networking/Server.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/tmp/tmp.tTdhs4Uvpv/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.o"
	/usr/bin/c++   $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.o -c /tmp/tmp.tTdhs4Uvpv/src/main/cpp/networking/Server.cpp

CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.i"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /tmp/tmp.tTdhs4Uvpv/src/main/cpp/networking/Server.cpp > CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.i

CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.s"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /tmp/tmp.tTdhs4Uvpv/src/main/cpp/networking/Server.cpp -o CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.s

CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.o.requires:

.PHONY : CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.o.requires

CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.o.provides: CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.o.requires
	$(MAKE) -f CMakeFiles/vision.dir/build.make CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.o.provides.build
.PHONY : CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.o.provides

CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.o.provides.build: CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.o


CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.o: CMakeFiles/vision.dir/flags.make
CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.o: ../src/main/cpp/processing/CameraFetcher.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/tmp/tmp.tTdhs4Uvpv/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.o"
	/usr/bin/c++   $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.o -c /tmp/tmp.tTdhs4Uvpv/src/main/cpp/processing/CameraFetcher.cpp

CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.i"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /tmp/tmp.tTdhs4Uvpv/src/main/cpp/processing/CameraFetcher.cpp > CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.i

CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.s"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /tmp/tmp.tTdhs4Uvpv/src/main/cpp/processing/CameraFetcher.cpp -o CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.s

CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.o.requires:

.PHONY : CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.o.requires

CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.o.provides: CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.o.requires
	$(MAKE) -f CMakeFiles/vision.dir/build.make CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.o.provides.build
.PHONY : CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.o.provides

CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.o.provides.build: CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.o


CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.o: CMakeFiles/vision.dir/flags.make
CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.o: ../src/main/cpp/processing/Processor.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/tmp/tmp.tTdhs4Uvpv/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building CXX object CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.o"
	/usr/bin/c++   $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.o -c /tmp/tmp.tTdhs4Uvpv/src/main/cpp/processing/Processor.cpp

CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.i"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /tmp/tmp.tTdhs4Uvpv/src/main/cpp/processing/Processor.cpp > CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.i

CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.s"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /tmp/tmp.tTdhs4Uvpv/src/main/cpp/processing/Processor.cpp -o CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.s

CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.o.requires:

.PHONY : CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.o.requires

CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.o.provides: CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.o.requires
	$(MAKE) -f CMakeFiles/vision.dir/build.make CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.o.provides.build
.PHONY : CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.o.provides

CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.o.provides.build: CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.o


CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.o: CMakeFiles/vision.dir/flags.make
CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.o: ../src/main/cpp/processing/VideoStreamer.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/tmp/tmp.tTdhs4Uvpv/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Building CXX object CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.o"
	/usr/bin/c++   $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.o -c /tmp/tmp.tTdhs4Uvpv/src/main/cpp/processing/VideoStreamer.cpp

CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.i"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /tmp/tmp.tTdhs4Uvpv/src/main/cpp/processing/VideoStreamer.cpp > CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.i

CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.s"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /tmp/tmp.tTdhs4Uvpv/src/main/cpp/processing/VideoStreamer.cpp -o CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.s

CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.o.requires:

.PHONY : CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.o.requires

CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.o.provides: CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.o.requires
	$(MAKE) -f CMakeFiles/vision.dir/build.make CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.o.provides.build
.PHONY : CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.o.provides

CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.o.provides.build: CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.o


CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.o: CMakeFiles/vision.dir/flags.make
CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.o: ../src/main/cpp/processing/utils/QuickMaths.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/tmp/tmp.tTdhs4Uvpv/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_6) "Building CXX object CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.o"
	/usr/bin/c++   $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.o -c /tmp/tmp.tTdhs4Uvpv/src/main/cpp/processing/utils/QuickMaths.cpp

CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.i"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /tmp/tmp.tTdhs4Uvpv/src/main/cpp/processing/utils/QuickMaths.cpp > CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.i

CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.s"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /tmp/tmp.tTdhs4Uvpv/src/main/cpp/processing/utils/QuickMaths.cpp -o CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.s

CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.o.requires:

.PHONY : CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.o.requires

CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.o.provides: CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.o.requires
	$(MAKE) -f CMakeFiles/vision.dir/build.make CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.o.provides.build
.PHONY : CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.o.provides

CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.o.provides.build: CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.o


# Object files for target vision
vision_OBJECTS = \
"CMakeFiles/vision.dir/src/main/cpp/main.cpp.o" \
"CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.o" \
"CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.o" \
"CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.o" \
"CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.o" \
"CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.o"

# External object files for target vision
vision_EXTERNAL_OBJECTS =

vision: CMakeFiles/vision.dir/src/main/cpp/main.cpp.o
vision: CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.o
vision: CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.o
vision: CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.o
vision: CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.o
vision: CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.o
vision: CMakeFiles/vision.dir/build.make
vision: /usr/local/lib/libopencv_highgui.so.4.0.1
vision: /usr/local/lib/libopencv_videoio.so.4.0.1
vision: /usr/local/lib/libopencv_imgcodecs.so.4.0.1
vision: /usr/local/lib/libopencv_imgproc.so.4.0.1
vision: /usr/local/lib/libopencv_core.so.4.0.1
vision: /usr/local/lib/libopencv_cudev.so.4.0.1
vision: CMakeFiles/vision.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/tmp/tmp.tTdhs4Uvpv/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_7) "Linking CXX executable vision"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/vision.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/vision.dir/build: vision

.PHONY : CMakeFiles/vision.dir/build

CMakeFiles/vision.dir/requires: CMakeFiles/vision.dir/src/main/cpp/main.cpp.o.requires
CMakeFiles/vision.dir/requires: CMakeFiles/vision.dir/src/main/cpp/networking/Server.cpp.o.requires
CMakeFiles/vision.dir/requires: CMakeFiles/vision.dir/src/main/cpp/processing/CameraFetcher.cpp.o.requires
CMakeFiles/vision.dir/requires: CMakeFiles/vision.dir/src/main/cpp/processing/Processor.cpp.o.requires
CMakeFiles/vision.dir/requires: CMakeFiles/vision.dir/src/main/cpp/processing/VideoStreamer.cpp.o.requires
CMakeFiles/vision.dir/requires: CMakeFiles/vision.dir/src/main/cpp/processing/utils/QuickMaths.cpp.o.requires

.PHONY : CMakeFiles/vision.dir/requires

CMakeFiles/vision.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/vision.dir/cmake_clean.cmake
.PHONY : CMakeFiles/vision.dir/clean

CMakeFiles/vision.dir/depend:
	cd /tmp/tmp.tTdhs4Uvpv/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /tmp/tmp.tTdhs4Uvpv /tmp/tmp.tTdhs4Uvpv /tmp/tmp.tTdhs4Uvpv/cmake-build-debug /tmp/tmp.tTdhs4Uvpv/cmake-build-debug /tmp/tmp.tTdhs4Uvpv/cmake-build-debug/CMakeFiles/vision.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/vision.dir/depend

