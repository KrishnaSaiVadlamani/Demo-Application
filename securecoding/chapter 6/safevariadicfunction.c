#include<stdio.h>
#include<stdlib.h>

  #define va_start(ap,v) (ap=(va_list)_ADDRESSOF(v)+_INTSIZEOF(v)); \
   int va_count = va_arg(ap, int)
  
  #define va_arg(ap,t) \
    (*(t *)((ap+=_INTSIZEOF(t))-_INTSIZEOF(t))); \
    if (va_count-- == 0) abort();

  
  int main(void) {
    int av = -1;
    av = average(5, 6, 7, 8, -1); // works
    av = average(5, 6, 7, 8); // fails
    return 0;
  }