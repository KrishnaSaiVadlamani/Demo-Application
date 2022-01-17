#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int main(){

  static unsigned int already_written, width_field;
  static unsigned int write_word;
  static char convert_spec[256];
  static char format_str[256];

  already_written = 16;

    // first word
  write_word = 0x9020;
  already_written %= 0x10000;

  width_field = (write_word-already_written) % 0x10000;
  if (width_field < 10) width_field += 0x10000;
  sprintf(convert_spec, "%%4$%du%%5$n", width_field);
  strcat(format_str, convert_spec);
  puts(format_str);

    // last word
  already_written += width_field;
  write_word = 0x0804;
  already_written %= 0x10000;

  width_field = (write_word-already_written) % 0x10000;
  if (width_field < 10) width_field += 0x10000;
  sprintf(convert_spec, "%%6$%du%%7$n", width_field);
  strcat(format_str, convert_spec);
  puts(format_str);

}