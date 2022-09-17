#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>

int msum;
int csum;
void *runner(void *param);
int main(int argc, char*argv[]){
	pthread_t tid;
	pthread_attr_t  attr;
	pthread_attr_init(&attr);

	pthread_create(&tid, &attr, runner, argv[1]);

	pthread_join(tid, NULL);
	for(int i = 0 ; i <= atoi(argv[1]) ;i++){
		msum += i;
	}
	printf("Parent sum = %d\n" ,msum);
	printf("Child sum = %d\n", csum);
	printf("Different Parent & child = %d\n", msum - csum);
	return 0;
}

void *runner(void *param){
	int upper = atoi(param);
	int i;
	if(upper > 0){
		for(i = 0 ; i <= 2*upper; i++){
			csum += i;
		}
	}
	pthread_exit(0);
}

