// MAXIMAM OF FOUR VALUES
#include <stdio.h>
int main()
{
	int a,s,d,f;
	scanf("%d %d %d %d",&a,&s,&d,&f,printf("ENTER FOUR NUMBERS : "));
	int max=a;
	if (s>max)
	max=s;
	if (d>max)
	max=d;
	if (f>=max)
	max=f;
	printf ("MAXIMUN NUMBER IS : %d ",max);
	return 0;
}
