#include <stdio.h>
struct alpha 
{
	char a ;
	char b ;
	char c ;
	int  d ;
//	int  a ;
//	int  b ;
//	int  c ;
//	int  d ;
//	int  e ;
	int *f ;
	char g;
	int h;
	int i;
	int j;
	char k;
	int * l;
};
int main ()
{
	struct alpha alpha1 ; 
//	alpha1.a = 1 ;
//	alpha1.b = 2 ;
//	alpha1.c = 3 ;
	char * ptr;
	ptr  =  (char*)&alpha1;
	alpha1.c = 'c';
	alpha1.d = 4;
	int size = sizeof ( alpha1 );
	printf ("size = %10d",size );
	printf ("\n&alpha1.a char= %10d",&alpha1.a);
	printf ("\n&alpha1.b char= %10d",&alpha1.b);
	printf ("\n&alpha1.c char= %10d",&alpha1.c);
	printf ("\n&alpha1.d int= %10d",&alpha1.d);
	printf ("\n&alpha1.f int*= %10d",&alpha1.f);
	printf ("\n&alpha1.g char= %10d",&alpha1.g);
	printf ("\n&alpha1.h int= %10d",&alpha1.h);
	printf ("\n&alpha1.i int= %10d",&alpha1.i);
	printf ("\n&alpha1.j int= %10d",&alpha1.j);
	printf ("\n&alpha1.k int= %10d",&alpha1.k);
	printf ("\n&alpha1.l int= %10d",&alpha1.l);
	printf ("\nalpha1.c char= %10c",alpha1.c);
	printf ("\nalpha1.d int= %10d",alpha1.d);
	ptr = ptr +2;
	printf ("\nalpha1.c char= %10c",*ptr);
	ptr = ptr + 2;
	printf ("\nalpha1.d int= %10d",*ptr);
	return 0;
}




/*
struct alpha 
{
	int  a ;
	int  b ;
	int  c ;
	int g;
	int h;
	int * d;
	int e;
	int  f;
	int i;
	int j;
	int k;
};
int main ()
{
	struct alpha alpha1 ; 
	alpha1.a = 1 ;
	alpha1.b = 2 ;
	alpha1.c = 3 ;
	alpha1.g = 4 ;
	alpha1.h = 5 ;
//	alpha1.c = 3 ;
	
	
	int size = sizeof ( alpha1 );
//	struct alpha * head;
	int * head;
	head  = &alpha1.h;
	head += 1; 
	*head = 6;
	printf ( "\n alpha1 = %d\n &alpha1.a = %d\n &alpha1.b = %d\n &alpha1.c = %d\n &alpha1.g = %d \n&alpha1.h = %d\n &alpha1.d = %d\n &alpha1.e = %d\n head+1 = %d\n size = %d " ,
	             alpha1 ,      &alpha1.a ,      &alpha1.b ,      &alpha1.c ,      &alpha1.g ,      &alpha1.h ,      &alpha1.d ,      &alpha1.e ,      head+1 ,      size      ) ;
	printf ( "\n alpha1 = %d\n alpha1.a = %d\n alpha1.b = %d\n alpha1.c = %d\n alpha1.g = %d \n alpha1.h = %d\n alpha1.d = %d\n alpha1.e = %d\n head+1 = %d\n size = %d " ,
	             alpha1 ,      alpha1.a ,      alpha1.b ,      alpha1.c ,      alpha1.g ,       alpha1.h ,      alpha1.d ,      alpha1.e ,      head+1 ,      size      ) ;
	printf ( "\n *head = %d " ,  *head);
	return 0 ;
}
/*
int main ()
{
	struct alpha alpha1[4] ; 
	alpha1[0].a = 'a' ;
	alpha1[0].b = 'b' ;
	alpha1[0].c = 'c' ;
	
	
	int size = sizeof ( alpha1 );
	struct alpha (* head) [4];
	head  = &alpha1;
	printf ( "\n alpha1 = %d\n &alpha1[0].a = %d\n &alpha1[0].b = %d\n &alpha1[0].c = %d\n head+1 = %d\n size = %d " ,
	             alpha1 ,      &alpha1[0].a ,      &alpha1[0].b ,      &alpha1[0].c ,      head+1 ,      size      ) ;
	
	return 0 ;
}
*/



