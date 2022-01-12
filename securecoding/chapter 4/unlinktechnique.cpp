#include <stdlib.h>
#include <string.h>
#include<iostream>

int main(int argc, char *argv[]) {
    char *first, *second, *third;
    first = (char *)malloc(666);
    second = (char *)malloc(12);
    third = (char *)malloc(12);
    strcpy(first, argv[1]);
    std::cout<<first<<std::endl;
    free(first);
    // std::cout<<first<<std::endl;
    free(second);
    free(third);
    return(0);
}