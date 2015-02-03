#include <stdio.h>

void h(int *x, int y)
{
    /* label 1 */
    if (*x > y)
        h(x, *x); /* $1 */
    else
        *x = *x - y;
    /* label 2 */
}

void g(int *a)
{
    int b;
    /* label 3 */
    b = *a + 1;
    while (b >= 0)
    {
        h(&b, *a); /* $2 */
        *a = b + 1;
        /* label 4 */
    }
}

int main()
{
    int z;
    scanf("%i", &z);
    /* label 5 */
    g(&z); /* $3 */
    /* label 6 */
    printf("%d", z);
    return 0;
}
