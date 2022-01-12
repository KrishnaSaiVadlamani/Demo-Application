#include <stdio.h>
#include <string.h>

int main()
{
	char str[50] = "Welcome to Zemoso!!!";
	printf("\nBefore memset(): %s\n", str);

	memset(str + 8, '.', 3*sizeof(char));

	printf("After memset(): %s\n", str);
	return 0;
}
