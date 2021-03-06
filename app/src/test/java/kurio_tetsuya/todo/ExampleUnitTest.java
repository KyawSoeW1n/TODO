package kurio_tetsuya.todo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kurio_tetsuya.todo.model.TaskModel;
import kurio_tetsuya.todo.ui.presenter.AddPresenter;
import kurio_tetsuya.todo.ui.presenter.MainPresenter;
import kurio_tetsuya.todo.ui.presenter.UpdatePresenter;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    MainPresenter mainPresenter;
    AddPresenter addPresenter;
    UpdatePresenter updatePresenter;

    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){

    }


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void loadData(){
        String task=null;
        if (task!=null){
            mainPresenter.getTasks(task);
        }
    }

    @Test
    public void addData(){
        String task_name=null;
        String task_desc=null;
        String end_date=null;
        String status=null;
        if (task_name!=null && task_desc!=null && end_date!=null && status!=null){
            addPresenter.saveTask(task_name,task_desc,end_date,status);
        }
//        mainPresenter.getTasks(task);
    }

    @Test
    public void updateData(){
        TaskModel taskModel=null;
        String task_name=null;
        String task_desc=null;
        String end_date=null;
        String status=null;
        if (task_name!=null && task_desc!=null && end_date!=null && status!=null){
            updatePresenter.updateTask(taskModel,task_name,task_desc,end_date,status);
        }
//        mainPresenter.getTasks(task);
    }
}