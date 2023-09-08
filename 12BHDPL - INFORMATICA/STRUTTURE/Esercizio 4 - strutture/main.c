#include <stdio.h>
#include <stdlib.h>
typedef struct
{
    int alt;
    int longt;
    int lat;
}rilievo;
int main(int argc,char *argv[])
{
    int i,risposta,j;
    rilievo v[10];
    do
    {
        printf("Nuova misurazione? 1)SI 2)NO\n");
        scanf("%d",&risposta);
        if(risposta==1)
        {
            printf("Altitudine: \n");
            scanf("%d",&v[i].alt);
            printf("Longitudine: \n");
            scanf("%d",&v[i].longt);
            printf("Latitudine: \n");
            scanf("%d",&v[i].lat);
            i++;
        }
    }while((i<10)&&(risposta==1));

    for(j=0;j<i;j++)
    {
        if((v[j].longt%2)==0)
            printf("alt: %d\nlongt: %d\nlat: %d\n",v[j].alt,v[j].longt,v[j].lat);
    }

    return 0;
}
