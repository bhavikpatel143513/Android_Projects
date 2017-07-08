#include<stdio.h>
int main(){
    int n, i,j;
    scanf("%d",&n,printf ("Enter an num  : "));

    for ( i=1 ; i <= n ; i++ ){
        printf ("     ");
        for ( j =0 ; j < n-i ; j++ ){
            printf (" ");
        }
        for ( j =1 ; j <= i ; j++ ){
            printf ("* ");
        }
        printf ("\n");
    }
    return 0;
}
