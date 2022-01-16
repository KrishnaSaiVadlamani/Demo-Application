#include<stdio.h>
#include<limits.h>

int main(){

  signed long sl1 = 45134;
  signed long sl2 = 5484;
  signed long quotient;

  /* Initialize sl1 and sl2 */

  if ( (sl2 == 0) || ( (sl1 == LONG_MIN) && (sl2 == -1) ) ) {
        printf("Enter Valid Input");
  }
  else {
    quotient = sl1 / sl2;
    printf("Quotient : %ld\n",quotient);
  }
}