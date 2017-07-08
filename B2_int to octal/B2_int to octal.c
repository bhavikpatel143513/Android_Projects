//find octal equivalent of an integer
#include<stdio.h>
#include<math.h>
int main ()
{
	int num,oct,p,rem,n=0;
	oct=num=p=rem=0;
	scanf("%d",&n);
	while(n>0)
	{
		rem=n%8;
		n/=8;
		oct=oct+rem*pow(10,p);
		p++;
	}
	printf ("\n%d",oct);
	return 0;
}
