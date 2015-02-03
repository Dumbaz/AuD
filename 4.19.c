#include <stdio.h>

int a;

void g(int *j, int k)
{
	if (*j + k < 6)
	{
		*j = *j + 1;
		g(j, k);
	}
}

void f(int m, int *d)
{
	int i;
	i = 2;

	while (m != 1)
	{
	g(&i, m);
	m = m / i;
	*d = *d + 1;
	}
}

int main ()
{
	int n, c;
	scanf("%i", &a);
	n = a;
	c = 0;
	f(n, &c);
	return 0;
}
