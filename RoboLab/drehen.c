#include "../h/main.h"

void drehen(int g){ //Roboter um g Grad drehen, standard ist rechts herum
//	ecrobot_status_monitor("Am Drehen");
	reset_count();
	if(g > 0){ //Rechts herum
		do {
			nxt_motor_set_speed(NXT_PORT_C,70,0);
			nxt_motor_set_speed(NXT_PORT_A,-70,0);
		} while ((nxt_motor_get_count(NXT_PORT_C) < g * 3.28) && (nxt_motor_get_count(NXT_PORT_A) > g * -3.28));
		hardstop();
		}
	if (g < 0){ //links herum
		do{
			nxt_motor_set_speed(NXT_PORT_A,70,0);
			nxt_motor_set_speed(NXT_PORT_C,-70,0);
		} while ((nxt_motor_get_count(NXT_PORT_C) > g * 3.28) && (nxt_motor_get_count(NXT_PORT_A) < g * -3.28));
		hardstop();
		}
	systick_wait_ms(500);
}
