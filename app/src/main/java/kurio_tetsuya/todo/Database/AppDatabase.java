package kurio_tetsuya.todo.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kurio_tetsuya.todo.Model.TaskModel;

@Database(entities = {TaskModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDo taskDao();
}
