#include <stdio.h>
#include <stdlib.h>
#define N 5

int main()
{
    int v[N], value;
    unsigned int n_elements;
    unsigned int i;

    for (n_elements=0; n_elements<N; n_elements++)
    {

        printf("Insert a value: ");
        scanf("%d", &value);

        for (i=n_elements; i>0; i--)
        {
            if ( value<v[i-1] )
            {
                v[i] = v[i-1];
            }
            else
            {
                break;
            }
        }
        v[i] = value;

    }


    printf("VEC:");
    for (i=0; i<N; i++)
    {
        printf(" %d", v[i]);
    }
    printf("\n");

}
