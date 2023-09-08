#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#define N 5
int main()
{
   char str1[N+1];
   char str2[N+1];
   int h1,h2,m1,m2,n1,n2;
   printf("inserisci orario 1:\n");
   gets(str1);
   sscanf(str1,"%d:%d",&h1,&m1);
   n1=strlen(str1);
   while(isdigit(str1[0])==0||isdigit(str1[1])==0||isdigit(str1[3])==0||isdigit(str1[4])==0)||(str1[2]!=':')||n1!=5||(str1[0]=='\0'||str1[1]=='\0'||str1[3]=='\0'||str1[4]=='\0')||h1>23||h1<0||m1<0||m1>59);
   {
        printf("formato orario1 non valido, reinserire nel formato hh:mm ");
        scanf("%s",str1);
        sscanf(str1,"%2d:%2d",&h1,&m1);
        n1=strlen(str1);

   }
   printf("inserisci orario 2:\n");
   gets(str2);
   sscanf(str2,"%d:%d",&h2,&m2);
   n2=strlen(str2);
   while(isdigit(str2[0])==0||isdigit(str2[1])==0||isdigit(str2[3])==0||isdigit(str2[4])==0)||(str2[2]!=':')||n2!=5||(str2[0]=='\0'||str2[1]=='\0'||str2[3]=='\0'||str2[3]=='\0')||h2>23||h2<0||m2<0||m2>59);
   {
        printf("formato orario2 non valido, reinserire nel formato hh:mm ");
        scanf("%s",str2);
        sscanf(str2,"%d:%d",&h2,&m2);
        n2=strlen(str2);
   }

   if(h1==h2&&m1==m2)
    printf("orari uguali\n");
   else if(h1<h2)
    printf("orario1 precedente a orario2\n");
   else if(h1==h2&&m1<m2)
    printf("orario1 precedente a orario2\n");
   else if(h1>h2)
    printf("orario 2 precedente a orario 1\n");
   else if(h1==h2&&m1>m2)
    printf("orario 2 precedente a orario 1\n");

   return 0;
   }


