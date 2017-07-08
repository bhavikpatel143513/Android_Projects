#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>
#define WOW printf("NOT WORKING");
struct slist 
{
	char data;
	slist *link;
};
slist * slist_add_first ( slist *shead , char add )
{
	slist *snode;
	snode = (slist*) malloc(sizeof ( slist ));
	snode->data=add;
	snode->link=shead;
	shead=snode;
	return shead;
}
void get_chars (char characters[])
{
	printf ("\n\t\t\t\t\tGive characters in continuous to add: ");
	scanf ("%s",characters );fflush(stdin);
}
void get_name (char name[])
{
	printf ("\n\t\t\t\t\tGive name to new list: ");
	scanf ("%s",name );fflush(stdin);
}
slist* create_new_slist ()
{
	int i=0;
	char add , another ='y',characters[100];
	slist *shead ;
	shead=NULL;
//	slist_set();
	get_chars (characters);
	i=strlen(characters)-1;
	while (i>=0)
	{
		shead = slist_add_first ( shead , characters[i] );
		i--;
	}
	return shead;
}
void display_slist ( slist * shead )
{
	slist *temp;
	temp= shead;
	printf ("\n\t\t\t\t\t");
	while ( temp!=NULL)
	{
		printf("%c",temp->data);
		temp=temp->link;
	}
}

int main ()
{
	slist *shead;
	shead = create_new_slist (); 
	display_slist ( shead);
	return 0;
}
