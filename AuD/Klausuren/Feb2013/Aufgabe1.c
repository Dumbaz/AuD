/*
   typedef struct nodeTag *treeP;
   typedef struct nodeTag {
    int key;
    treeP left, right;
    } node;

    */

int ident(treeP t1, treeP t2)
{
    if (t1==NULL && t2==NULL)
    {
        return 1;
    }
    if (t1->key == t2->key)
    {
        int left = ident(t1->left, t2-left);
        int right= ident(t1->right, t2->right);
        return (left && right);
    }
    else
        return 0;
}
