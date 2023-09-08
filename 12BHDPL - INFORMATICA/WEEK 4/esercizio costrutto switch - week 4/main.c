#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n;
    char c;
    printf("inserire un carattere: ");
    scanf("%c",&c);
    printf("inserire un numero: ");
    scanf("%d",&n);
    switch(c)
    {
    case 'm':
        n=n*3;
        printf("%d",n);
        break;
    case 'e':
        n=n*n;
        break;
    case 'd':
        n=n/4;
        break;
    default:
        printf("il carattere non serve a un cazzo");
        break;
    }
    return 0;
}
