#include <stdio.h>
#include <math.h>
float sum (int n,float x);
int main ()
{
	float x;
	scanf("%f",&x,printf("ENTER A NUMBER TO FIND IT'S NATURAL LOG : "));
	printf("%f",sum ( 7,x));
	return 0;
}
float sum (int n,float x)
{
	if (n==1)
	return ((x-1)/x);
	return ( .5*pow((x-1)/x,n) + sum (n-1,x));
}
