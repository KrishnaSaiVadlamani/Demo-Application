#include<stdio.h>
#include<limits.h>

  int my_atoi(const unsigned char *s) {
    _Bool neg;
    int val = 0;
    if (neg = *s == '-')
      ++s;
    while (isdigit(*s)) {
      if (val > INT_MAX/10)  // this check is correct
  err:  report_error("atoi overflow");  // assumed to not return
      else
        val *= 10;
      int i = *s++ - '0'; // C Standard requires *s - '0' to work
      if (i > INT_MAX - val)  // this check is correct
        goto err;
      val +=  i;
    }
    return neg ? -val : val;
 }

 int main(){
     my_atoi("123");
     return 0;
 }