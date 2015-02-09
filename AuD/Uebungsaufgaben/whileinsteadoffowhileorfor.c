#include <stdio.h>

void forloop (){
	for (int i = 0; i < 5; i++)
	{
		printf("%d\n", i);
	}
}

void dowhileloop(int i) {
	do
	{
		printf("%d\n", i);
		i++;
	} while (i < 5);
}

void whileloop (int i) {
	while (i < 5) {
		printf("%d\n", i);
		i++;
	}
}

int main(int argc, char const *argv[])
{
	forloop();
	dowhileloop(0);
	whileloop(0);
	return 0;
}