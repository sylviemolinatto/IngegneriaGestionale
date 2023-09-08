#include <stdio.h>
#include <stdlib.h>
#define N 8
int main()
{
    int F0=0;
    int F1=1;
    int F2;
    int i=0;
    while(i<N)
    {
        F2=F0+F1;
        printf("%d ",F2);
        F0=F1;
        F1=F2;
        i++;
    }


    return 0;
}
