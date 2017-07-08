#include <stdio.h>
#include <math.h>
int main ()
{
	int i,j,k,l;
	int lim,lim_i,lim_j;
	printf("enter the limit: ");
	scanf ("%d",& lim);
	lim_i=cbrt(lim);
	lim_j=cbrt(lim-(lim_i*lim_i*lim_i));
	printf("%d",lim_i);
	for (i=1;i<=lim_i;i++)
	for (j=i;j<=cbrt(lim-(i*i*i));j++)
	for (k=i+1;k<=lim_i;k++)
	for (l=k;l<=cbrt(lim-(i*i*i));l++)
	{
		if ( i!=k&&i!=l&&j!=k&&j!=l)
		if ( i*i*i+j*j*j==k*k*k+l*l*l)
		printf("NUMBER = %8d = %3d * %3d = %3d * %3d \n",i*i*i+j*j*j,i,j,k,l);
	}return 0;
}
		
	
