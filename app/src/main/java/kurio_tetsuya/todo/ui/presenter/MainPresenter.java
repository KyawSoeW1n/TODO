package kurio_tetsuya.todo.ui.presenter;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import kurio_tetsuya.todo.database.DatabaseClient;
import kurio_tetsuya.todo.model.TaskModel;
import kurio_tetsuya.todo.ui.view.IMainView;

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
