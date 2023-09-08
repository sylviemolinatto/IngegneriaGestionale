#include <stdio.h>
#include <stdlib.h>

int main()
{int N,i,x,max;
i=0;
max=0;
scanf("%d",&N);
while(i<N){
    scanf("%d",&x);
    if(x>max)
        max=x;
    i++;
}
printf("%d e' la temperatura massima\n",max);


    return 0;
}
