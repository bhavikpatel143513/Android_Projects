#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
struct A
{
	struct A * next ;
	struct A * previous ;
	char v;
};
char* add ( char * head , char i )
{
	struct A * neww ;
	neww = (struct A * ) malloc ( sizeof ( struct A ) ) ;
	neww->previous = NULL;
	neww->v = i;
	if ( head == NULL ) 
	neww->next = NULL;
	else 
	{
		neww->next = ( struct A* ) head ;
		neww->next->previous = neww ;
	}
	head = (char *) neww ;
//	printf ("\n\n\n%c\n\n\n",*(head+16));
	return head ;
}
int main ()
{
	char p ='y';
//	int i =1;
	char * head = NULL;
	printf ("give  =  ");
	do
	{
//		printf ("WOW");
//		fflush (stdin);
		scanf ("%c",&p);
		head = add ( head ,p );
//		printf ("\n%c",*(head+16));
	}while( *(head+16) != 10);
//	printf ("WOWwwwwwwwwwwwwww");
	struct A * temp ;
	temp = (struct A * ) head ;
	for ( ; temp->next !=NULL;)
	{
		
//		printf ("\n%c",temp->v);
		temp = temp->next;
	}
	for ( ; temp!=NULL;)
	{
		
		printf ("%c",temp->v);
		temp = temp->previous;
	}
	return 0;
}


























		
