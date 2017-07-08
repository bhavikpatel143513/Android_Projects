#include <stdio.h>
int main ()
{
	struct team
	{
		char name[20];
		int age;
		team * next;
	};
	team t1={"bhavik",20};
	printf("%s %d ",t1.name,t1.age);
	team t2={"dhaval",22};t1.next=&t2;
	printf ("%s %d ",t2.name,t2.age);
	team t3={"nikhil",19};t2.next=&t3;
	printf ("%s %d ",t3.name,t3.age);
	return 0;
}
	
