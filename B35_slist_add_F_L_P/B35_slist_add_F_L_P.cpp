#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
struct dnode
{
	int data;
	dnode * link;
};
dnode* add ( dnode *dhead , int data )
{
	dnode * newnode=NULL;
	newnode = (dnode*)malloc(sizeof(dnode));
	newnode->data=data;
	if(dhead==NULL)
	newnode->link=NULL;
	else
	newnode->link=dhead;
	dhead=newnode;
	return dhead;
}
dnode* addlast ( dnode *dhead , int data )
{
	dnode* temp=dhead;
	dnode * newnode=NULL;
	newnode = (dnode*)malloc(sizeof(dnode));
	newnode->data=data;
	while ( temp->link!=NULL)
	{
//		printf ("%d",temp->data);
		temp=temp->link;//printf ("%d",temp->data);
	}
	newnode->link=NULL;
	temp->link=newnode;
	return dhead;
}
dnode* add_at_p ( dnode * dhead, int data , int p )
{
	int choise;
	dnode* temp;
	dnode*newnode;
	newnode = ( dnode *) malloc (sizeof(dnode));
	newnode->data=data;
	newnode->link=NULL;
	if(p<=0||p>1&&dhead==NULL)
	{
		printf("invalid input\n");
		return NULL;
	}
	else if (p==1)
	{
		if(dhead==NULL)
		{
			dhead = newnode;
			return dhead;
		}
		newnode->link=dhead;
		dhead = newnode;
		return dhead;
	}
	else
	{
		choise=2;
		temp=dhead;
		while ( choise < p&&temp->link!=NULL)
		{
			temp=temp->link;
			choise++;
		}
//		printf("WOW choise = %d ",choise);
		if(choise==p)
		{
			newnode->link=temp->link;
			temp->link=newnode;
		}
		else if(temp->link==NULL)
		{
			printf("invalid input \n");
			return NULL;
		}
		return dhead;
	}
}
	
int main()
{
	int data,p;
	char another ='y';
	dnode*dhead=NULL;
	dnode * temp=NULL;
	//add first
	while ( another =='y')
	{
		scanf("%d",&data,printf("enter data"));fflush(stdin);
		dhead = add(dhead,data);
		scanf("%c",&another,printf("want to add another "));fflush(stdin);
	}
	temp=dhead;
	while(temp!=NULL)
	{
		printf("%d",temp->data);
		temp=temp->link;
	}
	another='y';
	//add last
	while ( another =='y')
	{
		scanf("%d",&data,printf("enter data"));fflush(stdin);
		dhead = addlast(dhead,data);
		scanf("%c",&another,printf("want to add another "));fflush(stdin);
	}
	temp=dhead;
	while(temp!=NULL)
	{
		printf("%d",temp->data);
		temp=temp->link;
	} 
	
	
	// add at p position
	another = 'y';
	while ( another =='y')
	{
		scanf("%d",&data,printf("enter data :"));fflush(stdin);
		scanf("%d",&p,printf("enter position :"));fflush(stdin);
		dhead = add_at_p(dhead,data,p);
		temp=dhead;
		while(temp!=NULL)
		{
			printf("%d",temp->data);
			temp=temp->link;
		} 
		scanf("%c",&another,printf("\nwant to add another "));fflush(stdin);
	}
	
	return 0;
}
