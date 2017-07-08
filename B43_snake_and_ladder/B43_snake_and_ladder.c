#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<conio.h>
#include<unistd.h>
#include<time.h>
void showgame ( );
void getplayer ();
int dice ();
void intro () ;
void move (int i , int *name_no,int dice_no,int name_no_list[],int nop);
int turn(int i,char name[][4]);
int sandl[101] ;
int table [101];

int main (){




    table [0] = 0 ;
    int z = 0 ;
    for ( z = 1 ; z < 101 ; z++ ) {
        table [z]  = z ;
    }
    table [1] = 38;
    table [4] = 14;
    table [9] = 31;
    table [17] = 7;
    table [21] = 42;
    table [28] = 84;
    table [51] = 67;
    table [54] = 34;
    table [62] = 19;
    table [64] = 60;
    table [71] = 91;
    table [80] = 100;
    table [87] = 24;
    table [93] = 73;
    table [95] = 75;
    table [98] = 79;



    sandl[0] = 0 ;
    int i,j,k;
    i=j=k=0;
    for ( i = 90 ; i > 0 ; i = i - 20 ,j = j-20 ){
        for ( j = i+10  ; j > i-10 ; j-- ) {
            if  ( j > i ) {
                sandl[j] = j ;
            }
            else {
                    k = 2*i-9 ;
                sandl[j] = k- j ;
            }
        }
    }
    sandl[10] = 38 ;
    sandl[7] = 14 ;
    sandl[2] = 31 ;
    sandl[17] = 7 ;
    sandl[30] = 42 ;
    sandl[23] = 84 ;
    sandl[51] = 67 ;
    sandl[54] = 34 ;
    sandl[69] = 19 ;
    sandl[67] = 60;
    sandl[71] = 91;
    sandl[80] = 100 ;
    sandl[84] = 24;
    sandl[93] = 73 ;
    sandl[95] = 75 ;
    sandl[98] = 79 ;






    int win = 0 ;
    intro ();
    int nop ;
    printf ("ENTER NUMBER OF PLAYERS = " );
    scanf ( "%d",&nop );
    int name_no [nop];
    for ( z = 0 ; z < nop ; z++ ){
        name_no [z] = 0 ;
    }
    char name[nop][4];
    getplayer (&nop,name_no,name) ;
    for (i = 0 ;win == 0;i++,i=i%nop){
        showgame (name);
        int dice_no = 0 ;
        dice_no = turn(i,name);
        move (i,&name_no[i],dice_no,name_no,nop);
        if ( table [name_no[i]] == 100 ) win = i+1 ;
        getch () ;
        system ("cls");
    }
    system ( "cls" );
    printf ( " ********************** %s WINS THE GAME ",name[i]);
    return 0;
}

int converter ( int in ){
    if ( in == 0 )
        return 0 ;
    else if ( ( in - 1 )/10 % 2 == 0 )
    {
        return ((in-1) /10 )*20+11-in;
    }
    else
    {
        return in;
    }
}

int check ( int i ,int name_no_list[],int nop)  {
    int z = 0 ;
    for ( z = 0 ; z < nop ; z++ ){
        if ( z != i ){
           if ( name_no_list[z] == name_no_list[i] )
            return z ;
           else return nop+100;
        }
    }
    return nop+100;
}

void move (int i , int *name_no,int dice_no,int name_no_list[],int nop){
    int out = converter ( *name_no );
    //printf (" name_no = %d \n out = %d \n",*name_no,out);
    int z = check (i,name_no_list,nop);
    if ( z< nop ){
           // printf ("&&&&&&&&&&&&&&&&&&&&&&&&&  IN CHECK  &&&&&&&&&&&&&&&&&&&&&");
        sandl [out] = 101+z;
    }
    else sandl[out] = *name_no ;
    if (dice_no + table[*name_no] <=100 ){
        *name_no = dice_no + table[*name_no] ;
    }else printf ("Cannot move.......try in next chance !!");
    *name_no = table [*name_no];
    out = converter ( table[*name_no] );
    sandl[out]  = 101+i;
    //printf (" name_no = %d \n out = %d \n",*name_no,out);
}

int turn(int i,char name[][4]){
    int dice_no = dice () ;
    printf (" TURN OF %s ********** ENTER A KRY TO THROW DICE \n",name+i);
    getch();
    printf (" DICE = %d\n",dice_no);
    return dice_no ;
}

int dice (){
    srand ( time ( NULL));
    int dice_no = rand()%6+1;
}
void showgame ( char name[][4])
{
    int i = 0 ;
    for ( i = 10 ; i >=1 ; i-- ) {
        line ();
        block_up_down();
        block (i,name);
        block_up_down();
    }
    line();
}


void line ()
{
    int i = 0 ;
    printf ("          ");
    for ( i = 1  ; i <= 72 ; i++ ){
        printf ( "%c",-37 );
    }
    printf ( "\n");
}

void block_up_down ( )
{
    int i = 0 ,j = 0 ;
    printf ("          ");
    for ( i = 1  ; i <= 10 ; i++ ){
        printf ("%c%c     ",-37,-37);
    }
    printf ("%c%c\n",-37,-37);
}

void block (int row, char name[][4] )
{
    row *= 10 ;
    int i = 0 ,j = 0 ;
    printf ("          ");
    for ( i = 1  ; i <= 10 ; i++ ){
            if ( sandl[row-i+1]> 100){
                printf ("%c%c %s ",-37,-37,name[sandl[row-i+1]-101]);
            }
            else {
                printf ("%c%c %3d ",-37,-37,sandl[row-i+1]);
            }
    }
    printf ("%c%c\n",-37,-37);
}

void getplayer (int *nop,int name_no[],char name[][4])
{
    char temp_name [20];
    int i = 1 ;
    for ( ; i<= *nop ; i++ ){
        printf ( "ENTER NAME OF PLAYERS %d ",i);
        scanf ( "%s",&temp_name);
        strncpy ( (name+i-1),&temp_name , 3 );
        *(*(name+i-1)+3) = '\0';
    }
}

void intro ()
{
    printf ( " ****************SNAKE AND LADDER****************\n");
    printf ( " press a key to begin !!\n ") ;
    getch ();
    system ( "cls" );
}
