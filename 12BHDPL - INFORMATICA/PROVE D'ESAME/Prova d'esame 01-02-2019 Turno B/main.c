#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_SPOST 100
#define NOME 10
#define N 4
typedef struct
{
    int x;
    int y;
}spost;

typedef enum{TRUE,FALSE}boolean;
int main(int argc,char *argv[])
{
    FILE *fp;
    int i,j,k,l,lungh1,lungh2,conta=0;
    char m1[NOME+1],m2[NOME+1],robot[NOME+1];
    char spostamento1[(2*MAX_SPOST)+1],spostamento2[(2*MAX_SPOST)+1];
    boolean trovato1,trovato2;
    spost spost1[MAX_SPOST],spost2[MAX_SPOST];

    if(argc!=4)
    {
        printf("Errore parametri\n");
        return -1;
    }
    sscanf(argv[2],"%s",m1);
    sscanf(argv[3],"%s",m2);

    fp=fopen(argv[1],"r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -2;
    }
    trovato1=FALSE;
    trovato2=FALSE;

    while(fscanf(fp,"%s",robot)!=EOF)
    {
        i=0;
        if(strcmp(robot,m1)==0)
        {
            trovato1=TRUE;
            fscanf(fp,"%d %d",&spost1[i].x,&spost1[i].y);
            spost1[i].x--;
            spost1[i].y--;
            i++;
            fscanf(fp,"%s",spostamento1);
            lungh1=(strlen(spostamento1)/2);
            for(j=0;j<(lungh1)*2;j++)
            {
                if(spostamento1[j]=='+'&&spostamento1[j+1]=='v')
                {
                    spost1[i].x=spost1[i-1].x+1;
                    spost1[i].y=spost1[i-1].y;
                    i++;
                }
                if(spostamento1[j]=='-'&&spostamento1[j+1]=='v')
                {
                    spost1[i].x=spost1[i-1].x-1;
                    spost1[i].y=spost1[i-1].y;
                    i++;
                }
                if(spostamento1[j]=='+'&&spostamento1[j+1]=='o')
                {
                    spost1[i].x=spost1[i-1].x;
                    spost1[i].y=spost1[i-1].y+1;
                    i++;
                }
                if(spostamento1[j]=='-'&&spostamento1[j+1]=='o')
                {
                    spost1[i].x=spost1[i-1].x;
                    spost1[i].y=spost1[i-1].y-1;
                    i++;
                }
            }
        }
        k=0;
        if(strcmp(robot,m2)==0)
        {
            trovato2=TRUE;
            fscanf(fp,"%d %d",&spost2[k].x,&spost2[k].y);
            spost2[k].x--;
            spost2[k].y--;
            k++;
            fscanf(fp,"%s",spostamento2);
            lungh2=(strlen(spostamento2)/2);
            for(l=0;l<(lungh2)*2;l++)
            {
                if(spostamento2[l]=='+'&&spostamento2[l+1]=='v')
                {
                    spost2[k].x=spost2[k-1].x+1;
                    spost2[k].y=spost2[k-1].y;
                    k++;
                }
                if(spostamento2[l]=='-'&&spostamento2[l+1]=='v')
                {
                    spost2[k].x=spost2[k-1].x-1;
                    spost2[k].y=spost2[k-1].y;
                    k++;
                }
                if(spostamento2[l]=='+'&&spostamento2[l+1]=='o')
                {
                    spost2[k].x=spost2[k-1].x;
                    spost2[k].y=spost2[k-1].y+1;
                    k++;
                }
                if(spostamento2[l]=='-'&&spostamento2[l+1]=='o')
                {
                    spost2[k].x=spost2[k-1].x;
                    spost2[k].y=spost2[k-1].y-1;
                    k++;
                }
            }
        }
    }
    fclose(fp);
    if(trovato1==FALSE)
    {
        printf("Robot 1 inesistente nel file\n");
    }
    else if(trovato2==FALSE)
    {
        printf("Robot 2 inesistente nel file\n");
    }
    else
    {
        if(lungh1>lungh2)
        {
            for(i=0;i<lungh2+1;i++)
            {
                for(j=0;j<lungh1+1;j++)
                {
                    if((spost2[i].x==spost1[j].x)&&(spost2[i].y==spost1[j].y))
                        conta++;
                }
            }
        }
        else
        {
            for(i=0;i<lungh1+1;i++)
            {
                for(j=0;j<lungh2+1;j++)
                {
                    if((spost1[i].x==spost2[j].x)&&(spost1[i].y==spost2[j].y))
                        conta++;
                }
            }
        }
        printf("Ci sono %d caselle visitate da entrambi i robot\n",conta);
    }


    return 0;
}
