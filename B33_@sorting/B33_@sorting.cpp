//INSERSION SORTING 
#include <stdio.h>
int main ()
{
	int num,i;
	scanf ("%d",&num,printf("ENTER NUMBER OF NUMBERS TO BE SORTED \n"));
	int sort[num];
	printf ("ENTER NUMBERS ONE BY ONE : \n");
	for (i=0;i<num;i++)
	{
		scanf ("%d",&sort[i]);
	}
	int current_number;int j;
	for (i=1;i<num;i++)
	{
		current_number=sort[i];
		j=i;
		while ( j>=1&&sort[j-1]>current_number)
		{
			sort[j]=sort[j-1];
			sort[j-1]=current_number;
			j--;
		}
	}
	for (i=0;i<num;i++)
	{
		printf ("%d ",sort[i]);
	}
	return 0;
}
	
