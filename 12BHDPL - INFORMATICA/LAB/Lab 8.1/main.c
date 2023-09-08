#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#define N 100
int main()
{
    int i,k=0,j=0,l;
    char str1[N+1];
    char str2[N+1];
    gets(str1);
    printf("La lunghezza della stringa e' %d\n",strlen(str1));
    for(i=0; i<strlen(str1); i++)
    {
        if((isalpha(str1[i]))!=0)
            k++;
        if((isdigit(str1[i]))!=0)
            j++;
    }
    printf("I caratteri alfabetici sono %d\n",k);
    printf("I caratteri numerici sono %d",j);


    return 0;
}
