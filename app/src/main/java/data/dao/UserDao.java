package  data.dao;
import  data.bean.User;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
@Dao
public interface UserDao {  
@Insert(onConflict = OnConflictStrategy.REPLACE)
public void insert(User... users);
@Update
public void update(User  user);
@Delete
public void delete(User  user);
@Query("SELECT * FROM User")
public List<User> findAll();
@Query("SELECT * FROM User where id =:id")
public User findById (int id);
} 
