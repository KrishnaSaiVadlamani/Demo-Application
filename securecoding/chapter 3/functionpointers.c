#include<stdlib.h>
#include<stdio.h>
#include<string.h>

#define BUFFSIZE 25

void good_function(const char *str) {
    printf("Welcome!!!");
}
int main(int argc, char *argv[]) {
static char buff[BUFFSIZE];
static void (*funcPtr)(const char *str);
funcPtr = &good_function;
strncpy(buff, argv[1], strlen(argv[1]));
(void)(*funcPtr)(argv[2]);
}