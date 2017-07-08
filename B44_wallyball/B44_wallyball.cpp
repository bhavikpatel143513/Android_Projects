#include<stdio.h>
#include <pthread.h>
#include <time.h>
#include <unistd.h>
#include <stdlib.h>
#include <conio.h>
void *myThreadFun1(void *vargp);


void gap();
void fil();
void blank();
void full_line();
void next_line();
void set_ground();
void frame();
void ball();
void player();
void mkplayer(int,int,int);
void rmplayer(int,int);
void mvplayer(int, int, int, int);
const int L=162,B=40;
int ground[40][160]={0}, player_1[3][6],player_2[3][6],P1b,P1l,P2b,P2l;





int main(){
    set_ground();
    frame();
    pthread_t tid1;
    pthread_create(&tid1, NULL, myThreadFun1, NULL);
    pthread_join(tid1, NULL);
    return 0;
}

void *myThreadFun1(void *vargp)
{
	char key;
	int dir,id;
	while(P1l!=78&&P1l!=-1){
    key = getch();
    if(key=='d'){
    	dir=1;
    	id=1;
	}
    if(key=='a'){
    	dir=-1;
    	id = 1;
	}
	if(key=='6'){
		dir = 1;
		id = 2;
	}
    if(key=='4'){
    	dir = -1;
    	id = 2;
	}
    if ( id == 1 ){
    	mvplayer( P1l,P1b,id,dir);
	}
	if ( id == 2 ){
		mvplayer( P2l,P2b,id,dir);
	}
    system("cls");
    frame();
	}
	return NULL;
}


void mvplayer(int l, int b, int id,int dir){
	rmplayer(l,b);
	if(id==1)P1l=l+dir*2;
	if(id==2)P2l=l+dir*2;
	mkplayer(l+dir*2,b,id);
}


void rmplayer(int l, int b){
	b -= 1;
    l -= 1;
    int x,y;
    for(y=0;y<3;y++)
        for(x=0;x<3;x++) ground[b+y][l+x]=0;
}
void mkplayer(int l ,int b, int id){
    b -= 1;
    l -= 1;
    int x,y;
    for(y=0;y<3;y++)
        for(x=0;x<3;x++) ground[b+y][l+x]=id;
}


void ball(){
    printf("%c",79);
}


void player(){
    printf("%c",-80);
}


void set_ground(){
    int l,b;
    for(b=24;b<40;b++){
        ground[b][80]=4;
        ground[b][81]=4;
    }
    ground[28][39]=3;
    mkplayer(39,38,1);P1l=39;P1b=38;
    mkplayer(121,38,2);P2l=121;P2b=38;
}


void frame(){
    int l,b;
    full_line();
    for (b=0;b<B;b++){
        gap();fil();
        for(l=0;l<L;l++){
            if(ground[b][l]==0) blank();
            if(ground[b][l]==1) player();
            if(ground[b][l]==2) player();
            if(ground[b][l]==3) ball();
            if(ground[b][l]==4) fil();
        }
        fil();next_line();
    }
    full_line();
}


void fil(){
    printf("%c",-37);
}


void blank(){
    printf(" ");
}


void gap(){
    printf("  ");
}


void next_line(){
    printf("\n");
}


void full_line(){
    int l;
    gap();fil();
    for (l=0;l<L;l++){
        printf("%c",-37);
    }
    fil();next_line();
}

