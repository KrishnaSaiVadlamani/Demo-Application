#include<stdio.h>

void clear(int array[]) {
    printf("size of array pointer : %ld ,size of each element : %ld\n",sizeof(array),sizeof(array[0]));
}

int main(){

    int a[10];
    printf("size of array : %ld ,size of each element : %ld\n",sizeof(a),sizeof(a[0]));
    clear(a);
    return 0;
    
}