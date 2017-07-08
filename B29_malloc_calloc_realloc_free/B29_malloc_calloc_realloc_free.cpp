#include <stdio.h>
#include <stdlib.h>
int main()
{
	int *number ;
	number= (int*)malloc ( 3*sizeof(int));
	*number=77;
	printf ("%d ",*number);
	number++;
//	*number = 88;   *number  not initialized
	printf ("%d ",*number );
	number++;
	*number=99;
	printf("%d \n",*number);
	

	
//	calloc	
	int *num;
	num= (int*)calloc(3,3*sizeof (int));// calloc initialiges all allocted bytes to zero
	*(num+0)=111;
	*(num+1)=122;
	*(num+2)=133;
	*(num+1*3+0)=211;
	*(num+2*3+2)=333;
	printf ("%d ",*(num+1*3+2));
	
	
	int (*numb)[3];
	numb= (int(*)[3])calloc(3,3*sizeof(int));
	**(numb+0)=5131;
	**(++numb+2)=5132;//equivalent to *(*(numb+1)+2)=5132;
	printf("%d %d",**(numb+0)=5131,**(++numb+2)=5132);
	
	
	
//	realloc
//	number=(int*)realloc(number,3*sizeof(int));


//	free
	free(number-=2);
	printf (" %d ",*number);
	
	
	return 0;
}
