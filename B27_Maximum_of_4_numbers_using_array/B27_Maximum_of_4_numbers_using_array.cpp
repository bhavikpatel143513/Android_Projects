// MAXIMUM OF FOUR NUMBERS USING ARRAY;
#include <stdio.h>
int main()
{
	int number[4];
	printf ("ENTER FOUR NUMBERS : ");
	int i=0,max=0;
	while (i<4)
	{
		scanf ("%d",&number[i]);
		if(i==0)
		{
			max=number[i];
			i++;
			continue;
		}
		if (number[i]>max)
		max=number[i];
		i++;
	}
	printf ("MAXIMUN NUMBER IS : %d ",max);
	return 0;
}
	
