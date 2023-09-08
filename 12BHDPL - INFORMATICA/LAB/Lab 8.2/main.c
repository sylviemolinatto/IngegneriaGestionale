#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#define N 100
int main()
{
    char str[N+1];
    int i,parola=0;
    gets(str);
    for(i=0;i<strlen(str);i++)
    {
        if(islower(str[i])!=0)
            str[i]=toupper(str[i]);
        if(isalnum(str[i])==0)
            str[i]='_';
        if(isdigit(str[i])!=0)
            str[i]='*';
    }
    puts(str);
    for(i=0;i<N+1;i++)
    {
        if(((isalnum(str[i])!=0)||(str[i]=='*'))&&((str[i+1]=='_')||((str[i])=='\0')))
           parola++;
    }
    printf("Sono presenti %d parole",parola);

    return 0;
}
