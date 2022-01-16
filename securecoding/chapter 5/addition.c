#include<stdio.h>
#include<limits.h>

int main(){


  unsigned int ui1, ui2, usum;
  ui1=1000;
  ui2=451;

  if (UINT_MAX - ui1 < ui2) {
   printf("Value of UNIT_MAX - ui1 is less than ui2");
  }
  else {
    usum = ui1 + ui2;
    printf("Sum : %d\n",usum);
  }
}