#include<stdio.h>
#include<stdlib.h>

static int GLOBAL_INIT = 1; 
static int global_uninit; 

int main(int argc, char **argv) { 
   int local_init = 1; 
   int local_uninit; 
   static int local_static_init = 1; 
   static int local_static_uninit; 
   int *buff_ptr = (int *)malloc(32);
   printf("hello world!!!\n");
}