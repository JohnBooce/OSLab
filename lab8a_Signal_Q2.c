//64050538 ประณต คำวงษา

#include<stdio.h>
#include<stdlib.h>
#include<signal.h>
#include<stdlib.h>
#include<unistd.h>

int main(void){
    pid_t pid = fork();
    if(pid == 0){
        printf("child created\n");
        while(1);
        printf("this line should not be shown\n");
        exit(0);
    }else{
        sleep(1);
        kill(pid, SIGINT);
    }

    printf("child terminated.\n");

    return 0;
}