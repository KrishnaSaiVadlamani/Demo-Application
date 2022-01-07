#include<stdio.h>  
#include <string.h>    
int main(){    
char ch[20]={'k','r','i','s','h','n','a', '\0'};    
   char ch2[20];    
   strcpy(ch2,ch);    
   printf("Value of second string is: %s\n",ch2);    
 return 0;    
}    