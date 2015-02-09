#define K 100

/* lasse a[l] in a[l], a[l+1],...,a[r] hineinsinken */

void sinkenlassen(int a[], int l, int r)
{
    int i, j, h, loop;
    i = l;
    h = a[i];
    loop = 1;
    while(loop)
    {
        j = 2*i+1;
        if( j > r)
            break;

        if(j < r)
            if(a[j] < a[j+1])
                j = j+1;

        if( h > a[j])
            break;
        else
        {
            a[i] = a[j];
            i = j;
        }
    }
    a[i] = h;
}

void Heapsort(int a[], int n)
{
    int li, re, x;

    i = n DIV 2;
    re = n-1;
    while(li > 0)
    {
        li = li-1;
        sinkenlassen(a , li, re)
    }
    while(re > 0)
    {
        x = a[0];
        a[0] = a[re];
        a[re] = x;
        re = re-1;
        sinkenlassen(a, 0, re);
    }
}

int main()
{
    int a[K];
    /* Werte fuer a[0] bis a[K-1];
}
