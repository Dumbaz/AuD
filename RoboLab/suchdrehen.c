#include "../h/main.h"

void suchdrehen(){//Drehenfunktion mit Lichtsensor
//	ecrobot_status_monitor("Suche Linie");
	reset_count();
	while (1){
		if (ecrobot_get_light_sensor(NXT_PORT_S3) > grenzwert){
			hardstop();
			break;
		}
		reset_count();

		//Drehen Rechts 20 Grad
		while((ecrobot_get_light_sensor(NXT_PORT_S3) < grenzwert) && ((nxt_motor_get_count(NXT_PORT_A) > -30 * 3.295) && (nxt_motor_get_count(NXT_PORT_C) < 30 * 3.295))){
			nxt_motor_set_speed(NXT_PORT_A,-70,0);
			nxt_motor_set_speed(NXT_PORT_C,70,0);
			if (ecrobot_get_light_sensor(NXT_PORT_S3) > grenzwert){
				hardstop();
				break;
					}
		}
		if (ecrobot_get_light_sensor(NXT_PORT_S3) > grenzwert){
			hardstop();
			break;
		}
		hardstop();
		reset_count();

		//Drehen Links 40 Grad
		while((ecrobot_get_light_sensor(NXT_PORT_S3) < grenzwert) && ((nxt_motor_get_count(NXT_PORT_A) < 60 * 3.295) && (nxt_motor_get_count(NXT_PORT_C) > -60 * 3.295))){
			nxt_motor_set_speed(NXT_PORT_A,70,0);
			nxt_motor_set_speed(NXT_PORT_C,-70,0);
			if (ecrobot_get_light_sensor(NXT_PORT_S3) > grenzwert){
				hardstop();
				break;
				}
		}
		if (ecrobot_get_light_sensor(NXT_PORT_S3) > grenzwert){
			hardstop();
			break;
			}
		if (ecrobot_get_light_sensor(NXT_PORT_S3) < grenzwert){
			drehen(34);
		}
		hardstop();
		break;
		}
}
