#include <stdio.h>

int fakultaet(int a) {
	if (a == 0)
	{
		return 0;
	}
	else {
		return (a * fakultaet(a-1));
	}
}

int main(int argc, char const *argv[])
{
	int eingabe;
	printf("Bitte eine Zahl eingeben\n");
	scanf("%d", &eingabe);
	printf("%d\n", fakultaet(eingabe));
	return 0;
}