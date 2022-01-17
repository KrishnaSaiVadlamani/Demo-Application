#include<stdio.h>

int main(){


  int i, j, k = 0;

  printf(
    "%4$5u%3$n%5$5u%2$n%6$5u%1$n\n",
    &k, &j, &i, 5, 6, 7
  );

  printf("i = %d, j = %d, k = %d\n", i, j, k);

}