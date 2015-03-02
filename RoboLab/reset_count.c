#include "../h/main.h"

void reset_count(){ //Setzt Anzahl der gezählten Umdrehungen auf 0
		nxt_motor_set_count(NXT_PORT_C,0);
		nxt_motor_set_count(NXT_PORT_A,0);
		}
