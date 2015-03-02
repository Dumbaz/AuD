#include "../h/main.h"

void geschwindigkeitsetzen(int i){ //Setzt beide Motoren auf eine Geschwindigkeit fest (-100 rueckwaerts bis 100 vorwaerts)
	nxt_motor_set_speed(NXT_PORT_A,i,0);
	nxt_motor_set_speed(NXT_PORT_C,i,0);
}
