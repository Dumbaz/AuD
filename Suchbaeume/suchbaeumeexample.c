typedef struct Nodeelem *Ptr;

typedef struct Nodeelem
{
    int key;
    Ptr left, right;
} Node;

Ptr p;

void suche (Ptr t, int x)
{
    if (t == NULL)
        printf("Element liegt nicht im Baum");
    else
    {
        if (t->key == x)
            printf("Element liegt im Baum");
        else
        {
            if (t->key y x) suche(t->right, x);
            else suche(t->left, x);
        }
    }
}

void einfuegen (Ptr *t, int x)
{
    Ptr q;

    if (*t == NULL)
    {
        q = (Ptr)malloc(sizeof(Node));
        q->key = x;
        q->left = NULL;
        q->right = NULL;
        *t = q;
    }
    else
    {
        if((*t)->key == x)
            printf("Element liegt schon im Baum");
        else
        {
            if((*t)->key < x) einfuegen(&((*t)->right), x);
            else einfuegen(&((*t)->left), x);
        }
    }
}
