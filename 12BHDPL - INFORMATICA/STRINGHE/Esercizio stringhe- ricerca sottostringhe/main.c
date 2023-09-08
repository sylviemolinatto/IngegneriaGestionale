#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 30
int main()
{
    char s1[N+1];
    char s2[N+1];
    printf("Stringa 1: \n");
    scanf("%s",s1);
    printf("Stringa 2: \n");
    scanf("%s",s2);
    if (strlen(s1) < strlen(s2) )
    printf("La seconda parola e'  piu' lunga della prima parola \n") ;
    else
    {
        if ( strstr(s1, s2) != NULL )
        printf("La seconda parola e' contenuta nella prima \n") ;
        else
        printf("La seconda parola non e' contenuta nella prima \n") ;
    }
    return 0;
}
