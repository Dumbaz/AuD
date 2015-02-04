/* Header-File pushdown.h */

typedef struct ele *pushdown;

void CreatePushdown(pushdown *s);
void Push(pushdown *s, int x);
void Pop(pushdown *s, int *x);

int Empty(pushdown s);
