package kltuk.com.agroterra.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import kltuk.com.agroterra.AgroTerraApp;
import kltuk.com.agroterra.models.Act;
import kltuk.com.agroterra.models.Poly;


public class DBHelper extends SQLiteOpenHelper{

    private static final String INT_TYPE = " INTEGER";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_RESULT_TABLE =
            "CREATE TABLE " + Act.ActEntry.TABLE_NAME + " (" +
                    Act.ActEntry._ID + " INTEGER PRIMARY KEY," +
                    Act.ActEntry.COLUMN_FIELD_ID + INT_TYPE + COMMA_SEP +
                    Act.ActEntry.COLUMN_POLY_PRIMARY_KEY + INT_TYPE + COMMA_SEP +
                    Act.ActEntry.COLUMN_COUNT_WEEDS + INT_TYPE + COMMA_SEP +
                    Act.ActEntry.COLUMN_FIELD_AREA + INT_TYPE + COMMA_SEP +
                    Act.ActEntry.COLUMN_TOTAL_COUNT_WEEDS + INT_TYPE + COMMA_SEP +
                    Act.ActEntry.COLUMN_COMMENT + TEXT_TYPE + " )";
    private static final String SQL_CREATE_POLY_TABLE =
            "CREATE TABLE " + Poly.PolyEntry.TABLE_NAME + " (" +
                    Poly.PolyEntry._ID + " INTEGER PRIMARY KEY," +
                    Poly.PolyEntry.COLUMN_NAME + TEXT_TYPE  + " )";

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = AgroTerraApp.class.getName() + ".db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_RESULT_TABLE);
        db.execSQL(SQL_CREATE_POLY_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long writeAct(Act act) {

        if (getAct(act.getId()) != null) {
            return -1;
        }

        long polyId = writePoly(act.getField());

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Act.ActEntry.COLUMN_FIELD_ID, act.getId());
        values.put(Act.ActEntry.COLUMN_POLY_PRIMARY_KEY, polyId);
        values.put(Act.ActEntry.COLUMN_COUNT_WEEDS, act.getCountWeeds());
        values.put(Act.ActEntry.COLUMN_FIELD_AREA, act.getFieldArea());
        values.put(Act.ActEntry.COLUMN_TOTAL_COUNT_WEEDS, act.getTotalCountWeeds());
        values.put(Act.ActEntry.COLUMN_COMMENT, act.getComment());

        return db.insert(Act.ActEntry.TABLE_NAME, Act.ActEntry.COLUMN_NAME_NULLABLE, values);

    }

    public long writePoly(Poly poly) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Poly.PolyEntry.COLUMN_NAME, poly.getName());

        return db.insert(Poly.PolyEntry.TABLE_NAME, Poly.PolyEntry.COLUMN_NAME_NULLABLE, values);

    }



    public List<Act> getActs() {

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                Act.ActEntry.COLUMN_FIELD_ID,
                Act.ActEntry.COLUMN_POLY_PRIMARY_KEY,
                Act.ActEntry.COLUMN_COUNT_WEEDS,
                Act.ActEntry.COLUMN_FIELD_AREA,
                Act.ActEntry.COLUMN_TOTAL_COUNT_WEEDS,
                Act.ActEntry.COLUMN_COMMENT,
        };

        Cursor c = db.query(
                Act.ActEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List<Act> result = new ArrayList<>();

        if (!c.moveToFirst()) {
            c.close();
            return new ArrayList<>();
        }

        do {

            int fieldId = c.getInt(c.getColumnIndex(Act.ActEntry.COLUMN_FIELD_ID));
            long polyId = c.getLong(c.getColumnIndex(Act.ActEntry.COLUMN_POLY_PRIMARY_KEY));
            int countWeeds = c.getInt(c.getColumnIndex(Act.ActEntry.COLUMN_COUNT_WEEDS));
            int fieldArea = c.getInt(c.getColumnIndex(Act.ActEntry.COLUMN_FIELD_AREA));
            int totalCountWeeds = c.getInt(c.getColumnIndex(Act.ActEntry.COLUMN_TOTAL_COUNT_WEEDS));
            String comment = c.getString(c.getColumnIndex(Act.ActEntry.COLUMN_COMMENT));

            Poly poly = getPoly(polyId);

            Act act = new Act();
            act.setId(fieldId);
            act.setField(poly);
            act.setCountWeeds(countWeeds);
            act.setFieldArea(fieldArea);
            act.setTotalCountWeeds(totalCountWeeds);
            act.setComment(comment);

            result.add(act);


        } while (c.moveToNext());

        c.close();

        return result;

    }

    public Act getAct(long id) {

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                Act.ActEntry.COLUMN_FIELD_ID,
                Act.ActEntry.COLUMN_POLY_PRIMARY_KEY,
                Act.ActEntry.COLUMN_COUNT_WEEDS,
                Act.ActEntry.COLUMN_FIELD_AREA,
                Act.ActEntry.COLUMN_TOTAL_COUNT_WEEDS,
                Act.ActEntry.COLUMN_COMMENT,
        };

        String selection = Act.ActEntry.COLUMN_FIELD_ID + " LIKE ?";

        String[] selectionArgs = { String.valueOf(id) };

        Cursor c = db.query(
                Act.ActEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(c.moveToFirst()) {

            int fieldId = c.getInt(c.getColumnIndex(Act.ActEntry.COLUMN_FIELD_ID));
            long polyId = c.getLong(c.getColumnIndex(Act.ActEntry.COLUMN_POLY_PRIMARY_KEY));
            int countWeeds = c.getInt(c.getColumnIndex(Act.ActEntry.COLUMN_COUNT_WEEDS));
            int fieldArea = c.getInt(c.getColumnIndex(Act.ActEntry.COLUMN_FIELD_AREA));
            int totalCountWeeds = c.getInt(c.getColumnIndex(Act.ActEntry.COLUMN_TOTAL_COUNT_WEEDS));
            String comment = c.getString(c.getColumnIndex(Act.ActEntry.COLUMN_COMMENT));

            Poly poly = getPoly(polyId);

            Act act = new Act();
            act.setId(fieldId);
            act.setField(poly);
            act.setCountWeeds(countWeeds);
            act.setFieldArea(fieldArea);
            act.setTotalCountWeeds(totalCountWeeds);
            act.setComment(comment);

            c.close();

            return act;

        }

        return null;

    }

    @Nullable
    public Poly getPoly(long id) {

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                Poly.PolyEntry._ID,
                Poly.PolyEntry.COLUMN_NAME
        };


        String selection = Poly.PolyEntry._ID + " LIKE ?";

        String[] selectionArgs = { String.valueOf(id) };


        Cursor c = db.query(
                Poly.PolyEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            c.moveToFirst();

            Poly poly = new Poly();
            poly.setName(c.getString(c.getColumnIndex(Poly.PolyEntry.COLUMN_NAME)));

            c.close();

            return poly;

        }

        return null;

    }


}
