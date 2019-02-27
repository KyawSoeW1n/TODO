package kurio_tetsuya.todo.ui.presenter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

import kurio_tetsuya.todo.database.DatabaseClient;
import kurio_tetsuya.todo.model.TaskModel;
//import kurio_tetsuya.todo.service.AlarmReceiver;
import kurio_tetsuya.todo.ui.activity.MainActivity;
import kurio_tetsuya.todo.ui.view.IAddView;

import static android.content.ContentValues.TAG;
import static android.content.Context.ALARM_SERVICE;

public class AddPresenter   {
    private Context c;
    AlarmManager alarmManager;
    IAddView view;
    ArrayList<Integer> keyword = new ArrayList<>();
    public AddPresenter(Context c, IAddView view){
        this.c=c;
        this.view=view;
    }


    public void saveTask(final String task_name, final String desc, final String sEndDate, final String status) {
        Log.e("Save","Save");
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                splitKeyword(sEndDate);
                //creating a task
                TaskModel task = new TaskModel();
                task.setTask(task_name);
                task.setDesc(desc);
                task.setEnd_date((sEndDate));
                task.setStatus(status);

                //adding to database
                DatabaseClient.getInstance(c).getAppDatabase()
                        .taskDao()
                        .insert(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                long delay = 0;
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, keyword.get(1));
                calendar.set(Calendar.YEAR, keyword.get(2));
                calendar.set(Calendar.DAY_OF_MONTH, keyword.get(0));
                delay = calendar.getTimeInMillis();
                Log.e(TAG, "" + delay);
                Intent intent = new Intent(c, MainActivity.class);
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(c, 0, intent, 0);
                alarmManager = (AlarmManager) c.getSystemService(ALARM_SERVICE);
                final int _id = (int) System.currentTimeMillis();
                alarmManager.set(AlarmManager.RTC_WAKEUP, delay,
                        PendingIntent.getBroadcast(c, _id, intent,
                                PendingIntent.FLAG_UPDATE_CURRENT));
                view.onSuccess();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

    public ArrayList<Integer> splitKeyword(String sEndDate) {
        String[] arrOfStr = sEndDate.split("/", 3);
        for (String a : arrOfStr) {
            keyword.add(Integer.parseInt(arrOfStr[0]));
            keyword.add(Integer.parseInt(arrOfStr[1]));
            keyword.add(Integer.parseInt(arrOfStr[2]));
        }
        return keyword;
    }
}
