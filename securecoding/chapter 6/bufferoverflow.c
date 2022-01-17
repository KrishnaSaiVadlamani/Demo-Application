#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>

int main(int argc, char* argv[]) {

    char outbuf[512];
    char buffer[512];
    sprintf(
            buffer,
            "ERR Wrong command: %.400s\n",
            argv[1]
        );
    printf(buffer);
    sprintf(outbuf, buffer);
    printf(outbuf);

}