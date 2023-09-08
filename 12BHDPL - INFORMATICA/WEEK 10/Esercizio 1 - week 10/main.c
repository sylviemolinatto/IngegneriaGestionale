#include <stdio.h>
#include <stdlib.h>
#define N 6
int main()
{
    int i=0,punti[N],max,squadra;
    char classifica[N][100],squadre[N][100];
    while(i<N)
    {
        printf("Inserisci squadra %d e punteggio\n",i);
        gets(classifica[i]);
        i++;
    }
    FILE *fp;
    fp=fopen("campionato.txt","w");
    if(fp==NULL)
        printf("Errore nell'apertura\n");
    else
    {
        for(i=0; i<N; i++)
        {
            fputs(classifica[i],fp);
            fputc('\n',fp);
        }
    }
    fclose(fp);
    fp=fopen("campionato.txt","r");
    if(fp==NULL)
        printf("Errore nell'apertura\n");
    else
    {
        for(i=0;i<N;i++)
        {
            fscanf(fp,"%s %d",squadre[i],&punti[i]);
        }
    }
    max=punti[0];
    for(i=1;i<N;i++)
    {
        if(punti[i]>max)
            {
                max=punti[i];
                squadra=i;
            }
    }
    printf("La squadra con il punteggio maggiore e' %s e il suo punteggio e' %d",squadre[squadra],max);
    return 0;
}
