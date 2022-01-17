#include<stdio.h>
#include <stdarg.h>

  int average(int first, ...) {
    int count = 0, sum = 0, i = first;
    va_list marker;

    va_start(marker, first);
    while (i != -1) {
      sum += i;
      count++;
      i = va_arg(marker, int);
    }
    va_end(marker);
    return(sum ? (sum / count) : 0);
  }

  int main(){
     printf("Average: %d\n",average(3, 5, 8, -1));
  }