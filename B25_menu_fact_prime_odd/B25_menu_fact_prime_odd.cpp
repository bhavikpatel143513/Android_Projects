#include <stdio.h>
#include <math.h>
void odd_or_even ();
int prime (int number );
void prime_or_not ();
int get_number ();
int fact (long number);
void factorial ();
int main ()
{
	int button=0;
    while(1)
	{	
		printf("\n\n\n\n\n");
		printf("\t\t\t\t\tSelect any option :     \n");
		printf("\t\t\t\t\t1) Factorial of a number\n");
		printf("\t\t\t\t\t2) Prime or not         \n");
		printf("\t\t\t\t\t3) Odd or even          \n");
		printf("\t\t\t\t\t4) Exit                 \n");
		scanf("%d",&button);
		switch (button)
		{
			case 1:
				factorial();
				break;	
			case 2:
			    prime_or_not();
				break;
			case 3:
			    odd_or_even();
			    break;
			case 4:	
       	     return 0;
    	}
    }
}
void factorial ()
{
	long number=0;
	do
	{
		
		printf("\n\n\n");
		scanf ("%ld",&number,printf("\t\t\t\t\tENTER A NUMBER : "));
		if(number ==0)
		{
			printf("\t\t\t\t\tFactorial of %d is = %d\n",number,1);
			return;
		}
		if (number<=-1)
		printf("\t\t\t\t\tGIVE A VALID INPUT , TRY AGAIN \n\n");
		
	}while(number <=-1);
	printf("\t\t\t\t\tFactorial of %d is = %d\n",number,fact(number));
}
int fact (long number)
{
	if (number==1)return 1;
	return (number*fact(number-1));
}
int get_number ()
{
	int number =0;
	printf("\n\n\n");
	scanf ("%d",&number,printf("\t\t\t\t\tENTER A NUMBER : "));
	return number;
}
void prime_or_not ()
{
	int number=0;
	do
	{
		number = get_number();
		if (number<=1)
		printf("\t\t\t\t\tGIVE A VALID INPUT , TRY AGAIN \n\n");
	}while(number <=1);
	number = prime(number);
	if ( number == 2)
	printf("\t\t\t\t\tNUMBER IS = NOT PRIME\n\n\n");
	else
	printf("\t\t\t\t\tNUMBER IS = PRIME\n\n");
}
int prime (int number )
{
	int root_number,i;
	root_number = sqrt ( number );
	for ( i=2;i<=root_number;i++)
	{
		if(number %i==0)
		return 2;
	}
	return 3;
}
void odd_or_even ()
{
	int number =0;
	number = get_number();
	if(number%2==0)
	printf("\t\t\t\t\tNUMBER IS : EVEN \n\n\n\n\n\n\n\n");
	else 
	printf("\t\t\t\t\tNUMBER IS : ODD  \n\n\n\n\n\n\n\n");
}
	













































		
