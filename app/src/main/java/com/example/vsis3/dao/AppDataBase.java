package com.example.vsis3.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.vsis3.entity.TeamMemberEntity;

@Database(entities = {TeamMemberEntity.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase appDatabase;

    public abstract TeamMemberDAO createTeamMemberDAO();

    public static AppDataBase getInstance(Context context) {
        if(appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "driver_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }
}
