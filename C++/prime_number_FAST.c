#include<stdio.h>
#include<stdlib.h>
#include<conio.h>

int main()
{
	 int jsk,r,n=0,l,f=0,s,h=0,g;
	 int jms[25];
     FILE *k;
	 do
	 {
printf("Enter numbers between 1-10000 to find the primes: ");
scanf("%d",&g);
if(g<1||g>10000)printf("\n\t\tWrong Input!!\n\n");
	 }while(g<1||g>10000);
 //j=atoi(argv[1]);

if(g>0 && g<10001)
{

	printf("It's done!! go and check the file.\n");

k = fopen("Jrk.txt","w+" );

fprintf(k,"Prime number between 1-%d includes: \n",g);

for(r=2;r<g;r++)
{

 if(r<=100){
    if(r%2!=0 && r%3!=0 && r%5!=0 && r%7!=0 ||r==2||r==3||r==5||r==7)
		{
			 jms[f]=r;
			 fprintf(k,"%d, ",r);
			 n++;
			 f++;
		}
            }
if(r>100)
	{
		h=0;
		for(l=0;l<25;l++)
		{
			s=jms[l];
			if( (r%s)!=0||r==s) h++;

	       	}

	if(h==25){fprintf(k,"%d, ",r);n++;}

	}

}

fprintf(k,"\nThere are %d prime numbers between 1-%d",n,g);

}

getch();
return 0;

}
