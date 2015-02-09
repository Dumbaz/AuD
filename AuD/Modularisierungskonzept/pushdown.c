/* Implementierungsmodul pushdown.c */

#include <stdlib.h>
#include "pushdown.h"

typedef struct ele { int key;
    pushdown next;} element;

void CreatePushdown(pushdown *s)
{
    *s = NULL;
}

void Push(pushdown *s, int x)
{
    pushdown top;

    top = (pushdown) malloc(sizeof(element))
    top->key = x;
    top->next = *s;
    *s = top;
}

void Pop(pushdown *s, int *x)
{
    pushdown top;

    top = *s;
    *x = (*s)->key;
    *s = (*s)->next;
    free(top);
}

int Empty(pushdown s)
{
    return s==NULL;
}
