#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#define MAX 30
typedef struct
{
    char nome[81];
    char cognome[81];
    char sesso;
    char luogo_nascita[5];
    char data[11];
    char codice_fiscale[15];
} persona;

int main()
{
    int i,giorno,mese,anno;
    FILE *fp;
    char nome_file[MAX+1];
    persona utente;
    char new_codice[15];
    printf("Nome file: ");
    scanf("%s",nome_file);
    fp=fopen(nome_file,"r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file!\n");
        return -1;
    }

    while(fscanf(fp,"%s,%s,%c,%s,%s,%s,%s",utente.nome,utente.cognome,&utente.sesso,utente.luogo_nascita,utente.data,utente.codice_fiscale)!=EOF)
        {
        fscanf(fp,"%s,%s,%c,%s,%s,%s,%s",utente.nome,utente.cognome,&utente.sesso,utente.luogo_nascita,utente.data,utente.codice_fiscale);
        if((strlen(utente.nome)>=3))
        {
            if((utente.nome[0]!='_')&&(utente.nome[1]!='_')&&(utente.nome[2]!='_'))
            {
                for(i=0; i<3; i++)
                    {
                        new_codice[i]=toupper(utente.nome[i]);
                    }
            }
            for(i=0;i<3;i++)
            {
                if(utente.nome[i]=='_')
                {
                    new_codice[i]=toupper(utente.nome[i+1]);
                }
            }


        }
        else
        {
           for(i=0;i<strlen(utente.nome);i++)
            new_codice[i]=toupper(utente.nome[i]);
           for(i=strlen(utente.nome)-1;i<3;i++)
            new_codice[i]='X';
        }

    if((strlen(utente.cognome)>=3))
        {
            if((utente.cognome[0]!='_')&&(utente.cognome[1]!='_')&&(utente.cognome[2]!='_'))
            {
                for(i=2; i<5; i++)
                    {
                        new_codice[i]=toupper(utente.cognome[i]);
                    }
            }
            for(i=0;i<3;i++)
            {
                if(utente.cognome[i]=='_')
                {
                    new_codice[i+3]=toupper(utente.cognome[i+1]);
                }
            }


        }
        else
        {
           for(i=strlen(utente.cognome)-1;i<3;i++)
                new_codice[i+3]='X';

        }

        sscanf(utente.data,"%d/%d/%d",&giorno,&mese,&anno);
        for(i=6;i<10;i++)
        {
            if(utente.sesso=='M')
            {
                new_codice[i]=giorno+mese+anno;
            }
            else
            {
                new_codice[i]=giorno+mese+anno+1000;
            }
        }
        for(i=10;i<14;i++)
        {
            new_codice[i]=utente.luogo_nascita[i];
        }

        }

    if(strcmp(new_codice,utente.codice_fiscale)!=0)
    {
        printf("Codice errato %s  codice corretto %s",utente.codice_fiscale,new_codice);
    }
    fclose(fp);
    fp=fopen("log.txt","w");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file!\n");
        return -1;
    }



    return 0;
}
