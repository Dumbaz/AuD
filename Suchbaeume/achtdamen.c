#include <stdio.h>
#include <math.h>

short a[8];

void drucke {
    short i;
    for(i=0; i <= 7; i=i+1) printf("%2d", a[1]);
    printf("\n");
}

int konsistent(int t)
{ short j;

    for (j = 0; j < t; j=j+1)
    {   if (a[j] == a[t]) return 0;
        if (abs(a[j] - a[t]) == (t-j)) return 0;
    }
    return 1;
}

void suche (int t)
{   short i;
    if ( t == 8) drucke();
    else
        for(i = 0; i <= 7; i=i+1)
        {a[t] = 1;
            if (konsistent(t) suche(t+1);
                    }
                    }

int main()
{
suche(0);
return 0;
}
