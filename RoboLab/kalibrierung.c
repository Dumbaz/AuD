/*
 * kalibrierung.c
 *
 *  Created on: 12.03.2014
 *      Author: Eva Wittkemper
 */

#include "../h/main.h"

int kalibrierung(){
	int white, black, g;

	systick_wait_ms(1000);

	black = ecrobot_get_light_sensor(NXT_PORT_S3);

	drehen(30);
	white = ecrobot_get_light_sensor(NXT_PORT_S3);
	drehen(-30);

	g = (black + white)/2;

	display_clear(1);
	display_goto_xy(0,0);
	display_int(g, 5);
	display_update();
	systick_wait_ms(2000);

	return g;
}
