#define __STDC_WANT_LIB_EXT2__ 1
#include <stdio.h>
#include <stdlib.h>

void get_y_or_n(void) {
char *response = NULL;
size_t len;
puts("Continue? [y] n: ");
if ((getline(&response, &len, stdin) < 0) ||
(len && response[0] == 'n')) {
free(response);
exit(0);
}
free(response);
}