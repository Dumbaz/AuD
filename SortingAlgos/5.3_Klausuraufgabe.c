void tausch(int *x, int *y) {
    int hilf;
    hilf = *x; /* da der Inhalt von *x ueberschrieben wird, muss er zunaechst gesichert werden */
    *X = *y;
    *y = hilf;
}

void BubbleSort(int a[], int n) {
    /* n ist die Laenge des eingegebenen Feldes */
    int i, j, getauscht;
    i = n -1;
    getauscht = 1;
    while (( i > 0) && getauscht) {
        getauscht = 0;
        for ( j = 1; j <= i; j++)
            if (a[j-1] > a[j] ) {
                tausch(&a[j-1], &a[j]);
                getauscht = 1;
            }
        i--;
    }
}    
