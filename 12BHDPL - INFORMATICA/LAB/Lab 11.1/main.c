#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
typedef struct
{
    int x;
    int y;
}punto;
float distanza(punto p1,punto p2);
int main(int argc,char *argv[])
{
    punto p1,p2,p3,p4;
    float d1,d2,d3,d4,lunghezza_percorso,distanza_minima;
    printf("Inserisci x1,y1,x2,y2,x3,y3,x4,y4: \n");
    scanf("%d%d%d%d%d%d%d%d",&p1.x,&p1.y,&p2.x,&p2.y,&p3.x,&p3.y,&p4.x,&p4.y);
    if(argc!=2)
    {
        printf("Errore parametri!\n");
        return -1;
    }
    d1=distanza(p1,p2);
    d2=distanza(p2,p3);
    d3=distanza(p3,p4);
    d4=distanza(p4,p1);
    if(strcmp(argv[1],"-m")==0)
    {
      lunghezza_percorso=d1+d2+d3+d4;
      printf("La lunghezza del percorso e' %.2f",lunghezza_percorso);
    }
    if(strcmp(argv[1],"-a")==0)
    {
      distanza_minima=d1;
      if(d2<distanza_minima)
        distanza_minima=d2;
      if(d3<distanza_minima)
        distanza_minima=d3;
      if(d4<distanza_minima)
        distanza_minima=d4;
      printf("La distanza minima tra 2 punti e' : %.2f",distanza_minima);
    }

    return 0;
}

float distanza(punto p1,punto p2)
{
    float d;
    d=sqrt(pow((p2.x-p1.x),2)+pow((p2.y-p1.y),2));
    return d;
}
