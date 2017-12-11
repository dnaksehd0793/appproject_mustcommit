package onc.appproject.firstonc;

import android.content.Context;
import android.telephony.SmsManager;

/**
 * Created by jamleekim on 2017-12-11.
 */

public class Messenger {

    private Context mContext;

    public Messenger(Context mContext) {
        this.mContext = mContext;
    }

    public void sendMessageTo(String phoneNum, String message) {

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNum, null, message, null, null);
    }

}
