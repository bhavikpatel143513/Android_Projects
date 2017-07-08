// SUM OF SERIES  (1/1!) + (2/2!) + (3/3!) +.........+ ( n/n!)
#include <stdio.h>
int fact ( int i );
int main ()
{
	int i,a;float j=0;
	scanf("%d",&a,printf("ENTER A NUMBER "));
	for ( i = 1 ; i<=a;i++)
	{
		j=j+(float)i/fact(i);
	}
	printf ( " %f ", j );
	return 0;
}
int fact ( int i )
{
	int f=1,z;
	for ( z = 2 ; z <= i ; z++ )
	{
		f=f*z;
	}
	return f ;
}
