#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool comparePassword(){

    char password[12];
    gets(password);

    return 0==strcmp(password,"goodpass");
}

int main(int argc, char *argv[]) {
    
    puts("Enter password : ");
    bool pwstatus=comparePassword();
    if(!pwstatus){
        puts("Access Denied");
    }else{
        puts("Access granted");
    }
    exit(-1);

}