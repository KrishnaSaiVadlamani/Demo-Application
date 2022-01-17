  #include <stdio.h>
  #include <string.h>
  #include<stdlib.h>

  int main(int argc, char * argv[]) {
    int x, y;
    static char format[256] = "%d * %d = ";

    x = atoi(argv[1]);
    y = atoi(argv[2]);

    if (strcmp(argv[3], "hex") == 0) {
      strcat(format, "0x%x\n");
    }
    else {
      strcat(format, "%d\n");
    }
    printf(format, x, y, x * y);

    exit(0);
  }