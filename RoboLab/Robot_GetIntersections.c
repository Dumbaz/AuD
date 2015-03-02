#include "../h/main.h"

int Robot_GetIntersections(){
	ecrobot_sound_tone(1000,50,50);

//	short LinieIstHinten = 0;
//	short LinieIstRechts = 0;
//	short LinieIstLinks = 0;
//	short LinieIstVorne = 0;
	short relPos[4] = {0,0,0,0}; //Relative Position
	display_clear(1);

//	reset_count();
//	do{
//		fahren();
//	}while (nxt_motor_get_count(NXT_PORT_A) < 187 && nxt_motor_get_count(NXT_PORT_C) < 187);
//	hardstop();

	//LinieIstHinten = 32;

	//Kreuzung scannen
	// 0..45
	relPos[0] = suchdrehen2(45);
//	ecrobot_sound_tone(2000,50,50);
	// 45..135
	relPos[1] = suchdrehen2(90);
//	ecrobot_sound_tone(2000,50,50);
	// 135..225
	relPos[2] = suchdrehen2(90);
//	ecrobot_sound_tone(2000,50,50);
	// 225..315
	relPos[3] = suchdrehen2(90);
//	ecrobot_sound_tone(2000,50,50);
	// 315..360
	int isTopSet = suchdrehen2(45);
	relPos[0] = relPos[0] || isTopSet;
//	ecrobot_sound_tone(2000,50,50);

	hardstop();

	display_clear(1);

	short ReturnWert = 0;
	short i = 0;
	short test[7] = {16,128,32,64,16,128,32};
	for (i = 0; i < 4; i++){
		ReturnWert = ReturnWert + relPos[i] * test[i+Ausrichtung];
	}
	display_clear(1);
	display_goto_xy(0,0);
	display_int(ReturnWert, 6);
	display_goto_xy(6,0);
	display_int(Ausrichtung, 5);
	display_goto_xy(0,2);
	if(relPos[0] == 1){
		display_string("Vorne");
	}
//	display_int(relPos[0], 5); //Kreuzung in Blickrichtung
	display_goto_xy(0,4);
	if(relPos[1] == 1){
		display_string("Rechts");
	}
//	display_int(relPos[1], 5); //Kreuzung in Blickrichtung Rechts
	display_goto_xy(0,6);
	if(relPos[3] == 1){
		display_string("Links");
	}
//	display_int(relPos[3], 5); //Kreuzung in Blickrichtung Links
	display_update();
	systick_wait_ms(100);
	return ReturnWert;
}
