#include <stdio.h>
#include <stdlib.h>

int main()
{
    const int N;
    scanf("%d",&N);
    int v[N],i,j,cu;
    cu=0;
    for(i=0;i<N;i++)
    {
        scanf("%d",&v[i]);
    }
    for(j=0;j<N;j++)
    {
        if(v[j]-v[j+1]==0)
        {
          cu++;
        }
    }
    printf("Le coppie di elementi uguali sono %d",cu);

}
