package kurio_tetsuya.todo.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kurio_tetsuya.todo.Database.DatabaseClient;
import kurio_tetsuya.todo.Model.TaskModel;
import kurio_tetsuya.todo.View.IMainView;

public class MainPresenter {
    Context c;
    private IMainView iMainView;
    List<TaskModel> taskList = new ArrayList<>();

    public MainPresenter(Context c, IMainView v) {
        this.c = c;
        this.iMainView = v;
    }

    public void getTasks(final String task_name) {
        class GetTasks extends AsyncTask<Void, Void, List<TaskModel>> {
            @Override
            protected List<TaskModel> doInBackground(Void... voids) {
                taskList = DatabaseClient
                        .getInstance(c)
                        .getAppDatabase()
                        .taskDao()
                        .getTask(task_name);
                return taskList;
            }

            @Override
            protected void onPostExecute(List<TaskModel> tasks) {
                iMainView.showList(tasks);
            }
        }
        GetTasks gt = new GetTasks();
        gt.execute();
    }
}
