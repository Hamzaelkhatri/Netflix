/*
 * Create by Hamza elkhatri
 */

/*
 * Create by Hamza elkhatri
 */

package com.example.netflix.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.netflix.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class SqliteMovie extends SQLiteOpenHelper {
    public static final String DBName = "Movie.db";

    public SqliteMovie(Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table movies (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,imgCover TEXT,imgThum TEXT,description TEXT,time INTEGER,streaminglink TEXT,original INTEGER,season INTEGER ,episode INTEGER)");
        db.execSQL("create table Mylist (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,imgCover TEXT,imgThum TEXT,description TEXT,streaminglink TEXT,original INTEGER,season INTEGER ,episode INTEGER)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS movies");
        db.execSQL("DROP TABLE IF EXISTS Mylist");
        onCreate(db);
    }

    public boolean insertData(String title, String imgCover, String imgThum, String description, int time,String streamingLink,int original,int episode,int season) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("imgCover", imgCover);
        contentValues.put("imgThum", imgThum);
        contentValues.put("description", description);
        contentValues.put("time", time);
        contentValues.put("streaminglink", streamingLink);
        contentValues.put("original", original);
        contentValues.put("episode", episode);
        contentValues.put("season", season);
        long result = db.insert("movies", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public List<Movie> getMovies()
    {
        List<Movie> movieList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =db.rawQuery("select * from movies",null);
        res.moveToFirst();
        while (res.isAfterLast()==false)
        {
            //                                                                          Movie(String title, String description, String thumbnail, String cover, String streamibgLink, int original, int episode, int season ,int time)
            movieList.add(new Movie(res.getString(1),res.getString(4),res.getString(3),res.getString(2),res.getString(6),res.getInt(7),res.getInt(9),res.getInt(8),res.getInt(5)));//(String title, String description, String thumbnail, String cover, int original, int episode, int season, String streamibgLink, int time)
            res.moveToNext();
        }
        return movieList;
    }

    public void DeleteMovies(String title,int season ,int episode)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("title",title);
        db.delete("movies","title like ? and season = ? and episode = ?",new String[]{title,season+"",episode+""});

    }

    public boolean AddList(String title, String imgCover, String imgThum, String description,String streamingLink,int original)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("imgCover", imgCover);
        contentValues.put("imgThum", imgThum);
        contentValues.put("description", description);
        contentValues.put("streaminglink", streamingLink);
        contentValues.put("original", original);
        long result = db.insert("Mylist", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean searchMovies(String title)
    {
        SQLiteDatabase db = this.getReadableDatabase();

       Cursor c = db.rawQuery("SELECT * FROM Mylist Where title like '"+title+"'",null);
        if(c.moveToNext())
            return true;
        return false;
    }

    public List<Movie> getMyList()
    {
        List<Movie> movieList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =db.rawQuery("select * from Mylist",null);
        res.moveToFirst();
        while (!res.isAfterLast())
        {
            movieList.add(new Movie(res.getString(1),res.getString(4),res.getString(3),res.getString(2),res.getString(5),res.getInt(6)));//(String title, String description, String thumbnail, String cover, String streamibgLink)
            res.moveToNext();
        }
        return movieList;
    }

    public void deleteFromList(String title)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("title",title);
        db.delete("Mylist","title like ?",new String[]{title});
    }

    public int getTime(String title,int season,int ep)
    {
        int time=0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =db.rawQuery("select time from movies where title like '"+title+"' and season = "+season+" and episode = "+ep+"",null);
        res.moveToFirst();
        if(!res.isAfterLast())
            time=res.getInt(0);
        return time;
    }

}
