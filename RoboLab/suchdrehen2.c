#include "../h/main.h"

int suchdrehen2(int degree){
	int lineFound = 0;

	ecrobot_status_monitor("Suche Linie");
	reset_count();

	while(nxt_motor_get_count(NXT_PORT_C) < degree * 2.8) {
		if(ecrobot_get_light_sensor(NXT_PORT_S3) > grenzwert) {
//			ecrobot_sound_tone(1000,50,50);
			lineFound++;
		}

		nxt_motor_set_speed(NXT_PORT_A,-80,0);
		nxt_motor_set_speed(NXT_PORT_C,80,0);
	}

	if(lineFound > 5) {
		return 1;
	} else {
		return 0;
	}

//	while (1){
//		if (i > 2){
//			return 0;
//		}
//	if (ecrobot_get_light_sensor(NXT_PORT_S3) > grenzwert){
//			ecrobot_status_monitor("Linie gefunden");
//			ecrobot_sound_tone(1000,50,50);
//			hardstop();
//			lineFound = 1;
//			break;
//		}
//	reset_count();
//	while((ecrobot_get_light_sensor(NXT_PORT_S3) < grenzwert) && ((nxt_motor_get_count(NXT_PORT_A) > -30 * 3.295) && (nxt_motor_get_count(NXT_PORT_C) < 30 * 3.295))){
//		nxt_motor_set_speed(NXT_PORT_A,-80,0);
//		nxt_motor_set_speed(NXT_PORT_C,80,0);
//	}
//	if(ecrobot_get_light_sensor(NXT_PORT_S3) > grenzwert) {
//		ecrobot_status_monitor("Linie gefunden");
//		ecrobot_sound_tone(1000,50,50);
//		lineFound = 1;
//		break;
//	}
//
//	reset_count();
//	while((ecrobot_get_light_sensor(NXT_PORT_S3) < grenzwert) && ((nxt_motor_get_count(NXT_PORT_A) < 60 * 3.295) && (nxt_motor_get_count(NXT_PORT_C) > -60 * 3.295))){
//		nxt_motor_set_speed(NXT_PORT_A,80,0);
//		nxt_motor_set_speed(NXT_PORT_C,-80,0);
//	}
//	if(ecrobot_get_light_sensor(NXT_PORT_S3) > grenzwert) {
//		ecrobot_status_monitor("Linie gefunden");
//		ecrobot_sound_tone(1000,50,50);
//		lineFound = 1;
//		break;
//	}
//
//	if (ecrobot_get_light_sensor(NXT_PORT_S3) < grenzwert){
//		drehen(30);
//		break;
//	}
//	i++;
//	}
}
