package com.eywa_kitchen.eywa_security.Main;

import android.app.Instrumentation;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import static xdroid.toaster.Toaster.toast;

public class PressController {

    public static void PressKey(String Mess){
        try {
            Instrumentation inst = new Instrumentation();
            switch(Mess){
                case "UP":
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_UP);
                    break;
                case "DOWN":
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN);
                    break;
                case "RIGHT":
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_RIGHT);
                    break;
                case "LEFT":
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_LEFT);
                    break;
                case "ENTER":
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_ENTER);
                    break;
                case "BACK":
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                    break;
                case "HOME":
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_HOME);
                    break;
                case "PLAYPAUSE":
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE);
                    break;
                case "MICRO":
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_VOICE_ASSIST);
                    break;
                case "PREV":
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_MEDIA_PREVIOUS);
                    break;
                case "NEXT":
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_MEDIA_NEXT);
                    break;
                case "VOLDOWN":
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_DOWN);
                    break;
                case "VOLUP":
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_UP);
                    break;
            }
        }catch(Exception e){mkmsg("err");}
    }

    private static void mkmsg(String str){
        toast(str);
    }
}
