#include <stdio.h>
int main ()
{
	int y;
	float x;
	printf("| X\\Y |");
	for(y=1;y<=6;y++)
	{
		printf("%5d|",y);
	}
	printf ("\n");
	for ( x=5.5;x<=12.5;x+=.5)
	{
		printf ("|%5.2f|",x);
		for(y=1;y<=6;y++)
		{
			printf("%5.2f|",2+(y+.5*x));
		}
		printf("\n");
	}
	return 0;
}
	
