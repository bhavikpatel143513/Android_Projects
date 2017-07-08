/*#include <stdio.h>
int main ()
{
	int i=-128;
	while (i++<=127)
	{
		printf ("%d    %c\n\n",i,i);

	}
	return 0;
}*/
#include <stdio.h>
#include <pthread.h>
#include <time.h>
#include <unistd.h>
void *myThreadFun(void *vargp)
{
    sleep (3);
    printf("Printing GeeksQuiz from Thread \n");
    return NULL;
}

int main()
{
    pthread_t tid;
    printf("Before Thread\n");
    pthread_create(&tid, NULL, myThreadFun, NULL);
    
    printf("After Thread\n");
    sleep(5);
    return(0);
}
