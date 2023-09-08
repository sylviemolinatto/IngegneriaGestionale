#include <stdio.h>
#include <stdlib.h>
#define N 15

int main(int argc,char *argv[])
{
    FILE *fp;
    float map[N][N];
    int riga,colonna,immersioni,maxImmersioni,i,j,recuperabili;
    float peso,maxPressione,maxPeso;
    immersioni=0;
    recuperabili=0;
    if(argc!=6)
    {
        printf("Errore parametri!\n");
        return -1;
    }
    maxPressione=atof(argv[3]);
    maxPeso=atof(argv[4]);
    maxImmersioni=atoi(argv[5]);
    fp=fopen(argv[1],"r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file!\n");
        return -1;
    }
    for(i=0;i<N;i++)
    {
        for(j=0;j<N;j++)
        {
            fscanf(fp,"%f",&map[i][j]);
        }
    }
    fclose(fp);
    fp=fopen(argv[2],"r");
    if(fp==NULL)
    {
        printf("Errore nell'apertura del file\n");
        return -1;
    }
    while(fscanf(fp,"%d %d %f",&riga,&colonna,&peso)!=EOF)
    {
        if(peso>maxPeso)//se il peso del reperto è superiore al peso massimo non è possibile effettuare l'immersione
            printf("Reperto: %d %d Peso superiore\n",riga,colonna);
        else if(map[riga][colonna]>maxPressione)//se la pressione nel punto in cui si trova il reperto è superiore alla massima pressione sopportabile dal sottomarino non è possibile effettuare l'immersione
            printf("Reperto: %d %d Pressione: %f\n",riga,colonna,map[riga][colonna]);
        else
        {
            if(immersioni<maxImmersioni)
            {
                immersioni++;
                printf("Immersione %d: %d %d\n",immersioni,riga,colonna);
            }
            else//non è possibile fare ulteriori immersioni se si è superato il limite massimo
                recuperabili++;
        }
    }
    fclose(fp);
    if(recuperabili>0)
    {
        printf("Numero di immersioni superato\n");
        printf("reperti recuperabili: %d\n",recuperabili);
    }

return 0;

}
