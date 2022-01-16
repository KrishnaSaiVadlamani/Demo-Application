#include<stdio.h>
#include<limits.h>

int main(){


  unsigned int ui1, ui2, udiff;
  ui1=1000;
  ui2=451;

  if (ui1 < ui2) {
   printf("ui1 is less than ui2");
  }
  else {
    udiff = ui1 - ui2;
    printf("Difference : %d\n",udiff);
  }
}