#include <stdio.h>
int main ()
{
	char alphabet,character;
	scanf ("%c",&alphabet,printf ("ENTER ALPHABET : "));
	int alpha=alphabet,totalColumn,row,column,blank=0;
	alpha=alpha-32-64;
	totalColumn=alpha*2-1;
	for ( row = 0 ; row <= alpha ; row++ )
	{
		blank=row*2-1;
		for ( column = 1 ; column <= (totalColumn-blank)/2 ; column ++)
		{
			printf ("%3c",character=64+column);
		}
		for ( ; column <= (totalColumn-blank)/2+blank;column++)
		{
			printf("%3c",32);
		}
		for ( ; column <= totalColumn;column++)
		{
			printf("%3c",character+(totalColumn-blank)/2+blank+1-column);
		}
		printf("\n");
	}
	return 0;
}
