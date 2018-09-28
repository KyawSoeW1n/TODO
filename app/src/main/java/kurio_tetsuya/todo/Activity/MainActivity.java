package kurio_tetsuya.todo.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kurio_tetsuya.todo.Adapter.ViewPagerAdapter;
import kurio_tetsuya.todo.Presenter.MainPresenter;
import kurio_tetsuya.todo.R;
import kurio_tetsuya.todo.Adapter.TaskAdapter;
import kurio_tetsuya.todo.Model.TaskModel;
import kurio_tetsuya.todo.View.IMainView;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, IMainView {
    private FloatingActionButton buttonAddTask;
    private RecyclerView recyclerView;
    private TabLayout tab_layout;
    private MainPresenter presenter;
    private List<TaskModel> listData;
    private TaskAdapter adapter;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        listData = new ArrayList<>();
        tab_layout = findViewById(R.id.tab_layout);
        recyclerView = findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        buttonAddTask = findViewById(R.id.floating_button_add);
        listData = new ArrayList<>();
//        pager=findViewById(R.id.pager);
        presenter = new MainPresenter(this,this);
        adapter = new TaskAdapter(MainActivity.this, listData);
//        pager_adapter=new ViewPagerAdapter();
        recyclerView.setAdapter(adapter);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                finish();
                startActivity(intent);
            }
        });
        tab_layout.setOnTabSelectedListener(this);
        presenter.getTasks("To Do");
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                presenter.getTasks("To Do");
                break;
            case 1:
                presenter.getTasks("Doing");
                break;
            case 2:
                presenter.getTasks("Done");
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    @Override
    public void showList(List<TaskModel> list) {
        adapter.changeData(list);
        Log.e("Show List","showList");
    }

    @Override
    public void errorList() {

    }
}
