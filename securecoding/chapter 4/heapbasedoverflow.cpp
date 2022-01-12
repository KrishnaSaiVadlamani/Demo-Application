#include<iostream>
#include<cstring>

typedef void *HANDLE;
typedef void *HLOCAL;
typedef void *HMODULE;


  int mem(char *buf) {
    HLOCAL h1 = 0, h2 = 0;
    HANDLE hp;

    hp = HeapCreate(0, 0x1000, 0x10000);
    if (!hp) return -1;
    h1 = HeapAlloc(hp, HEAP_ZERO_MEMORY, 260);
    strcpy((char *)h1, buf);
    h2 = HeapAlloc(hp, HEAP_ZERO_MEMORY, 260);
    puts("we never get here");
    return 0;
}

  int main(void) {
   HMODULE l;
   l = LoadLibrary("wmvcore.dll");
   buildMalArg();
    mem(buffer);
   return 0;
}