#include <stdio.h>
#include <stdlib.h>

int main()
{
    int N,S,i;
    i=1;
    S=0;
    scanf("%d",&N);
    while(i<=N){
        S=S+i;
        i++;
    }
    printf("la somma e':%d\n",S);
        return 0;
}
