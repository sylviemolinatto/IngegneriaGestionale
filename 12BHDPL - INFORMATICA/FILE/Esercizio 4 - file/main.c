#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main()
{
    int i,j,k=0;
    char parola[10];
    char parole[10][40];
    int occorrenze,occ;
    int indici[10];
    FILE *fp;
    printf("Inserisci una parola: \n");
    gets(parola);
    printf("Inserisci un'ipotesi sul suo numero di occorrenze: \n");
    scanf("%d",&occ);
    fp=fopen("text.txt","r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura nel file\n");
    }

    for(i=0;i<10;i++)
    {
            fscanf(fp,"%s",parole[i]);
            printf("%s",parole[i]);
            printf("\n");
    }
    occorrenze=0;
    for(i=0;i<10;i++)
    {
        if(strcmp(parola,parole[i])==0)
            {
                occorrenze++;
                indici[k]=i;
                k++;
            }
    }
    printf("La parola %s e' presente %d volte nel file e nelle righe: ",parola,occorrenze);
    for(i=0;i<occorrenze;i++)
    {
        printf("%d",indici[i]);
        printf(" ");
        printf("\n");

    }
    if(occ>occorrenze)
    {
        printf("Hai inserito un numero di occorrenze maggiore\n");
    }
    if(occ==occorrenze)
    {
        printf("Hai azzeccato il numero di occorrenze\n");
    }
    if(occ<occorrenze)
    {
        printf("Hai inserito un numero di occorrenze minore\n");
    }
    fclose(fp);


    return 0;
}
