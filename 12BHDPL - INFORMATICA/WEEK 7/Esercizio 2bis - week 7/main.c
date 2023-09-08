#include <stdio.h>
#include <stdlib.h>
#define N 6
void ordinaVettore(int v[], int n);
int main()
{
    int v1[N], v2[N];
    int v3[2*N];
    int i1, i2, i, numero;
    i1 = 0;
    i2 = 0;
    do
    {
        printf("Numero: ");
        scanf("%d", &numero);
        if((numero % 2) == 0)
        {
            if(i1 < N)
            {
                v1[i1] = numero;
                i1++;
            }
        }

        else
        {
            if(i2 < N)
            {
                v2[i2] = numero;
                i2++;

            }

        }

    }
    while((i1 < N)&&(i2 < N));
    for(i = 0; i < i1; i++)
    {
        v3[i] = v1[i];

    }
    for(i = i1; i < i1+i2; i++)
    {
        v3[i] = v2[i-i1];

    }
    ordinaVettore(v3, i1+i2);
    printf("Vettore v3:\n");
           for(i = 0; i < i1+i2; i++)
           printf("%d ", v3[i]);
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

