#include <iostream>
#include "linkedlist_5580201.h"

using namespace std;

class Q_NODE: public L_node{};

class QUEUE : public Lnk_Lst
{

public:
	Q_NODE *toq;

	QUEUE(Q_NODE *tt=NULL, int s=0,L_node *h=NULL, L_node *t=NULL);

	~QUEUE(void)
	{
		
		Q_NODE *prvs, *crnt = (Q_NODE *) head;
		
			  while(crnt)
			  {
				    prvs = crnt;
				    crnt = (Q_NODE *) crnt->next;
				    delete prvs;
			  }

		cout<<"\n\t\t\t\tQUEUE Destroyed!!  :)\n"<<endl;
		cout<<"\n\t\t\t    Linkedlist Destroyed!! :)\n"<<endl;
		cout<<nm<<" numbers have been deleted, the list is empty now"<<endl;
		cout<<"May Radhekrishna Bless you"<<endl<<"Jai Gurudev Bhagwan"<<endl;
		cout<<"\n\t\t\t\tEXIT SUCCESSFUL!!"<<endl;
		
		

	}
	 void enqueue(int x);
	 void dequeue(int x);

};


QUEUE::QUEUE(Q_NODE *tt, int s, L_node *h, L_node *t):Lnk_Lst(s,h,t)
	{
	  toq=tt;
	}
Lnk_Lst jrk;
QUEUE jms;

void QUEUE::enqueue(int x)
{
      Q_NODE *n = new Q_NODE;
      Q_NODE *n1;
      if(jrk.head==NULL)
	{
		toq=n;
		n->value=x;
		jrk.head=n;
		n->next=NULL;
		
	}
 else 
     if(jrk.tail)
	{
		toq=(Q_NODE *)jrk.tail;
		toq->next=n;
		n->value=x;
		toq=n;
		n->next=NULL;	
				
	      
	}
else
	{
	      toq->next = n;
	      toq=n;
	      n->value=x;
	      n->next=NULL;
	     
    	}

jrk.tail=NULL;
jrk.size++;
nm=jrk.size;
}

//what if there is only one element in the list and delete i, where i is not the number?
void QUEUE::dequeue(int x)
{
	Q_NODE *prvs, *crnt = (Q_NODE *) jrk.head;

while (crnt && (crnt->value != x))
{
      prvs = crnt;
      crnt =(Q_NODE *) crnt->next;
}


	if(crnt)
	{

		Q_NODE *prvs, *crnt = (Q_NODE *) jrk.head;

			  while(crnt&&crnt->value!=x)
			  {
				    prvs = crnt;
				    cout<< crnt->value<<" has been dequeued!" << endl;
				    crnt = (Q_NODE *) crnt->next;
				    delete prvs;
                          	    jrk.size--;
			  }

	
			
			if(crnt)
				{
				    prvs = crnt;
				    cout<< crnt->value<<" has been dequeued!" << endl;
				    crnt = (Q_NODE *) crnt->next;
				    jrk.head = crnt;
				    delete prvs;
				    jrk.size--;
				}
			else
			{
				jrk.head=NULL;
				cout<<x<<" is not in the list! :("<<endl;

			}
}
else cout<<x<<" is not in the list! :("<<endl;

	nm=jrk.size;

}

void Lnk_Lst::list_node(void)
{
 int z=0;
 L_node* k=head;
                        printf("List is");
			if(size==0) printf(" empty\n\n");
			   else     printf("(%d numbers):",size);
                        while(k != NULL)
                        {
                                printf(" %d",k->value);
				z++;
				if(z!=size)printf(",");else printf(".");
                                k=k->next;
                        }
                        printf("\n");

}

