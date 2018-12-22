package util;

import java.awt.event.ActionListener;
import java.util.Timer;

public class TimeUtil {

    public static void delay(int time){
        try {
            Thread.currentThread().sleep(time);
        }catch (InterruptedException e){

        }
    }
}
