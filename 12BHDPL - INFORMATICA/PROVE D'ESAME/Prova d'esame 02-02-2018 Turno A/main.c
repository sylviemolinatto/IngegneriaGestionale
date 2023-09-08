#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#define MAX 1000
typedef struct
{
    int n_matricola;
    long int tempo_arrivo;
    long int tempo_totale;
} accesso;
int presente(int matricola, accesso accessi[], int num_dati, int *posiz);
void converti(long int tempo, int *ora, int *minuti, int *secondi);
int main(int argc,char *argv[])
{
    FILE *fp;
    accesso accessi[MAX];
    int i=0;
    int ora,minuti,secondi;
    long int tempo;
    int max_t,min_t,pos_max,pos_min;
    int num_presenti,posizione;
    int n_accesso,n_matricola;
    int flag;
    char trattino;

    if(argc!=3)
    {
        printf("Errore parametri!\n");
        return -1;
    }

    fp=fopen(argv[1],"r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -1;
    }
    num_presenti=0;
    while(fscanf(fp,"%d%d%d%d%c%d%c%d",&n_accesso,&n_matricola,&flag,&ora,&trattino,&minuti,&trattino,&secondi)!=EOF)
    {
        tempo=secondi+minuti*60+ora*3600;
        if(flag==0)
        {
            if(presente(n_matricola,accessi,num_presenti,&posizione)==1)
            {
                accessi[posizione].tempo_arrivo=tempo;
            }
            else
            {
                accessi[num_presenti].n_matricola=n_matricola;
                accessi[num_presenti].tempo_arrivo=tempo;
                accessi[num_presenti].tempo_totale=0;
                num_presenti++;
            }
        }
        else
        {
            presente(n_matricola,accessi,num_presenti,&posizione);
            accessi[posizione].tempo_totale=(tempo-accessi[posizione].tempo_arrivo);
        }
    }
    if(strcmp(argv[2],"-v")==0)
    {
        for(i=0;i<num_presenti;i++)
        {
            converti(accessi[i].tempo_totale,&ora,&minuti,&secondi);
            printf("matricola %3d %.2d:%.2d:%.2d\n", accessi[i].n_matricola, ora, minuti, secondi);
        }
    }

    if(strcmp(argv[2],"-c")==0)
    {
        max_t = INT_MIN;
        min_t = INT_MAX;
        for(i = 0; i < num_presenti; i++)
            {
            if(accessi[i].tempo_totale > max_t)
                {
                max_t = accessi[i].tempo_totale;
                pos_max = i;
                }
            if(accessi[i].tempo_totale < min_t)
                {
                min_t = accessi[i].tempo_totale;
                pos_min = i;
                }
            }
        printf("Piu' tempo nell'edificio: matricola %3d\n", accessi[pos_max].n_matricola);
        printf("Meno tempo nell'edificio: matricola %3d\n", accessi[pos_min].n_matricola);
        }

return 0;
}

 int presente(int matricola,accesso accessi[], int num_dati, int *posiz)
{
    int trovato=0;
    int i;
    for(i=0;i<num_dati&&trovato==0;i++)
    {
        if(accessi[i].n_matricola==matricola)
        {
            trovato=1;
            *posiz=i;
        }
    }
    return trovato;
}
void converti(long int tempo, int *ora, int *minuti, int *secondi)
{

*ora = tempo / 3600;
tempo = tempo - (*ora * 3600);
*minuti = tempo / 60;
*secondi = tempo - (*minuti * 60);
return;
}
