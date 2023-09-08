#include <stdio.h>
#include <stdlib.h>
#define R 5
#define C 11
int main()
{
    int i=0,j=0,k=0,trovato=0,percorso=1,r=-1;
    int vett[C];
    char m[R][C]=
    {
        {"**.*.*....*"},
        {"..*.*...**."},
        {"*.....*...."},
        {".*.*.*.*.*."},
        {"..*.*...*.*"}
    };
    do
    {
        r++;
        j=0;
        k=0;
        trovato=0;
        percorso=1;
        i=r;
        if(m[i][0]=='*')
        {
            vett[k]=i;
            k++;
            do
            {
                if(m[i][j]=='*'&&m[i][j+1]=='*')
                {
                    trovato++;
                    vett[k]=i;
                    j++;
                    k++;
                }
                else if(m[i][j]=='*'&&m[i+1][j]=='*')
                {
                    trovato++;
                    vett[k]=i+1;
                    i++;
                    k++;
                }
                else if(m[i][j]=='*'&&m[i+1][j+1]=='*')
                {
                    trovato++;
                    vett[k]=i+1;
                    i++;
                    j++;
                    k++;
                }
                else if(m[i][j]=='*'&&m[i-1][j+1]=='*')
                {
                    trovato++;
                    vett[k]=i-1;
                    i--;
                    j++;
                    k++;
                }
                else
                    percorso=0;
            }
            while(j<C-1&&percorso==1);
        }

    }while(j<C-1);






       if (trovato==C-1)
        {
            for(i=0; i<C; i++)
            {
                printf("%d",vett[i]);
            }
        }
        else
            printf("Non e' possibile raggiungere la fine del percorso\n");









        return 0;
    }
