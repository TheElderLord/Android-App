package com.example.finalproject;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Insert
    void  registerUser(UserEntity user);
    @Query("SELECT * FROM users where email=(:email) and password = (:password)")
    UserEntity login(String email,String password);
    @Delete
    void  deleteUser(UserEntity user);
    @Update
    void updateUser(UserEntity user);
    @Query("Select * from users where id=(:id)")
    UserEntity getInfo(int id);

}
