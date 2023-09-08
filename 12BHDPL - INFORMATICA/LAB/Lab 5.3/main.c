#include <stdio.h>
#include <stdlib.h>

int main()
{
    const int N;
    printf("Inserisci il valore di N: \n");
    scanf("%d",&N);
    int v1[N],v2[N];
    int i,num,j=0,k=0,pieno1=1,pieno2=1,l,q;
    for (i=0;(i<2*N)||(pieno1<N-1)||(pieno2<N-1);i++)
    {
        printf("Inserisci num: \n");
        scanf("%d",&num);
        if((num>0)||((num<0)&&(num%3==0)))
        {
            v1[j]=num;
            printf("il valore %d viene salvato nel vettore1",v1[j]);
            j++;
            pieno1++;

        }
        else if((num<0)&&(num%3!=0)&&(num%2!=0))
        {
            v2[k]=num;
            printf("il valore %d viene salvato nel vettore 2",v2[k]);
            k++;
            pieno2++;

        }
        else
        {
            printf("il valore non viene inserito in nessuno dei due vettori");
        }

    }
    printf("Nel primo vettore sono contenuti i seguenti vettori:");
    for(l=0;l<j;l++)
    printf("%d",v1[k]);
    printf("Nel secondo vettore sono contenuti i seguenti vettori:");
    for(q=0;q<k;q++)
    printf("%d",v2[q]);
    printf("\n");
    return 0;
}
