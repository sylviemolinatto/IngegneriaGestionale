#include <stdio.h>
#include <stdlib.h>

int main()
{
    int a,b,c,d,max,min,differenza;
    scanf("%d%d%d%d",&a,&b,&c,&d);
    if((a>=0&&a<1000)&&(b>=0&&b<1000)&&(c>=0&&c<1000))
    {
        if(a>b&&a>c)
        {
            max=a;
        }
        else if (b>a&&b>c)
        {
            max=b;
        }
        else
        {
            max=c;
        }
        if (a<b&&a<c)
        {
            min=a;
        }
        else if (b<a&&b<c)
        {
            min=b;
        }
        else
        {
            min=c;
        }
    differenza= max-min;
    printf("La massima differenza tra i valori acquisiti e' %d\n",differenza);

    }
    else
    {
        printf("Errore\n");
    }

    return 0;
}
