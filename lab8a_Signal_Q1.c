#include<stdio.h>
#include<stdlib.h>
#include<signal.h>

//64050538 ประณต คำวงษา
//Q1 2 power n:
//
//Received a SIGURS1. The max n is 20! = 2432902008176640000

signed long prev_computed, i;

void SIGhandler(int sig){
    printf("\nReceived a ");
    printf("SIGURS1. The max n is ");
    printf("%ld! = %ld\n", i-1, prev_computed);
    exit(0);
}

int main(){
    signed long cur_value;
    printf("2 power n:\n");
    signal(SIGUSR1, SIGhandler);
    prev_computed = 1;
    for(i = 1; ; i++){
        cur_value = prev_computed * i;
        if(cur_value < prev_computed){
            raise(SIGUSR1);
        }
        prev_computed = cur_value;
    }

    return 0;
}