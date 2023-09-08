#include <stdio.h>
#include <stdlib.h>

int main()
{
    int N,i,max,num;
    scanf("%d",&N);
    i=0;
    max=0;
    while(i<N){
        scanf("%d",&num);
        if(num>max){
            max=num;
            i++;
        }
    }
    printf("max=%d\n",max);

    return 0;
}
