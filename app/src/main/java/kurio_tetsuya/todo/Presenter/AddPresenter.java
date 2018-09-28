package kurio_tetsuya.todo.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import kurio_tetsuya.todo.Activity.MainActivity;
import kurio_tetsuya.todo.Database.DatabaseClient;
import kurio_tetsuya.todo.Model.TaskModel;
import kurio_tetsuya.todo.R;
import kurio_tetsuya.todo.View.IAddView;

public class AddPresenter   {
    private Context c;
    IAddView view;
    public AddPresenter(Context c, IAddView view){
        this.c=c;
        this.view=view;
    }


    public void saveTask(final String task_name, final String desc, final String sEndDate, final String status) {
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                TaskModel task = new TaskModel();
                task.setTask(task_name);
                task.setDesc(desc);
                task.setEnd_date(sEndDate);
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
                view.onSuccess();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }
}
