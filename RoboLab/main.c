void geschwindigkeitsetzen(int i)
{
    nxt_motor_set_speed(NXT_PORT_A,i,0);
    nxt_motor_set_speed(NXT_PORT_C,i,0);
}

void fahren()
{
    while (nxt_motor_get_count(NXT_PORT_C) == nxt_motor_get_count(NXT_PORT_A))
    {
        geschwindigkeitsetzen(80);
    }
    if (nxt_motor_get_count(NXT_PORT_C) > nxt_motor_get_count(NXT_PORT_A))
    {
        nxt_motor_set_speed(NXT_PORT_C,80,0);
        nxt_motor_set_speed(NXT_PORT_A,85,0);
    }
    if (nxt_motor_get_count(NXT_PORT_C) < nxt_motor_get_count(NXT_PORT_A))
    {
        nxt_motor_set_speed(NXT_PORT_C,85,0);
        nxt_motor_set_speed(NXT_PORT_A,80,0);
    }
}

void reset_count()
{
    nxt_motor_set_count(NXT_PORT_C,0);
    nxt_motor_set_count(NXT_PORT_A,0);
}

void stop()
{
    geschwindigkeitsetzen(0);
}

void hardstop()
{
    nxt_motor_set_speed(NXT_PORT_A,0,1);
    nxt_motor_set_speed(NXT_PORT_C,0,1);
}

void checktouch()
{
    if (ecrobot_get_touch_sensor(NXT_PORT_S1) == 1 || ecrobot_get_touch_sensor(NXT_PORT_S4 == 1))
    {
        hardstop();
        ecrobot_sound_tone(1500,1000,50);
        systick_wait_ms(1000*10);
    }
}


