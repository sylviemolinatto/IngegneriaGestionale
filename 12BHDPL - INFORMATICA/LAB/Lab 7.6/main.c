#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include<string.h>
#define N 200
int main()
{
    char str[N+1];
    int i,inizio=1;
    gets(str);
    for(i=0;str[i]!='\0';i++)
    {
        if(isalpha(str[i]))
        {
            if(inizio!=0)
                str[i]=toupper(str[i]);

            inizio=0;
        }
        else if(isspace(str[i])&&(isalpha(str[i+1])))
                inizio=1;

    }
    puts(str);
    return 0;
}
