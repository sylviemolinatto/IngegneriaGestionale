#include <stdio.h>
#include <stdlib.h>

int main()
{
    const int N;
    printf("Inserisci N: \n");
    scanf("%d",&N);
    int v[N],i,j;
    if(N>0&&N<200)
    {
        printf("il vettore v[N] ha %d elementi\n",N);
    }
    else
        {
           printf("Errore\n");
        }
    for(i=0;i<N;i++)
    {
        printf("Inserisci il numero %d:\n",i+1);
        scanf("%d",&v[i]);
    }
    printf("\n");

    printf("La sequenza inserita e' la seguente\n");
    for(i=0;i<N;i++)
    {
        printf("Elemento %d:%d",i+1,v[i]);
    }
    printf ("\n");
    for(i=0;i<N;i++)
    {
        printf("Elemento %d:%d",i+1,v[i]);

       for(j=0;j<v[i];j++)
       {
           printf("*");
       }
       printf("\n");
    }

    return 0;
}
