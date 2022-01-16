#include<stdio.h>
#include<limits.h>
int main(){


  unsigned int ui1 = 1452;
  unsigned int ui2 = 425;
  unsigned int product;

  if (ui1 > UINT_MAX/ui2) {
     printf("Give Valid Input");
  }
  else {
    product = ui1 * ui2;
    printf("Product : %d\n",product);
  }
}