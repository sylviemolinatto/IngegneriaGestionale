#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#define N 30

int main()
{
    int i,j,finito=0,contenuto;
    char s1[N+1];
    char s2[N+1];
    printf("Stringa 1: \n");
    scanf("%s",s1);
    printf("Stringa 2: \n");
    scanf("%s",s2);
    if (strlen(s1)<strlen(s2))
        printf ("La seconda parola e' piu' lunga della prima\n");
    else
    {
        finito = 0;
        for(i = 0; ((i+strlen(s2))<strlen(s1)) && (finito == 0); i++)
        {
            contenuto = 1;
            for(j = 0; (j < strlen(s2))&&(contenuto == 1); j++)
            {
                if(s1[i+j] != s2[j])
                    contenuto = 0;
            }
            if(contenuto == 1)
                finito = 1;
        }
        if(contenuto == 1)
            printf("La seconda parola e' contenuta nella prima\n");
        else
            printf("La seconda parola non e' contenuta nella prima \n");
    }

    return 0;
}
