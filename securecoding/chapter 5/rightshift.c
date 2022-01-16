#include <stdio.h>
#include <stdlib.h>
#include <limits.h>


int main(int argc, char* argv[]) {

  int rc = 0;
  unsigned int stringify = 0x80000000;
  char buf[sizeof("256")];
  rc = sprintf(buf, "%u", stringify >> 24);
  printf("%u\n",stringify);
  printf("%d\n",stringify>>24);
  printf("%s\n",buf);
  printf("%d\n",rc);
  if (rc == -1 || rc >= sizeof(buf)) {
    printf("Something is wrong");
  }
  
}