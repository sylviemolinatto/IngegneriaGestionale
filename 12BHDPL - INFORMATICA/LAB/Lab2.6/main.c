#include <stdio.h>
#include <stdlib.h>

int main()
{
   int x;
   scanf("%d",&x);
    if (x>=0)
    {
        x=x;
        printf("%d e' il valore assoluto di x\n",x);
    }
    else
    {
        x=-x;
        printf("%d e' il valore assoluto di x\n",x);
    }
    return 0;
}
