#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) {
    int i = 0;
    char buff[128];
    char *arg1 = argv[0];
    if (argc == 0) {
      puts("No arguments");
      return EXIT_FAILURE;
    }
    // puts(arg1);
    while (arg1[i] != '\0') {
        printf("%c",arg1[i]);
      buff[i] = arg1[i];
      i++;
    }
    buff[i] = '\0';
    printf("\nbuff = %s\n", buff);
    exit(EXIT_SUCCESS);
  }