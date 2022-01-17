#include<stdio.h>

int main(){


char c;
short s;
int i;
long l;
long long ll;

printf("hello %hhn.", &c);
printf("hello %hn.", &s);
printf("hello %n.", &i);
printf("hello %ln.", &l);
printf("hello %lln.\n", &ll);

}