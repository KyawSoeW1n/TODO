package kurio_tetsuya.todo.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.haha.perflib.Main;

import kurio_tetsuya.todo.model.TaskModel;
import kurio_tetsuya.todo.ui.presenter.UpdatePresenter;
import kurio_tetsuya.todo.R;
import kurio_tetsuya.todo.ui.view.IUpdateView;

public class UpdateTaskActivity extends AppCompatActivity implements IUpdateView {

    private EditText editTextTask, editTextDesc ;
    private CheckBox checkBoxFinished,checkBoxDoing;
    private TextView tv_end_date;
    TaskModel task;
    Button delete,update;
    private UpdatePresenter updatePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);


        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        tv_end_date = findViewById(R.id.tv_end_date);
        updatePresenter=new UpdatePresenter(UpdateTaskActivity.this, this);
        checkBoxFinished = findViewById(R.id.checkBoxFinished);
        checkBoxDoing = findViewById(R.id.checkBoxDoing);
        delete=findViewById(R.id.button_delete);
        update=findViewById(R.id.button_update);

        task = (TaskModel) getIntent().getSerializableExtra("task");
        loadTask(task);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UpdateTaskActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_update:
                if (isValidate()){
                    String status;
                    if(checkBoxDoing.isChecked()){
                        status="Doing";
                    }else if(checkBoxFinished.isChecked()){
                        status="Done";
                    }else{
                        status="To Do";
                    }
                    updatePresenter.updateTask(task,editTextTask.getText().toString().trim(),editTextDesc.getText().toString().trim(),tv_end_date.getText().toString(),status);
                }
//                updatePresenter.updateTask();
               // updateTask(task);
                break;
            case R.id.button_delete:
                showDialog();
                break;
        }
    }

    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTaskActivity.this);
        builder.setTitle("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //deleteTask(task);
                updatePresenter.deleteTask(task);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog ad = builder.create();
        ad.show();
    }

    private void loadTask(TaskModel task) {
        editTextTask.setText(task.getTask());
        editTextDesc.setText(task.getDesc());
        tv_end_date.setText(task.getEnd_date());
        if (task.getStatus().equals("Doing")){
            checkBoxDoing.setChecked(true);
        }else if (task.getStatus().equals("Done")){
            checkBoxFinished.setChecked(true);
        }
    }

    public boolean isValidate(){
        boolean isValidate = true;
            if (editTextTask.getText().toString().isEmpty()) {
            editTextTask.setError("Task required");
            editTextTask.requestFocus();
                isValidate= false;
        }

        if (editTextDesc.getText().toString().isEmpty()) {
            editTextDesc.setError("Desc required");
            editTextDesc.requestFocus();
            isValidate= false;
        }
        return isValidate;
    }


    @Override
    public void onSuccess(String status) {
        if(status.equals("Update")){
            Toast.makeText(this, "Update Successfully", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }else{
            Toast.makeText(this, "Delete Successfully", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    public void onError() {
        Toast.makeText(this, "onError", Toast.LENGTH_SHORT).show();
    }
}
