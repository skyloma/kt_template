package data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import data.bean.User;
import data.dao.UserDao;

/**
 * Created by loma on 2017/12/21.
 */
@Database(entities = {User.class }, version = 1)
@TypeConverters({Converters.class})
public abstract class DB extends RoomDatabase {
    public abstract UserDao userDao();


}
