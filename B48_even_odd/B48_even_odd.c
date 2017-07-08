# include <stdio.h>
int main(){
int n = 0;
printf ("Enter a integer : ");
scanf("%d",&n);
if ( n%2 == 0 ) {
    printf ("integer %d is even",n);
}
else{
    printf ("integer %d is odd",n);
}
return 0;
}
