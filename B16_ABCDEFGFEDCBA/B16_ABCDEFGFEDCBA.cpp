#include <stdio.h>
int main ()
{
	int t=1,z=0,zz=0;
	for (zz=7;zz>=1;zz--)
	{
		for ( z=65;z<65+zz;z++)
		{
			printf ("%3c",z);
		}
		for ( z=1;z<=(7-zz)*2-1;z++)
		{
			printf ("%3c",32);
		}
		for ( z=64+zz;z>=65;z--)
		{
			if ( z!=71)
			printf ("%3c",z);
		}
		printf ("\n");
	}
	return 0;
}
