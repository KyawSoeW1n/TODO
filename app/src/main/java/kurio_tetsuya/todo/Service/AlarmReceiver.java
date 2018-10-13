package kurio_tetsuya.todo.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    String TAG = getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Alarm Receiver");
        Toast.makeText(context, "ALARM ON", Toast.LENGTH_SHORT).show();

        Intent intent1 = new Intent(context, NotiService.class);
        context.startService(intent1);

    }
}