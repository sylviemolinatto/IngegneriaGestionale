#include <stdio.h>
#include <stdlib.h>

int main(int argc,char *argv[])
{
    int num1,num2;
    if(argc!=3)
    {
        printf("Errore!\n");
    }
    else
        {
            printf("%s\n",argv[1]);
            printf("%s\n",argv[2]);
        }
    num1=atoi(argv[1]);
    num2=atoi(argv[2]);
    if((num1<-1000)||(num1>1000)||(num2<-1000)||(num2>1000))
       printf("Errore: i valori passati dalla linea di comando devono essere compresi tra -1000 e 1000\n");


    printf("La somma e' %d",num1+num2);

    return 0;
}
