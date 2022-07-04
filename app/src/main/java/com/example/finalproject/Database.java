package com.example.finalproject;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {UserEntity.class},version = 1,exportSchema = false)
public abstract class Database extends RoomDatabase {
private static final String dbName = "user";
private static  Database userDatabase;
public static synchronized Database getUserDatabase(Context context){
    if (userDatabase==null){
        userDatabase = Room.databaseBuilder(context,Database.class,dbName)
        .fallbackToDestructiveMigration().build();

    }
    return userDatabase;
}
public abstract UserDao userDao();

}
