#include <stdio.h>
#include <stdlib.h>
int expo(int b,int e);
int main()
{
    int x,y;
    scanf("%d%d",&x,&y);
    printf("%d",expo(x,y));
    return 0;
}
int expo(int b, int e)
{
    int i,esp=b;
    if (e==0)
    {
        esp=1;
    }
    else
    {
        for(i=1; i<e; i++)
        {
            esp=esp*b;
        }
    }


    return esp;
}
