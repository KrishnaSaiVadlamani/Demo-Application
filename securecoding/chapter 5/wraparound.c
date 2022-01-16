#include<stdio.h>
#include<string.h>
#include<stdlib.h>

  void getComment(size_t len, char *src) {
     size_t size;
     size = len - 2;
     char *comment = (char *)malloc(size + 1);
     memcpy(comment, src, size);
     printf("%s\n",comment);
     return;
   }

   int main(int argc, char *argv[]) {
     getComment(1, "Comment ");
     return 0;
}