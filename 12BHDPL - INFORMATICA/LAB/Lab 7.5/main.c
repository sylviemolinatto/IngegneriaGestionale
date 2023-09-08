#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

int main()
{
    char a,b,tmp;
    printf("Inserisci a: \n");
    scanf("%c",&a);
    getchar();
    printf("Inserisci b: \n");
    scanf("%c",&b);
    if(isalpha(a)&&isalpha(b))
    {
        if(a>b)
        {
            tmp=b;
            b=a;
            a=tmp;
        }
        printf("%c  %c\n",a,b);
    }
    else
    {
        if (isdigit(a)||isdigit(b))
        {
            printf("Almeno uno dei due caratteri e' un numero\n");
        }
    }

        return 0;
    }
