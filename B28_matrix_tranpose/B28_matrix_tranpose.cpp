#include <stdio.h>
int main ()
{
	int a[4][5]={
		             {1,1,1,1},
		             {2,2,2,2},
		             {3,3},
		             {4}
		         };
	int i=0,j=0;
	int at[5][4];
	for (i=0;i<4;i++)
	for (j=0;j<5;j++)
	{
		at[j][i]=a[i][j];
	}
	for (i=0;i<5;i++,printf("\n"))
	for (j=0;j<4;j++)
	{
		printf ("%d ",at[i][j]);
	}
	return 0;
} 
		         
