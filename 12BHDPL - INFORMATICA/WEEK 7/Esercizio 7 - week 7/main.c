#include <stdio.h>
#include <stdlib.h>
int f(int a,int b);
int main()
{
    int x,y;
    scanf("%d%d",&x,&y);
    f(x,y);
    return 0;
}
int f(int a,int b)
{
    int i,result,primi=0;
    if (a>b)
{
    for(i=2; i<=b&&primi==0; i++)
        {
            result=a%i;
            if(result==0)
            {
                primi=1;
                printf("i numeri non sono primi tra loro\n");
                return 1;
            }
            else
                printf("i numeri sono primi tra loro\n");
                return 0;

        }
}
    else
    for(i=2;i<=a&&primi==0;i++)
    {
        result=b%i;
        if(result==0)
        {
            primi=1;
            printf("i numeri non sono primi tra loro\n");
            return 1;
        }
        else
            printf("i numeri sono primi tra loro\n");
            return 0;
    }
return 0;
}
