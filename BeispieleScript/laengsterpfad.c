/*laengster Pfad */

typedef struct nodeelem *BPtr;
typedef struct nodeelem {
    int key;
    BPtr left, right;
    ... data;
} node;

int hoehe (BPtr wz)
{
    int h1, h2;
    /* l1 */
    if (wz == NULL) return 0;
    h1 = hoehe(wz->left); /* $ 1 */
     /* l2 */
    h2 = hoehe(wz->right); /* $ 2 */
     /* l3 */
    return max(h1, h2)+1;
}

void eingabe(BPtr *wz)
{}

int main()
{
    int h; BPtr w;
    /* l4 */
    eingabe(&w);
    j = hoehe(w); /* $ 3 */
    /* l5 */
    ...
}
