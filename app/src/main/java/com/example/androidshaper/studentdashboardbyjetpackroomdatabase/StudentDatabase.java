package com.example.androidshaper.studentdashboardbyjetpackroomdatabase;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Student.class},version = 1,exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();
    private static volatile StudentDatabase INSTANCE;

    static StudentDatabase getDatabase(final Context context)
    {
        if (INSTANCE==null)
        {
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),StudentDatabase.class,"word_database2")
                    .fallbackToDestructiveMigration()
                    .build();



        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final StudentDao mDao;

        PopulateDbAsync(StudentDatabase db) {
            mDao = db.studentDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
//            mDao.deleteAll();
//            Semister semister = new Semister(3.25,4.00,"mysem");
//            mDao.insertSemister(semister);
            return null;
        }
    }


}
