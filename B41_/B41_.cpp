#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
struct clist
{
	struct clist * n ;
	char * d ;
};
char ** af ( char ** head , char * d )
{
	
	struct clist * neww ;
	neww = ( struct clist * ) malloc ( sizeof (struct clist ) );
	neww->d = d ;
	if ( head == NULL ) 
	neww->n = neww ;
	else 
	{
		char ** temp = head ;
		neww->n = ( struct clist * )head ;
		while ( *temp != ( char * )head )
		temp = ( char ** ) * temp ;
		*temp = ( char *)neww;
	}
	head = (char **)neww ;
	return head;
}
void show ( char ** head ) 
{
	char ** temp = head ;char ** temp1 =  ( char ** )*(temp+1);
	if ( temp == NULL ) 
	printf ("\n\tlist is empty ");
	else 
	{
		do
		{
			
			do
			{
				printf ( "\n\t%c",**(temp1+1));
				temp1 = ( char ** ) *temp1;
			}while ( temp1 != temp1 );
			temp = ( char ** ) *temp;
		}while ( temp != ( char **)head );
	}
}
char ** gd ( char ** head )
{
	char c=2; ;
	fflush ( stdin ) ;
	while ( c!=10 )
	{
		scanf ( "%c",&c );
		if ( c != 10 )
		head = af ( head , &c );
		else
		break;
	}
	return head ;
}
int main ()
{
	char ** chead = NULL ;
	char ** data = NULL ;	
	data = gd ( data ) ;
	chead = af ( chead , ( char * ) data );
	show ( chead ) ;
	return 0 ;
}




































	
