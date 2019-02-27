package kurio_tetsuya.todo.ui.activity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//import kurio_tetsuya.todo.service.AlarmReceiver;
import kurio_tetsuya.todo.ui.presenter.AddPresenter;
import kurio_tetsuya.todo.R;
import kurio_tetsuya.todo.ui.view.IAddView;

public class AddTaskActivity extends AppCompatActivity implements IAddView {
    int mYear,mMonth,mDay;
    @BindView(R.id.editTextDesc)
    EditText editTextDesc;

    @BindView(R.id.editTextTask)
    EditText editTextTask;

    @BindView(R.id.tv_end_date)
    TextView tv_end_date;
    DatePickerDialog datePickerDialog;
    private AddPresenter addPresenter;

    @OnClick(R.id.button_save)
    public void onClick() {
        Log.e("Click", "Click");
        if (isValidate()) {
            addPresenter.saveTask(editTextTask.getText().toString().trim(),
                    editTextTask.getText().toString().trim(),
                    tv_end_date.getText().toString().trim(), "To Do");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.bind(this);
        addPresenter = new AddPresenter(getApplicationContext(), this);
    }

    public boolean isValidate() {
        boolean isvalidate = false;
        if (TextUtils.isEmpty(editTextTask.getText())) {
            Toast.makeText(this, "Enter Task Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(editTextDesc.getText())) {
            Toast.makeText(this, "Enter Task Description", Toast.LENGTH_SHORT).show();
        } else if (tv_end_date.getText().toString().trim().equals("End Date")) {
            Toast.makeText(this, "Select End Date", Toast.LENGTH_SHORT).show();
        } else if (!TextUtils.isEmpty(editTextDesc.getText()) && !TextUtils.isEmpty(editTextTask.getText())) {
            isvalidate = true;
        }
        return isvalidate;
    }

    public void pickDate(View view) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR); // current year
        mMonth = c.get(Calendar.MONTH); // current month
        mDay = c.get(Calendar.DAY_OF_MONTH); // current day

        // date picker dialog
        datePickerDialog = new DatePickerDialog(AddTaskActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        tv_end_date.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(this, MainActivity.class));
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(System.currentTimeMillis());
        cal.clear();
        cal.set(mYear,mMonth,mDay,12,00);

        AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        // cal.add(Calendar.SECOND, 5);
        alarmMgr.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }

    @Override
    public void onError() {

    }
}
