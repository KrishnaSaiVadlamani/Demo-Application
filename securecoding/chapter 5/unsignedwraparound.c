#include<stdio.h>
#include<limits.h>

int main()
{
    unsigned int ui;
    ui = UINT_MAX; 
    ui++;
    printf("ui = %u\n", ui); 
    ui = 0;
    ui--;
    printf("ui = %u\n", ui); 
}
