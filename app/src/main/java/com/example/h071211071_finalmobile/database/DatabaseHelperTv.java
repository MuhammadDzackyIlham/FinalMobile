package com.example.h071211071_finalmobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.h071211071_finalmobile.model.ModelTv;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperTv extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tv_db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "tv_shows";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_BACKDROP_PATH = "backdrop_path";
    private static final String COLUMN_POSTER_PATH = "poster_path";
    private static final String COLUMN_OVERVIEW = "overview";
    private static final String COLUMN_ORIGINAL_NAME = "original_name";
    private static final String COLUMN_FIRST_AIR_DATE = "first_air_date";
    private static final String COLUMN_VOTE_AVERAGE = "vote_average";

    public DatabaseHelperTv(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " TEXT PRIMARY KEY,"
                + COLUMN_BACKDROP_PATH + " TEXT,"
                + COLUMN_POSTER_PATH + " TEXT,"
                + COLUMN_OVERVIEW + " TEXT,"
                + COLUMN_ORIGINAL_NAME + " TEXT,"
                + COLUMN_FIRST_AIR_DATE + " TEXT,"
                + COLUMN_VOTE_AVERAGE + " REAL"
                + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addMovieToFavorites(ModelTv tv) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, tv.getId());
        values.put(COLUMN_BACKDROP_PATH, tv.getBackdropPath());
        values.put(COLUMN_POSTER_PATH, tv.getPosterPath());
        values.put(COLUMN_OVERVIEW, tv.getOverview());
        values.put(COLUMN_ORIGINAL_NAME, tv.getOriginalTitle());
        values.put(COLUMN_FIRST_AIR_DATE, tv.getReleaseDate());
        values.put(COLUMN_VOTE_AVERAGE, tv.getVoteAverage());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void removeTvFromFavorites(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{id});
        db.close();
    }

    public boolean isTvFavorite(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{id});
        boolean isFavorite = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isFavorite;
    }

    public List<ModelTv> getFavoriteTvs() {
        List<ModelTv> favoriteTvs = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String backdropPath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BACKDROP_PATH));
                String posterPath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POSTER_PATH));
                String overview = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OVERVIEW));
                String originalName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORIGINAL_NAME));
                String firstAirDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_AIR_DATE));
                double voteAverage = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_VOTE_AVERAGE));

                ModelTv tvShow = new ModelTv(id, backdropPath, posterPath, overview, originalName, firstAirDate, voteAverage);
                favoriteTvs.add(tvShow);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return favoriteTvs;
    }

    public boolean deleteTv(String tvId) {
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsAffected = db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(tvId)});

        db.close();
        return rowsAffected>0;
    }
}
