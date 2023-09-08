#include <stdio.h>
#include <stdlib.h>

int main()
{
    int x;
    int i;
    int r;
    i=2;
    r=0;
    scanf ("%d",&x);
    while (i<x)
    {
        if (x%i==0)
            r++;
            i++;
    }
    if (r!=0)
        printf ("%d non e' un numero primo\n",x);
    else
        printf ("%d e' un numero primo\n",x);
    return 0;
}
