#pragma once

#define CARGO_TARGET_CODE 'c'
#define SHIP_TARGET_CODE 's'

#define INFINITE_RETURN_METHOD 'i'
#define SINGLE_RETURN_METHOD 's'

typedef struct Command{
    char target;
    char method;
} Command;
