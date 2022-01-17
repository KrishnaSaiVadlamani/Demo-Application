  #include <stdio.h>
  #include<stdlib.h>
  int main(void) {

    char filename[256];
    FILE *f;
    char format[256];

    fscanf(stdin, "%s", filename);
    f = fopen(filename, "r"); /* read only */

    if (f == NULL) {
      sprintf(format, "Error opening file %s\n", filename);
      fprintf(stderr, format);
      exit(-1);
    }
    fclose(f);
  }