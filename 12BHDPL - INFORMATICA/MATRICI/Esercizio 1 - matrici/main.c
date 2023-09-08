#include <stdio.h>
#include <stdlib.h>

int main()
{
    int i,j,somma,max,min,posizione1,posizione2;
    const int K,G;
    printf("Inserisci un valore K positivo e minore di 100: \n");
    scanf("%d",&K);
    printf("Inserisci un valore G positivo e minore di 10: \n");
    scanf("%d",&G);
    int vet_K[K];
    int vet_G[G];
    int matrice[K][G];
    for (i=0;i<K;i++)
    {
        printf("Il candidato %d ha ricevuto i voti: \n",i);
        for(j=0;j<G;j++)
        {
            scanf("%d",&matrice[i][j]);
        }
    }

    for(i=0;i<K;i++)
    {
        somma=0;
        for(j=0;j<G;j++)
            somma=somma+matrice[i][j];
        vet_K[i]=somma;
    }
    for(j=0;j<G;i++)
    {
        somma=0;
        for(i=0;i<K;i++)
            somma=somma+matrice[i][j];
        vet_G[j]=somma;
    }


    max=vet_K[0];
    posizione1=0;
    for(i=1;i<K;i++)
    {
        if(vet_K[i]>max)
        {
            max=vet_K[i];
            posizione1=i;
        }
    }

    min=vet_G[0];
    posizione2=0;
    for(j=1;j<G;j++)
    {
        if(vet_G[j]<min)
        {
            min=vet_G[j];
            posizione2=j;
        }
    }
    printf("Il candidato piu' intelligente e' il numero %d e ha ottenuto una somma di giudizi pari a %d\n",posizione1,max);
    printf("Il giudice piu' severo e' il numero %d e ha dato una somma di giudizi pari a %d\n",posizione2,min);




    return 0;
}
