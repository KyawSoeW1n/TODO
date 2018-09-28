package kurio_tetsuya.todo.Database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient {
    private static DatabaseClient mInstance;
    private AppDatabase appDatabase;
    Context mCtx;
    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "MyToDos").build();
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
