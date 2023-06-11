package com.example.h071211071_finalmobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.h071211071_finalmobile.model.ModelMovie;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperMovie extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movie_db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "movies";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_BACKDROP_PATH = "backdrop_path";
    private static final String COLUMN_POSTER_PATH = "poster_path";
    private static final String COLUMN_OVERVIEW = "overview";
    private static final String COLUMN_ORIGINAL_TITLE = "original_title";
    private static final String COLUMN_RELEASE_DATE = "release_date";
    private static final String COLUMN_VOTE_AVERAGE = "vote_average";

    public DatabaseHelperMovie(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " TEXT PRIMARY KEY,"
                + COLUMN_BACKDROP_PATH + " TEXT,"
                + COLUMN_POSTER_PATH + " TEXT,"
                + COLUMN_OVERVIEW + " TEXT,"
                + COLUMN_ORIGINAL_TITLE + " TEXT,"
                + COLUMN_RELEASE_DATE + " TEXT,"
                + COLUMN_VOTE_AVERAGE + " REAL"
                + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addMovieToFavorites(ModelMovie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, movie.getId());
        values.put(COLUMN_BACKDROP_PATH, movie.getBackdropPath());
        values.put(COLUMN_POSTER_PATH, movie.getPosterPath());
        values.put(COLUMN_OVERVIEW, movie.getOverview());
        values.put(COLUMN_ORIGINAL_TITLE, movie.getOriginalTitle());
        values.put(COLUMN_RELEASE_DATE, movie.getReleaseDate());
        values.put(COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void removeMovieFromFavorites(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{id});
        db.close();
    }

    public boolean isMovieFavorite(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{id});
        boolean isFavorite = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isFavorite;
    }

    public List<ModelMovie> getFavoriteMovies() {
        List<ModelMovie> favoriteMovies = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String backdropPath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BACKDROP_PATH));
                String posterPath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POSTER_PATH));
                String overview = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OVERVIEW));
                String originalTitle = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORIGINAL_TITLE));
                String releaseDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RELEASE_DATE));
                double voteAverage = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_VOTE_AVERAGE));

                ModelMovie movie = new ModelMovie(id, backdropPath, posterPath, overview, originalTitle, releaseDate, voteAverage);
                favoriteMovies.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return favoriteMovies;
    }

    public boolean deleteMovie(String movieId) {
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsAffected = db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(movieId)});

        db.close();
        return rowsAffected>0;
    }
}

