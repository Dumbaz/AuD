#include <stdio.h>

int a;

void g(int *j, int k)
{
    /* label 1 */
    if(*j + k < 6)
    {
        *j = *j + 1;
        g(j, k); /* $1 */
        /*label 2 */
    }
    /* label 3 */
}

void f(int m, int *d)
{
    int i;
    i = 2;
    /* label 4 */
    while (m != 1)
    {
        g(&i, m); /* $2 */
        m = m / i;
        *d = *d + 1;
        /* label 5 */
    }
}

int main()
{
    int n, c;
    scanf("%i", &a);
    n = a;
    c = 0;
    /* label 6 */
    f(n, &c); /* $3 */
    /* label 7 */
    return 0;
}

/* Gueltigkeitsbereiche

   int a, globale Variable, gueltig Zeile 3 bis 41
   
   
   int *j, Pointer, gueltig Zeile 5 bis 15
   int k, gueltig Zeile 5 bis 15
   
   int m, gueltig Zeile 17-29
   int *d, Pointer, gueltig Zeile 17-29
   int i, gueltig Zeile 19-29

   int n, gueltig Zeile 33-41
   int c, gueltig Zeile 33-41

*/
