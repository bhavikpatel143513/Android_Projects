// MULTIPLICATION TABLE 
#include <stdio.h>
int main()
{
	int n,z,to;
	scanf ("%d %d",&n,&to,printf("ENTER A NUMBER AND LENGTH TO OUTPUT TABLE "));
	for (z=1;z<=to;z++)
	{
		printf ( "\n %4d * %2d = %5d ",n,z,n*z);
	}
	return 0;
}
