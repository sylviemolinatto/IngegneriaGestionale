#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <float.h>

#define N 2
typedef struct
{
    float temp;
    int letti;
    float temp_max;
    char ora[9];
}sensore;

int main(int argc,char *argv[])
{
    FILE *fp;
    sensore sensori[N][N];
    int i,j,riga,colonna,pos_x,pos_y;
    char ora[9];
    float temp,temp_media,temp_max;
    if(argc!=2)
    {
        printf("Errore parametri\n");
        return -1;
    }
    for(i=0;i<N;i++)
    {
        for(j=0;j<N;j++)
        {
            sensori[i][j].temp=0;
            sensori[i][j].temp_max=0;
            sensori[i][j].letti=0;
        }
    }
    fp=fopen(argv[1],"r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -2;
    }
    while(fscanf(fp,"%s %d %d %f",ora,&riga,&colonna,&temp)!=EOF)
    {
        if((sensori[riga][colonna].temp==0)&&(sensori[riga][colonna].temp_max==0)&&(sensori[riga][colonna].letti==0))
        {
            sensori[riga][colonna].temp=temp;
            sensori[riga][colonna].temp_max=temp;
            sensori[riga][colonna].letti++;
            strcpy(sensori[riga][colonna].ora,ora);
        }
        else
        {
            sensori[riga][colonna].temp=sensori[riga][colonna].temp+temp;
            sensori[riga][colonna].letti++;
            if(temp>sensori[riga][colonna].temp_max)
            {
                sensori[riga][colonna].temp_max=temp;
                strcpy(sensori[riga][colonna].ora,ora);
            }
        }
    }
    fclose(fp);
    for(i=0;i<N;i++)
    {
        for(j=0;j<N;j++)
        {
            temp_media=(float)sensori[i][j].temp/sensori[i][j].letti;
            printf("%.2f",temp_media);
            printf(" ");
        }
        printf("\n");
    }
    temp_max=FLT_MIN;
    for(i=0;i<N;i++)
    {
        for(j=0;j<N;j++)
        {
            if(sensori[i][j].temp_max>temp_max)
            {
                temp_max=sensori[i][j].temp_max;
                pos_x=i;
                pos_y=j;
            }
        }
    }
    printf("Il valore massimo e' stato registrato dal sensore %d %d alle %s",pos_x,pos_y,sensori[pos_x][pos_y].ora);

    return 0;
}
