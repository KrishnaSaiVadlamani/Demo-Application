#include <stdio.h>
#include <stdlib.h>

static void create(void) __attribute__ ((constructor));
static void destroy(void) __attribute__ ((destructor));

int main(void) {
    printf("create: %p.\n", create);
    printf("destroy: %p.\n", destroy);
    exit(EXIT_SUCCESS);
  }

void create(void) {
    puts("create called.\n");
  }

void destroy(void) {
    puts("destroy called.");
 }