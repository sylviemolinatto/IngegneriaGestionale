#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 20

int main()
{
    int treni_in_partenza=0,treni_in_arrivo=0;
    char citta[N+1];
    char nome_file[N+1];
    char partenze[N+1];
    char arrivi[N+1];
    FILE *fp;
    printf("Inserisci il nome del file: \n");
    scanf("%s",nome_file);
    fp=fopen(nome_file,"r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura!\n");
    }
    printf("Inserisci il nome di una citta': \n");
    scanf("%s",citta);
    while(!feof(fp))
    {
        if(fscanf(fp,"%s %*s %s %*s",partenze,arrivi)==EOF)
            {
                printf("Errore!\n");
                return -1;
            }
        if(strcmp(citta,partenze)==0)
            treni_in_partenza++;
        if(strcmp(citta,arrivi)==0)
            treni_in_arrivo++;

    }fclose(fp);

    if(treni_in_partenza==0)
        printf("Non ci sono treni in partenza da %s\n",citta);
    else
        printf("Partono %d treni da %s\n",treni_in_partenza,citta);
    if(treni_in_arrivo==0)
        printf("Non ci sono treni in arrivo a %s\n",citta);
    else
        printf("Arrivano %d treni a %s\n",treni_in_arrivo,citta);
    return 0;
}
