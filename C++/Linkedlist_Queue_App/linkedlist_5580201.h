//         __________________
       	 // Name: Amon Mishra\\
      	//    ID : 5580201    \\
       // Programing Techni
 //

//     --------------------------

#include<stdio.h>
#include<iostream>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>


using namespace std;
int nm=0;
class L_node
{
	public :
	int value;
	L_node *next;
	
};

class Lnk_Lst
{
	public : 
	 
	int size;
	L_node* head;
	L_node* tail;	
	Lnk_Lst(int s=0, L_node *h = NULL, L_node *t = NULL)
	 {
	    size = s;
	    head = h;
	    tail = t;
	 }

      ~Lnk_Lst()  
{
	L_node* tmp;
	 while(head) 
	{
	    tmp = head;
	    head = head->next;
	    delete tmp;
	}
	exit(0);

}
      void list_node(void);
      void ins_node(int x);
      void del_node(int n);

};



void Lnk_Lst::ins_node(int x)
{

     L_node* p = new L_node; //allocate space
     L_node* p1;
	if(head)
	{
		p->value=x;
		p->next=head;
		head=p;
		size++;
	}
else
{
	      //tail=p;
              p->value = x;
              p->next = head;
	      head = p;
	      size++;
}
p1=head;
while(p1->next!=NULL)
{
  p1 = p1->next;
}
tail=p1;
nm=size;

}



void Lnk_Lst::del_node(int x)
{
L_node *crnt,*prvs;

if(head->value==x)
{
crnt=head;
head=head->next;
delete crnt;
size--;
}
else{
	crnt=prvs=head;
while (crnt && (crnt->value != x))
{
      prvs = crnt;
      crnt = crnt->next;
}

     if (prvs && crnt)
     {
		prvs->next=crnt->next;
		delete crnt;
		size--;
     }

if(crnt==NULL)printf("There is no %d in the list",x);
}
}



