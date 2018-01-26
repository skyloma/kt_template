package base;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.loma.BuildConfig;
import com.socks.library.KLog;

import data.DB;

public class App extends Application {
    public DB db;
    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(BuildConfig.LOG_DEBUG);
        db = Room.databaseBuilder(getApplicationContext(),
                DB.class, "zjt-db")
//                .allowMainThreadQueries()
                .build();
    }
}
