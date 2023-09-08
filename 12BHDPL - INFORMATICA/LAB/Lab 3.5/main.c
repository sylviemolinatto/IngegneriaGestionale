#include <stdio.h>
#include <stdlib.h>

int main()
{
    int a,b,c;
    printf("le misure dei lati sono:      \n");
    scanf("%d%d%d",&a,&b,&c);
    if(a<b+c&&b<a+c&&c<a+b)
    {
        printf("Il triangolo e' valido\n");
        if (a==b&&b==c)
        {
            printf("Il triangolo e' equilatero\n");
        }
        else if ((a==b&&b!=c)||(b==c&&c!=a)||(a==c&&c!=b))
        {
            printf("Il triangolo e' isoscele\n");
        }

        else
        {
            printf("Il triangolo e' scaleno\n");
        }

    }
    if ((a*a==b*b+c*c)||(b*b==a*a+c*c)||(c*c==a*a+b*b))
    {
        printf("Il triangolo e' rettangolo\n");
    }
    else if (a==b+c||b==a+c||c==a+b)
    {
        printf("Il triangolo e' degenere\n");
    }
    else
    {
        printf("Il triangolo non e' valido\n");
    }
    return 0;
}
