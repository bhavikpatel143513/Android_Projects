
#include<stdio.h>
#include"search.h"
int main()
{
	int n,i,s;
	printf("enter no. of terms u want to enter \n");
	scanf("%d",&n);
	int a[n];
	printf("enter terms of array \n");
	for( i=0;i<n;i++)
	{
		
	    scanf("%d",&a[i]); 
	}
	printf("enter the term u want to search \n");
	scanf("%d",&s);
	
	search(a,n,s);
	return 0;
	
}

