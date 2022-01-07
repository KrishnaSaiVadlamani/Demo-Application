#include<stdio.h>
#include<string.h>

void foo(void * arg, size_t len) {
char buff[100];
long val = 2000;
long *ptr = 4520;
memcpy(buff, arg, len);
*ptr = val;

printf(*ptr);

return;
}