#include <stdio.h>
int sum_of_upper_two ( int x,int y);
int main ()
{
	int row,roww,column;
	scanf ("%d",&roww,printf("ENTER NO OF ROW ( 1 TO 17) : "));
	for ( row=1;row<=roww;row++)
	{
		for ( column = 1 ; column <=roww-row;column++)
		{
			printf("%5c",32);
		}
		for ( column = 1 ; column <=row;column++)
		{
			if (column==1)printf("%5d",sum_of_upper_two(row,column));
			else printf("%10d",sum_of_upper_two(row,column));
		}
		printf("\n");
	}
	return 0;
}
int sum_of_upper_two ( int x,int y)
{
	if(x==y||y==1)
	return 1;
	else return (sum_of_upper_two (x-1,y-1)+sum_of_upper_two (x-1,y));
}
