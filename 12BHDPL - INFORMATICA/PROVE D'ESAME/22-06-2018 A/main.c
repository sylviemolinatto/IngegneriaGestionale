#include <stdio.h>
#include <stdlib.h>

int main(int argc,char *argv[])
{
    FILE *fp1,*fp2,*fp3;
    char ora1[9],ora2[9];
    float val1,val2,media;
    int hh1,mm1,ss1,hh2,mm2,ss2;
    int orario1,orario2;

    if(argc!=3)
    {
        printf("Errore parametri");
        return -1;
    }
    fp1=fopen(argv[1],"r");
    if(fp1==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -2;
    }
    fp2=fopen(argv[2],"r");
    if(fp2==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -2;
    }
    fp3=fopen("burton.txt","w");
    if(fp3==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -2;
    }

    while((fscanf(fp1,"%s %f",ora1,&val1)!=EOF)&&(fscanf(fp2,"%s %f",ora2,&val2)!=EOF))
    {
        sscanf(ora1,"%d:%d:%d",&hh1,&mm1,&ss1);
        sscanf(ora2,"%d:%d:%d",&hh2,&mm2,&ss2);
        orario1=hh1*3600+mm1*60+ss1;
        orario2=hh2*3600+mm2*60+ss2;
        if(orario1<orario2)
        {
            fprintf(fp3,"%s %.2f\n%s %.2f\n",ora1,val1,ora2,val2);
        }
        if(orario2<orario1)
        {
            fprintf(fp3,"%s %.2f\n%s %.2f\n",ora2,val2,ora1,val1);
        }
        if(orario1==orario2)
        {
            media=(val1+val2)/2;
            fprintf(fp3,"%s %.2f\n",ora1,media);
        }

    }
    if(fscanf(fp1,"%s %f",ora1,&val1)==EOF)
    {
        while(fscanf(fp2,"%s %f",ora2,&val2)!=EOF)
        {
            fprintf(fp3,"%s %.2f\n",ora2,val2);

        }
    }
    if(fscanf(fp2,"%s %f",ora2,&val2)==EOF)
    {
        while(fscanf(fp1,"%s %f",ora1,&val1)!=EOF)
        {
            fprintf(fp3,"%s %.2f\n",ora1,val1);

        }
    }
    fclose(fp1);
    fclose(fp2);
    fclose(fp3);



    return 0;
}
