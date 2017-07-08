#include <stdio.h>
int main ()
{
	int row,roww,column,number=1;
	scanf ("%d",&roww,printf ("ENTER NO OF ROW ( 1 TO 28 ) : "));
	for ( row = 1; row <= roww;row++)
	{
		for (column =1;column<=roww-row;column++)
		{
			printf("%3c",32);
		}
		for ( column =1 ; column <= row;column++)
		{
			if ( column ==1)printf ("%3d",number++);//column + (row-1)*(row)/2) == number++
			else printf ("%6d",number++);//column + (row-1)*(row)/2) == number++
		}
		printf ("\n");
	}
	return 0;
}
