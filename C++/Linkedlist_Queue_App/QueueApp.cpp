#include<iostream>
#include<cstdlib>
#include<unistd.h>
#include "queue.h"


using namespace std;


int main()
{

char c,sent[BUFSIZ],g[1];
int i,j=0,k,st,ff,f,gg;


cout<<endl<<"This program can do both linkedlist and queue"<<endl<<"Instructions:"<<endl<<"Enter 1 for linkedlist"<<endl<<"Enter 2 for queue"<<endl;
cin>>g;
gg=atoi(g);
system("clear");
if(gg!=1 && gg!=2){cout<<"That was a wrong input!!"<<endl;main();}
if(gg==2){printf("\n\n\t\t1)'E' to enqueue\t*capital E*\n\t\t2)'D' to dequeue\t*capital D*\n\t\t3)'S' to show list\n\t\t4)'Q' to to clear memory and exit\n\n");sleep(3);}


	while(true)
	{

		if(j==0&&gg!=2)
			{
				printf("\n\n\t\t1)'a' to add\n\t\t2)'d' to delete\n\t\t3)'l' to list\n\t\t4)'q' to clear memory and exit\n\n");
				sleep(3);
			}
	
	j++;
	
	printf("\n\nUser Command: ");
	gets(sent);
	if(sent[0]=='q'||sent[0]=='Q'&&sent[1]=='\0')break;
	st=strlen(sent);
	k=st-2;
	c='x';

    if(sent[0]=='a'||sent[0]=='d'||sent[0]=='l'||sent[0]=='E'||sent[0]=='D'||sent[0]=='S')
  {
          if(sent[0]=='l'&&sent[1]=='\0')c='l';
	  if(sent[0]=='S'&&sent[1]=='\0')c='l';
	
		  
		 
    if(sent[1]==' ')
  {
        ff=0;

    for(f=2;f<st;f++)
           {
                       if(isdigit(sent[f]))ff++;
                       else ff--;
           }	

    if(ff==k)
  {
	if(sent[0]=='a')c='a';
        if(sent[0]=='d')c='d';
	if(sent[0]=='E')c='E';
	if(sent[0]=='D')c='D';
	sscanf(sent,"%*s %d",&i);
  }
	}
		}


switch(c)
{
case 'a':jrk.ins_node(i);printf("%d has been added at the front (*LINKEDLIST*)",i);break;
case 'd':if(jrk.size==0){printf("You've not added any data to this empty list (*LINKEDLIST*)\nfist add, here are the instructions\n");main();}else jrk.del_node(i);break;
case 'l':jrk.list_node();break;
case 'E':jms.enqueue(i);printf("%d has been enqueued to the end (*QUEUE*)",i);break;
case 'D':if(jrk.size==0){printf("You've not added any data to this empty list\nfist add, here are the instructions\n");main();}else jms.dequeue(i);break;
default :if(j!=1){system("clear");printf("\n\n\n\t\t\tYou Are On Hold\n\t\t\t  Wrong Input\n");sleep(3);}system("clear");cout<<"Hold is currently Unlocked! :)"<<endl<<endl;
}

}

     return 0;
}
