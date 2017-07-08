#include<stdio.h>
#include<unistd.h>

int main()
{
	int t,t_sec,t_nano;
	struct timespec del;
	printf("enter time in ms");
	scanf("%d",&t);
	t_sec=t/1000;
	t_nano=(t-1000*t_sec)*1000*1000;
	del.tv_nsec=t_nano;
	del.tv_sec=t_sec;
	printf("%d secs and %d in nano",t_sec,t_nano);
	nanosleep(&del,NULL);
	printf("done");
	return 0;
}
