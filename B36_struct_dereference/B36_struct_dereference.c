#include <stdio.h>
#include <conio.h>

struct Abc
{
	struct Abc *ptr;
	int num;
	char c;
	//struct Abc *next;
};

int main()
{
	struct Abc *a;int size=sizeof(char);
	struct Abc b;b.c='p';b.num=9;
//	int *xy;
//	xy= (int*)malloc(sizeof(int));
//	*xy=10;
	a=&b;
	a= (struct Abc*)malloc(sizeof(struct Abc));
	//scanf("%d",&(a->num));
//	*a.c='t';
	
	(*a).c='p';
	(*a).num=65;
//	scanf("%d",&(a.num));
	printf("%d\n",size);
	printf("%d\n",b);
	printf("%d\n",a->num);
	return 1;
}
	
