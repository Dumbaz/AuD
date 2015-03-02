#include "../h/main.h"

void fahren() { //fährt einfach gradeaus (mit leichten Korrekturen sollte ein Rad schneller laufen). Stop muss von anderswo aufgerufen werden!
//	ecrobot_status_monitor("Nicht mit Fahrer sprechen");
	while (nxt_motor_get_count(NXT_PORT_C) == nxt_motor_get_count(NXT_PORT_A)){
			geschwindigkeitsetzen(80);
		}
	if (nxt_motor_get_count(NXT_PORT_C)>nxt_motor_get_count(NXT_PORT_A)){
		nxt_motor_set_speed(NXT_PORT_C,80,0);
		nxt_motor_set_speed(NXT_PORT_A,85,0);
			}
	if (nxt_motor_get_count(NXT_PORT_C)<nxt_motor_get_count(NXT_PORT_A)){
		nxt_motor_set_speed(NXT_PORT_C,85,0);
		nxt_motor_set_speed(NXT_PORT_A,80,0);
			}
}
