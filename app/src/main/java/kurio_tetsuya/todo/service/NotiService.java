//package kurio_tetsuya.todo.service;
//
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.media.Ringtone;
//import android.os.IBinder;
//import android.support.annotation.Nullable;
//import android.support.v4.app.NotificationCompat;
//import android.util.Log;
//
//import kurio_tetsuya.todo.ui.activity.MainActivity;
//import kurio_tetsuya.todo.R;
//
//public class NotiService extends Service {
//    static int x = 0;
//    String TAG = getClass().getSimpleName();
//    private Ringtone ringtone;
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//
//      /*  Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//
//        if(uri == null) {
//            uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        }*/
//        x++;
//        Log.d(TAG, "OnStartCommand");
//
//        PendingIntent notificIntent = PendingIntent.getActivity(this, 0,
//                new Intent(this, MainActivity.class), 0);
//
//        NotificationCompat.Builder mBuilder =
//                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
//
//                        .setSmallIcon(R.drawable.ic_launcher_background)
//                        .setContentTitle("Check Due Date")
//                        .setTicker("")
//                        .setContentText("Hi Checking due date.You have to do something.");
//
//        mBuilder.setContentIntent(notificIntent);
//
//
//        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
//
//        mBuilder.setAutoCancel(true);
//
//        NotificationManager mNotificationManager =
//                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        mNotificationManager.notify(1 + x, mBuilder.build());
//
//        return START_NOT_STICKY;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
////        ringtone.stop();
//    }
//}