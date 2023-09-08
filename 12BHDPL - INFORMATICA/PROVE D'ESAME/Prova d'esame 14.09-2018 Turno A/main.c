#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_PAROLA 20
#define MAXF1 1000
#define MAXF2 40
#define MAX_RIGA 100

int main(int argc,char *argv[])
{
    FILE *fp;
    int i,j,n1,n2,pos;
    char prima[MAXF1][MAX_PAROLA+1];
    char parola[MAX_PAROLA];
    char seconda[MAXF2*MAX_RIGA];

    if(argc!=2)
    {
        printf("Errore parametri\n");
        return -1;
    }
    fp=fopen("prima.txt","r");
    if(fp==NULL)
    {
        printf("Errore nell'aperura del primo file\n");
        return -1;
    }
    i=0;j=0;
    while(fscanf(fp,"%s",prima[i])!=EOF)
    {
        i++;
    }
    n1=i;
    fclose(fp);

    fp=fopen("seconda.txt","r");
    if(fp==NULL)
    {
        printf("Errore nell'aperura del secondo file\n");
        return -1;
    }
    i=0;
    while(fscanf(fp,"%c",&seconda[i])!=EOF)
    {
        i++;
    }
    n2=i;
    fclose(fp);
    fp=fopen(argv[1],"r");
    if(fp==NULL)
    {
        printf("Errore nell'aperura del terzo file\n");
        return -1;
    }
    while(fscanf(fp,"%s",parola)!=EOF)
    {
        for(i=0;i<n1;i++)
        {
            if(strcmp(parola,prima[i])==0)
            {
                pos=i;
                if(pos<=n2)
                    printf("%c",seconda[pos]);
            }
        }
    }
    fclose(fp);
    return 0;
}
