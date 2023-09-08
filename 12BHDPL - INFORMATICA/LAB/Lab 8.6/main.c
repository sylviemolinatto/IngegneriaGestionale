#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#define N 200
int main()
{
    int i,j;
    char str[N+1];
    printf("Inserire una sequenza di caratteri: \n");
    gets(str);
    for(i=0;i<strlen(str);i++)
    {
        if(str[i]=='c'&&str[i+1]=='h')
            {
                str[i]='k';
                for(j=i+1;j<strlen(str);j++)
                    {
                        str[j]=str[j+1];
                    }
            }
        if(isalpha(str[i])&&(str[i]==str[i+1]))
        {
            str[i]=str[i];
            for(j=i+1;j<strlen(str);j++)
            {
                str[j]=str[j+1];
            }
        }
    }
    printf("%s",str);

    return 0;
}
