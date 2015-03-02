#include "../h/main.h"

void stop(){ //Räder können ausrollen
	ecrobot_status_monitor("ANHALTEN");
	nxt_motor_set_speed(NXT_PORT_A,0,0);
	nxt_motor_set_speed(NXT_PORT_C,0,0);
}

void hardstop(){ //Bremsfunktion
	nxt_motor_set_speed(NXT_PORT_A,0,1);
	nxt_motor_set_speed(NXT_PORT_C,0,1);
			}
