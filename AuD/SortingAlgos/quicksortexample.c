void quicksort(int a[], int L, int R)
{
  int i, j, w, x, k;

  i = L; j = R; k = (L+R) / 2;
  x = a[k];

  do
  {
      while (a[i] < x) i = i+1;
      while (a[j] > x) j = j-1;
      if(i <= j)
      {
          w = a[i];
          a[i] = a[j];
          a[j] = w;
          i = i+1; j = j-1;
      }
  }
  while ( i <= j);

  if (L < j) quicksort(a, L, j);
  if (R > i) quicksort(a, i, R);
}
