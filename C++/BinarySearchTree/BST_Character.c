//         __________________
       	 // Name: Amon Mishra\\
      	//    ID : 5580201    \\
       // Programing Technique \\
//     --------------------------

#include<stdio.h>
#include<sys/stat.h>
#include<stdlib.h>
#include<string.h>
//#include<conio.h>
#include<ctype.h>


typedef struct bst_node
{
	char  *data;	  // Data element of a bst node
	struct bst_node *right;   // Pointer element of a right bst node
    struct bst_node *left;     // Pointer element of a left bst node
} BST_NODE;



BST_NODE* root;
int g=0;
void ins_node(char x[])
{
	BST_NODE* p = (BST_NODE *) malloc(sizeof(BST_NODE)),*p1;
	int dir=0;
	//p=p1=NULL;
	if(root)
	{
		p=root;
		while(root)
		{
			p1=p;
			if(strcmp(x,p->data)>0) { p = p->right; dir = 1; }
	          else { p= p->left; dir = 0; }
		}
		if(dir) p1->right->data=x;//->data=x;
		else    p1->left->data=x;
			    g++;
				printf("\n%s is number%d\n",x,g);
	}
else
{

			  p->data = x;
              p->left = p->right = NULL;
	      g++;
		  printf("\n%s is number%d\n",x,g);
}

void show()
{
		BST_NODE* p = (BST_NODE *) malloc(sizeof(BST_NODE)),*p1;
		p=p1=root;
		printf("Showing the contents (%d):",g);
		while(p1)
		{
			printf("%s",p1->data);
		}

}

//void del_node(char x[])
//{
//	printf("\nWorking on this part...\n");
//}

}
void exitt()
{
	printf("May Radhekrishna Bless you\nJai Gurudev Bhagwan");
	printf("\n\n\n\t\t\t\tEXIT SUCCESSFUL!!");
	getchar();
	exit(0);
}
 

int main()
{
char cc[8],c,sent[BUFSIZ],sent2[BUFSIZ];
int i,j=0,k,st,ff,f,gg=0;
if(j=0){root=NULL;

	printf("\n\nPassword: ");
        fflush(stdin);
        gets(cc);
	if(strcmp(cc,"#ch_bst")==0)ff=12;
}

if(ff==12){

printf("\nEntered character BST\n");

	while(gg!=12)
	{

if(j==0)printf("\n\n\t\t1)'i' to insert\n\t\t2)'d' to delete\n\t\t3)'s' to list\n\t\t4)'q' to delete");
j++;
	printf("\n\nUser Command: ");
	fflush(stdin);
	gets(sent);
	if(sent[0]=='q'&&sent[1]=='\0')exitt();
	st=strlen(sent);
//k=st-2;
c='x';

    if(sent[0]=='i'||sent[0]=='d'||sent[0]=='s'||sent[0]=='q')
    {
          if(sent[0]=='s'&&sent[1]=='\0')c='s';
		  
		 
    if(sent[1]==' ')
  {

	if(sent[0]=='i')c='i';
        if(sent[0]=='d')c='d';
	sscanf(sent,"%*s %s",sent2);
  }

    }


switch(c)
{
case 'i':ins_node(sent2);printf("%s has been added",sent2);break;
case 'd':printf("\nworking on this\n");break;//{if(g==0)
	//{
	//printf("You've not yet added %s,BST is empty\nfist add, here are the instructions\n",sent2);
	//main();
	//}else del_node(sent2);}break;
case 's':printf("\nJai Radhekrishna\n");break;// show();break;
default :printf("\nWrong Input\n");main();
}

}
}
     return 0;
}