q = 1;
for (i = 0; i < n-1; i = i+1)
{
    x = a[i+1];
    j = i;
    while ((j >= 0) && (a[j] > x))
    {
        a[j+q] = a[j];
        j = j-q;
    }
    a[j+q] = x;
}
        
