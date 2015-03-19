#include <stdio.h>
#include "fakultaet.h"

int main(int argc, char const *argv[])
{
	fakultaet(5);
	return 0;
}

/*

Für n, k ∈ 􏰂0 mit k ≤ n ist der Binomialkoeffizient b(n, k) definiert durch b(n,k):=􏰀n􏰁= n! .
k k!·(n−k)!
Schreiben Sie ein C–Programm, das Binomialkoeffizienten berechnet. Überlegen Sie sich Pro- blemlösungen, die es erlauben, dass Ihr C–Programm möglichst große Zahleneingaben korrekt verarbeiten kann.

*/