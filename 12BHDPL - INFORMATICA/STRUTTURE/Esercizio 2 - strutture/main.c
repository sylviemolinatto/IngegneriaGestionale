#include <stdio.h>
#include <stdlib.h>
#define N 3
typedef struct
{
    char nome[20];
    char prof[20];
    float media;
}corso;
void readCourses(corso v[],int dim);
int getEasiest(corso v[],int dim);
int main()
{
    corso v[N];
    int corso_facile;
    readCourses(v,N);
    corso_facile=getEasiest(v,N);
    printf("Il corso piu' semplice e': \n");
    printf("Corso: %s\nProfessore: %s\nMedia: %.2f\n",v[corso_facile].nome,v[corso_facile].prof,v[corso_facile].media);

    return 0;
}

void readCourses(corso v[],int dim)
{
    int i;
    for(i=0;i<dim;i++)
    {
        printf("Nome: \n");
        scanf("%s",v[i].nome);
        printf("Professore: \n");
        scanf("%s",v[i].prof);
        printf("Media: \n");
        scanf("%f",&v[i].media);
    }
    return;
}
int getEasiest(corso v[],int dim)
{
    int max=0,i,indice;
    for(i=0;i<dim;i++)
    {
        if(max<v[i].media)
        {
            max=v[i].media;
            indice=i;
        }
    }
    return indice;
}
