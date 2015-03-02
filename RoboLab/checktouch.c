#include "../h/main.h"

int checktouch(){ //Bei Berührung anhalten
			//Both Sensors need to trigger
	if (ecrobot_get_touch_sensor(NXT_PORT_S1) == 1 || ecrobot_get_touch_sensor(NXT_PORT_S4) == 1){

			hardstop();
							
			ecrobot_sound_tone(1500,1000,50);

			systick_wait_ms(1000*10); //10 Sekunden warten

			return 1;
	}

	return 0;
}
