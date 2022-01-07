#include<stdio.h>
#include<setjmp.h>
jmp_buf buf;
void func()
{
	printf("Welcome\n");

	longjmp(buf, 1);

	printf("Krishna\n");
}

int main()
{
	if (setjmp(buf))
		printf("Sahithi\n");
	else
	{
		printf("Mahesh\n");
		func();
	}
	return 0;
}
