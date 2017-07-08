#include<stdio.h>
#include<math.h>
int main(){
    int a , b, c  , flag = 0;
    float d;
    printf ("Enter a , b, c : ");
    scanf ("%d %d %d",&a,&b,&c);
    if ( a == 0 ){
        printf("x = %f",-1.0*c/b);
        return 0;
    }
    if ( b*b-4*a*c < 0 ){
            flag = 1;
        printf ("Has imaginary roots\n");
        d = sqrt(4*a*c - b*b);
    }
    else
        d = sqrt(b*b - 4*a*c);
    if ( flag == 0 ){
        printf("x = %f\n",1.0*(b + d)/(2*a));
        printf("x = %f\n",1.0*(b - d)/(2*a));
    }
    if ( flag != 0 ){
        printf("x = %f + %f i\n",1.0*b/(2*a),1.0*d/(2*a));
        printf("x = %f - %f i\n",1.0*b/(2*a),1.0*d/(2*a));
    }
    return 0;
}
