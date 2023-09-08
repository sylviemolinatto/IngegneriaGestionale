#include <stdio.h>
#include <stdlib.h>
typedef struct
{
    int ore;
    int minuti;
    int secondi;
}orario;
orario CalcolaOrario(int num);
int main()
{
    int n;
    orario o;
    printf("Numero di secondi: \n");
    scanf("%d",&n);
    o=CalcolaOrario(n);
    printf("%d:%d:%d",o.ore,o.minuti,o.secondi);
    return 0;
}
orario CalcolaOrario(int num)
{
    orario time;
    time.secondi=num%60;
    time.minuti=(num/60)%60;
    time.ore=(num/60)/60;
    return time;
}
