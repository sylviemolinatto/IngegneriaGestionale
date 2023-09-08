#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#define MAX 60

int main()
{
    int n_parole=0,lungh_parole[5];
    int lunghezza, caratteri=0,i=0,j=0,lettera=0;
    float media;
    char str[MAX+1];
    printf("Inserire una stringa di caratteri: \n");
    gets(str);
    lunghezza=strlen(str);
    for(i=1; i<=lunghezza; i++)
    {
        if(((str[i]==' ')&&(isalpha(str[i-1])))||((str[i]=='\0')&&(isalpha(str[i-1]))))
            n_parole++;
    }


    if(n_parole>5)
    {
        printf("Errore: hai inserito più di 5 parole\n");
    }
    else
    {
        printf("Hai inserito %d parole\n",n_parole);

        for(i=0; i<lunghezza; i++)
        {
            if(isalnum(str[i])!=0)
            {
                caratteri++;
            }
        }
        media=(float)caratteri/n_parole;
        printf("La media della lunghezza delle parole e' %f\n",media);
        j=0;
        for(i=0;i<lunghezza;i++)
        {
            if(isalpha(str[i]))
                lettera++;
            else if(str[i]==' ')
            {
                lungh_parole[j]=lettera;
                lettera=0;
                j++;
            }

        }

        for(i=0;i<5;i++)
        {
            printf("La parola %d contiene %d lettere\n",i,lungh_parole[i]);
        }




    }



        return 0;
    }


