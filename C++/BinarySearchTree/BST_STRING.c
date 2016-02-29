//         __________________
       	 // Name: Amon Mishra\\
      	//    ID : 5580201    \\
       // Programing Technique \\
//     --------------------------

#include<stdio.h>
#include<sys/stat.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>


typedef struct bst_node
{
	char  data[BUFSIZ];	  // Data element of a bst node
	struct bst_node *right;   // Pointer element of a right bst node
    struct bst_node *left;     // Pointer element of a left bst node
	struct bst_node *parent;     // Pointer element to the parent bst node
} BST_NODE;

int dir=2,g=0,j=0,ll=0,ff=0;
char ajarn_nop[BUFSIZ];

BST_NODE* ins_node(BST_NODE* p,char x[],int dir)
{

	BST_NODE *p1 = (BST_NODE *) malloc(sizeof(BST_NODE));
	if(p == NULL)
	{
		//p1=p->parent;
		p = (BST_NODE *) malloc(sizeof(BST_NODE));
		//p->parent=(BST_NODE *) malloc(sizeof(BST_NODE));
		strcpy(p->data,x);
		p->left =p->right = NULL;
		printf("\n\t\t%s has been added",p->data);
		if(dir==0)printf(" on the left to its parent @@ %s @@\n",ajarn_nop); //p->parent->data
		if(dir==1)printf(" on the right to its parent @@ %s @@\n",ajarn_nop);
		if(g==0)printf("\n\t\t**%s is the root**\n",p->data);
		g++;
	}
	else if(strcmp(x,p->data)<=0)
	{

		dir = 0;
		if(p->left==NULL){p->parent=p;strcpy(ajarn_nop,p->data);}
		p->left = ins_node(p->left,x,dir);
	}
	else
	{
		dir = 1;
		if(p->right==NULL){p->parent=p;strcpy(ajarn_nop,p->data);}
		p->right = ins_node(p->right,x,dir);
	}

	return p;
}
	void show(BST_NODE *root)
	{
		 if(root==NULL)return;
		show(root->left);       //Visit left subtree
		printf("%s->",root->data);  //Print data
		show(root->right);      // Visit right subtree
	}//*/
BST_NODE *FindMin(BST_NODE* root)
{
	while(root->left != NULL) root = root->left;
	return root;
}
BST_NODE* del_node(BST_NODE* root,char x[])
{
	if(root == NULL) return root;
	else if(strcmp(x,root->data)<0) root->left = del_node(root->left,x);
	else if(strcmp(x,root->data)>0) root->right = del_node(root->right,x);
	// Wohoo... I found you, Get ready to be deleted
	else
	{
		if(root->left == NULL && root->right == NULL)
		{
			free(root);
			g--;
			root = NULL;
		}
    else if(root->left == NULL)
		{
			BST_NODE *temp = root;
			root = root->right;
			free(temp);
			g--;
		}
	else if(root->right == NULL) {
			BST_NODE *temp = root;
			root = root->left;
			free( temp);
			g--;
		}
    else {
			BST_NODE *temp = FindMin(root->right);
			strcpy(root->data, temp->data);
			root->right = del_node(root->right,temp->data);
		     }

	}
		return root;
}

void exitt()
{
	printf("May Radhekrishna Bless you\nJai Gurudev Bhagwan");
	printf("\n\n\n\t\t\t\tEXIT SUCCESSFUL!!");
	exit(0);
}


void pswd()
{
	char cc[8];
	int lol=3;
	do
	{
	printf("\n\tPassword: "); gets(cc);
	if(strcmp(cc,"#ch_bst")==0)ff=12;else printf("Wrong Password! (%d)attempts left",lol);//"#ch_bst"
	if(lol==-1)exit(0);lol--;
	}while(strcmp(cc,"#ch_bst")!=0 && lol!=-1);
}
int main()
{
char c,sent[BUFSIZ],sent2[BUFSIZ];
int i,f,gg=0;
BST_NODE* root;
if(g==0)root=NULL;
BST_NODE* jrk=(BST_NODE *) malloc(sizeof(BST_NODE));

				if(j==0)
				{
					pswd();
				}

if(ff==12)
{
	if(j==0)printf("\nEntered character BST\n");

	while(gg!=12)
		{

			if(j==0)printf("\n\n\t\t1)'i' to insert\n\t\t2)'d' to delete\n\t\t3)'s' to list\n\t\t4)'q' to delete");
					j++;
			printf("\n\nUser Command: ");
			fflush(stdin);
			gets(sent);
			if(sent[0]=='q'&&sent[1]=='\0')exitt();
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
case 'i':root=ins_node(root,sent2,dir);break;
case 'd':ll=g;if(g==0)
			{
				printf("You've not yet added %s,BST is empty\nfist add by using 'i' one space and then your data\n",sent2);
				main();
			}
		 else root=del_node(root,sent2);
		 if(ll==g){printf("\n\tThere is no %s in the list\n\nFor the security reason please reenter the",sent2);pswd();}
		 else printf("%s has been deleted!!",sent2);
		 break;
case 's':if(root==NULL)printf("The list is empty\n");else { printf("The List is(%d): ",g);show(root);}break;
default :printf("\nWrong Input\n");main();

}
printf("%p\n",root);
}
}
     return 0;
}

