#include "../h/main.h"

int Robot_Move(int x, int y){ //Neue Position
	//Werte vergleichen und ausrichten
	if (x < position[0]){
		//nach Norden drehen Ausrichtung = 0
			if (Ausrichtung == 1){
				drehen(-90);
			}
			if (Ausrichtung == 2){
				drehen(180);
			}
			if (Ausrichtung == 3){
				drehen(90);
			}
			Ausrichtung = 0;
			suchdrehen();
		}
	if (x > position[0]){
		//nach Süden drehen Ausrichtung = 2
			if (Ausrichtung == 0){
				drehen(180);
			}
			if (Ausrichtung == 1){
				drehen(90);
			}
			if (Ausrichtung == 3){
				drehen(-90);
			}
			Ausrichtung = 2;
			suchdrehen();
	}
	if (y > position[1]){
		//nach Osten drehen Ausrichtung = 1
			if (Ausrichtung == 0){
					drehen(90);
				}
				if (Ausrichtung == 2){
					drehen(-90);
				}
				if (Ausrichtung == 3){
					drehen(180);
				}
				Ausrichtung = 1;
				suchdrehen();
	}
	if (y < position[1]){
		//nach Westen drehen Ausrichtung = 3
			if (Ausrichtung == 0){
				drehen(-90);
			}
			if (Ausrichtung == 1){
				drehen(180);
			}
			if (Ausrichtung == 2){
				drehen(90);
			}
			Ausrichtung = 3;
			suchdrehen();
	}
	if (Liniefahren() == 1){
		return 2;
	}
	return 1;
	//return 0; //Move ist fehlgeschlagen
	}
