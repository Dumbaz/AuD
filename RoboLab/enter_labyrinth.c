#include "../h/main.h"

short enter_labyrinth(){
	short f = 0;
	int k = 0;
	while (1){ //fahren bleibt nach dem Aufruf immer aktiv
		int vorSuchenA, vorSuchenC; //Durch das drehen ändert sich der motor_get_count, zwischenspeichern

		if(checktouch() == 1){
			f = 2;
		}

		if (ecrobot_get_light_sensor(NXT_PORT_S3) < grenzwert){
			hardstop();
			k = 0;
			vorSuchenA = nxt_motor_get_count(NXT_PORT_A);
			vorSuchenC = nxt_motor_get_count(NXT_PORT_C);
			reset_count();

			if (k == 0){
				suchdrehen();
				k++;
			}

			nxt_motor_set_count(NXT_PORT_A,vorSuchenA + 5);
			nxt_motor_set_count(NXT_PORT_C,vorSuchenC + 5);
		}

		if ((k != 0) && (ecrobot_get_light_sensor(NXT_PORT_S3) < grenzwert)){
			reset_count();
			do{
				fahren();
			}while (nxt_motor_get_count(NXT_PORT_A) < 187 && nxt_motor_get_count(NXT_PORT_C) < 187);
			hardstop();
			break;
		} else if (ecrobot_get_light_sensor(NXT_PORT_S3) > grenzwert){
			k = 0;
			fahren();
		}
	}

	return f;
	}
