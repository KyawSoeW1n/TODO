package kurio_tetsuya.todo.ui.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import kurio_tetsuya.todo.database.DatabaseClient;
import kurio_tetsuya.todo.model.TaskModel;
import kurio_tetsuya.todo.ui.view.IUpdateView;

public class UpdatePresenter {

    Context c;
    private IUpdateView updateView;
    public UpdatePresenter(Context c,IUpdateView view){
        this.updateView=view;
        this.c=c;
    }

   /* public void loadTask(TaskModel task) {
       *//* editTextTask.setText(task.getTask());
        editTextDesc.setText(task.getDesc());
        tv_end_date.setText(task.getEnd_date());
        if (task.getStatus().equals("Doing")){
            checkBoxDoing.setChecked(true);
        }else{
            checkBoxFinished.setChecked(true);
        }*//*
    }*/

    public void updateTask(final TaskModel task, final String task_name, final String task_desc, final String task_end_date, final String status) {
        Log.e("Update","Update");
//        final String sTask = editTextTask.getText().toString().trim();
//        final String sDesc = editTextDesc.getText().toString().trim();
//        final String sFinishBy = tv_end_date.getText().toString().trim();


        class UpdateTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                task.setTask(task_name);
                task.setDesc(task_desc);
                task.setEnd_date(task_end_date);
                task.setStatus(status);
                DatabaseClient.getInstance(c).getAppDatabase()
                        .taskDao()
                        .update(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                updateView.onSuccess("Update");
            }
        }

        UpdateTask ut = new UpdateTask();
        ut.execute();
    }

    public void deleteTask(final TaskModel task) {
        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(c).getAppDatabase()
                        .taskDao()
                        .delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                updateView.onSuccess("Delete");
            }
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();

    }

}
