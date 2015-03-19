#include <stdio.h>

int splitter(int betrag) {
	switch(betrag/500) {
		case 0: break;
		default:	printf("%d mal 500\n", betrag/500);
					betrag = betrag - ((betrag/500)*500);
	}
	switch(betrag/200) {
		case 0: break;
		default:	printf("%d mal 200\n", betrag/200);
					betrag = betrag - ((betrag/200)*200);
	}
	switch(betrag/100) {
		case 0: break;
		default:	printf("%d mal 100\n", betrag/100);
					betrag = betrag - ((betrag/100)*100);
	}
	switch(betrag/50) {
		case 0: break;
		default:	printf("%d mal 50\n", betrag/50);
					betrag = betrag - ((betrag/50)*50);
	}
	switch(betrag/20) {
		case 0: break;
		default:	printf("%d mal 20\n", betrag/20);
					betrag = betrag - ((betrag/20)*20);
	}
	switch(betrag/10) {
		case 0: break;
		default:	printf("%d mal 10\n", betrag/10);
					betrag = betrag - ((betrag/10)*10);
	}
	switch(betrag/5) {
		case 0: break;
		default:	printf("%d mal 5\n", betrag/5);
					betrag = betrag - ((betrag/5)*5);
	}
	switch(betrag/2) {
		case 0: break;
		default:	printf("%d mal 2\n", betrag/2);
					betrag = betrag - ((betrag/2)*2);
	}
	switch(betrag/1) {
		case 0: break;
		default:	printf("%d mal 1\n", betrag/1);
					betrag = betrag - ((betrag/1)*1);
	}
} 


	int main(int argc, char const *argv[]) {
	int eingabe = 21368;
	printf("Bitte einen Betrag eingeben\n");
	scanf("%d", &eingabe);
	printf("%d\n", splitter(eingabe));
	return 0;
}


/*Übung 4 (AGS 3.2)
Ein ganzzahliger Geldbetrag in Euro soll in die Grundeinheiten 500, 200, 100, 50, 20, 10, 5, 2 und 1 zerlegt werden.
Schreiben Sie ein C–Programm, welches einen Geldbetrag als Eingabe fordert, dann die Viel- fachheiten der oben genannten Grundeinheiten berechnet und das Ergebnis auf dem Bildschirm ausgibt. Entwickeln Sie einen solchen Algorithmus, der ein Minimum an Geldscheinen und Mün- zen erzeugt.
Geben Sie zunächst den Algorithmus in Pseudocode an.
*/