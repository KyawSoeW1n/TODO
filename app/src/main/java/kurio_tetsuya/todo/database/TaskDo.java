package kurio_tetsuya.todo.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import kurio_tetsuya.todo.model.TaskModel;

@Dao
public interface TaskDo {

    @Query("SELECT * FROM TaskModel")
    List<TaskModel> getAll();

    @Query("SELECT * FROM TaskModel WHERE status IN (:name)")
    List<TaskModel> getTask(String name);

    @Insert
    void insert(TaskModel task);

    @Delete
    void delete(TaskModel task);

    @Update
    void update(TaskModel task);
}
