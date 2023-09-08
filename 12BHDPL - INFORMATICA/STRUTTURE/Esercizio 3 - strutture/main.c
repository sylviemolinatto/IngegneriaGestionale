#include <stdio.h>
#include <stdlib.h>
#define N 10
typedef struct
{
    char nome[20];
    char cognome[20];
    int matricola;
}studente;
int main(int argc,char *argv[])
{
    studente v[N];
    studente tmp;
    int i,j,k;
    for(i=0;i<N;i++)
    {
        printf("Nome: \n");
        scanf("%s",tmp.nome);
        printf("Cognome: \n");
        scanf("%s",tmp.cognome);
        printf("Matricola: \n");
        scanf("%d",&tmp.matricola);
    }
    if(i==0)
        v[0]=tmp;
    else
    {
        j=0;
        while((j<i)&&(v[j].matricola<tmp.matricola))
        j++;
        k=i;
        while(k>j)
        {
            v[k]=v[k-1];
            k--;
        }
        v[j]=tmp;
    }
    for(i=0;i<N;i++)
    {
        printf("%s %s %d\n",v[i].nome,v[i].cognome,v[i].matricola);
    }
    return 0;
}
