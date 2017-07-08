//check for prime number
#include<stdio.h>
#include<math.h>
int main()
{
	int number,i=0;
	while (1)
	{
		scanf ("%d",&number,printf ( " Enter a number to check all prime number till given number : "));
		for ( i=2;i<=number;i++) 
		{
			if ( prime ( i )!=0)
			printf ( " %d \n",prime ( i));
		}
		if (--i == number) return 0;
		printf ( " ENTER A VALID NUMBER , TRY AGAIN \n");
	}
}
int prime ( int number )
{
	int rootOfNumber=0 , i=0;
//	scanf ("%d",&number,printf ("enter a number to check whether prime or not : "));
	rootOfNumber = sqrt (number );
	for (i=2;i<=rootOfNumber;i++)
	{
		if ( number % i == 0)
		{
//			printf("have a factor = %d ",i);
			return 0;
		}
	}
	if (--i==rootOfNumber )
	{
//		printf ("prime number = %d",number);
		return number ;
	}
}
