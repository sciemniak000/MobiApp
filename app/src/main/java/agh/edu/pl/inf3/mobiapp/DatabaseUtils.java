package agh.edu.pl.inf3.mobiapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class DatabaseUtils extends SQLiteOpenHelper {
    private static final String tableName = "savedImages";
    private static final String tableNameTomorrow = "tomorrowImages";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "memes.db";
    private static final String SQL_CREATE_ENTRIES_SAVED = "" +
            "create table if not exists " + tableName +
            " (image blob);";

    private static final String SQL_CREATE_ENTRIES_TOMORROW = "" +
            "create table if not exists " + tableNameTomorrow +
            " (image blob);";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + tableName;

    public DatabaseUtils(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Bitmap loadTomorrowImage(TomorrowImage enm){
        int index = 0;
        switch (enm) {
            case H01: index = 1;
                break;
            case H02: index = 2;
                break;
            case H03: index = 3;
                break;
            case H04: index = 4;
                break;
            case H05: index = 5;
                break;
            case H06: index = 6;
                break;
            case H07: index = 7;
                break;
            case H08: index = 8;
                break;
            case H09: index = 9;
                break;
            case H10: index = 10;
                break;
            case H11: index = 11;
                break;
            case H12: index = 12;
                break;
        }

        Cursor c = getTomorrowValues();
        Bitmap b = null;

        for(int i = 0; i < index - 1; i++){
            c.moveToNext();
        }
        byte[] blob = c.getBlob(0);
        c.close();
        Bitmap bitmap = BitmapFactory.decodeByteArray(blob, 0, blob.length);
        return bitmap;
    }

    public Cursor getTomorrowValues(){
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                "image"
        };

// Filter results WHERE "title" = 'My Title'
//        String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
//        String[] selectionArgs = { "My Title" };

        Cursor cursor = db.query(
                tableName,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        return cursor;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println(SQL_CREATE_ENTRIES_SAVED);
        db.execSQL(SQL_CREATE_ENTRIES_SAVED);
        db.execSQL(SQL_CREATE_ENTRIES_TOMORROW);

        Cursor c = getTomorrowValues();
        if(c.getCount() < TomorrowImage.values().length){
            insertTomorrowImage(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.image1), db);
            insertTomorrowImage(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.image2), db);
            insertTomorrowImage(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.image3), db);

            insertTomorrowImage(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.image1), db);
            insertTomorrowImage(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.image2), db);
            insertTomorrowImage(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.image3), db);

            insertTomorrowImage(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.image1), db);
            insertTomorrowImage(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.image3), db);
            insertTomorrowImage(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.image2), db);

            insertTomorrowImage(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.image1), db);
            insertTomorrowImage(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.image3), db);
            insertTomorrowImage(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.image2), db);
        }
        c.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }



    public Cursor getValues(){
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                "image"
        };

// Filter results WHERE "title" = 'My Title'
//        String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
//        String[] selectionArgs = { "My Title" };

        Cursor cursor = db.query(
                tableName,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        return cursor;
    }

    public void deleteTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public void saveImage(Bitmap img){
        byte[] data = getBitmapAsByteArray(img);
        ContentValues cv = new  ContentValues();
        cv.put("image",   data);
        getWritableDatabase().insert( tableName, null, cv );
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public void insertTomorrowImage(Bitmap bitmapImage, SQLiteDatabase db){

        byte[] data = getBitmapAsByteArray(bitmapImage);

        ContentValues cv = new  ContentValues();
        cv.put("image",   data);
        db.insert( tableNameTomorrow, null, cv );
    }
}
