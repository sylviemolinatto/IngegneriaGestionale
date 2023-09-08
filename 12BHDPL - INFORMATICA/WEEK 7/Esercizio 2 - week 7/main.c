#include <stdio.h>
#include <stdlib.h>
void ordinaVettore(int v[],int n);
int main()
{
    const int N;
    int i,num,pos1,pos2;
    pos1=0;
    pos2=0;
    printf("Inserisci N: \n");
    scanf("%d",&N);
    int v1[N],v2[N],v3[2*N];
    for (i=0; (i<2*N)&&(pos1<=N-1)&&(pos2<=N-1); i++)
    {
        printf("Inserisci un numero: \n");
        scanf("%d",&num);
        if(num%2==0)
        {
            v1[i]=num;
            pos1++;
            printf("L'elemento %d viene inserito nel vettore 1 nella posizione %d\n",i,pos1);
        }
        else
        {
            v2[i]=num;
            pos2++;
            printf("L'elemento %d viene inserito nel vettore 2 nella posizione %d\n",i,pos2);
        }
    }
    printf("\n");

    for(i=0; i<pos1; i++)
    {
        v3[i]=v1[i];
    }
    for(i=pos1; i<pos1+pos2; i++)
    {
        v3[i]=v1[i-pos1];

    }
ordinaVettore(v3,pos1+pos2);
printf("Vettore 3: ");
for(i=0; i<pos1+pos2; i++)
    printf("%d",v3[i]);

return 0;
}
void ordinaVettore(int v[], int n)
{
    int i, j, tmp;
    for(i = 0; i < n; i++)
    {
        tmp = v[i];
        for(j = i-1; (j >= 0)&&(v[j] >= tmp); j--)
            v[j+1] = v[j];
        v[j+1] = tmp;
    }
    return;
}
