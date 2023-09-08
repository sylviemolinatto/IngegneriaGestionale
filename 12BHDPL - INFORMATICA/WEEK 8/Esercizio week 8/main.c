#include <stdio.h>
#include <stdlib.h>

int main()
{
    int tasto;
    printf("Premi un tasto...\n");
    tasto = getchar();
    if (tasto != EOF)
    {
        printf("Hai premuto %c\n", tasto);
        printf("Codice ASCII = %d\n", tasto);
    }
    return 0;
    return 0;
}
