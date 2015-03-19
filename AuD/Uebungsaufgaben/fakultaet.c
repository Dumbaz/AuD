#include "fakultaet.h"

#include <stdio.h> //Standardbibliothek für Ein- und Ausgabe


int fakultaet(int a) { //Funktion zum berechnen der Fakultät
	if (a == 0)
	{
		return 1;
	}
	else {
		return (a * fakultaet(a-1)); //Die Funktion ruft sich rekursiv selbst auf, bis die Abbruchbedingung erreicht ist
	}
}

int main(int argc, char const *argv[])
{
	int eingabe;
	printf("Bitte eine Zahl eingeben\n");
	scanf("%d", &eingabe); //Der Wert der Eingabe wird auf der Variable eingabe abgelegt
	printf("%d\n", fakultaet(eingabe));
	return 0;
}

/*
Übung 3 (AGS 3.5)
Schreiben Sie für die Berechnung der Fakultätsfunktion ein C–Programm, welches eine natürliche Zahl als Eingabe fordert und den zugeordneten Funktionswert ausgibt.
*/