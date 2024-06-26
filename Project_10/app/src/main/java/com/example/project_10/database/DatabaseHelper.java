package com.example.project_10.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Пропишем в отдельные переменные название БД и её столбов
    private static final String TABLE_NAME = "Cars";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_MARK = "mark";
    private static final String COLUMN_YearOfRelease= "YearOfRelease";
    private static final String COLUMN_COLOR = "color";
    private static final String COLUMN_PRICE = "price";
    public DatabaseHelper(Context context) {
        super(context, "car.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER, " +
                COLUMN_MARK + " TEXT, " +
                COLUMN_YearOfRelease + " TEXT, " +
                COLUMN_COLOR + " TEXT, " +
                COLUMN_PRICE + " TEXT)";
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    // Добавление новой машины
    public boolean addCar(Car car) { //метод getReadableDatabase() (получение базы данных для чтения)
       // или getWritableDatabase() (запись данных в БД)
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, car.getId());
        cv.put(COLUMN_MARK, car.getMark());
        cv.put(COLUMN_YearOfRelease, car.getYearOfRelease());
        cv.put(COLUMN_COLOR, car.getColor());
        cv.put(COLUMN_PRICE, car.getPrice());
        long result = db.insert(TABLE_NAME, null, cv);
        db.close();
        return result != -1;
    }
    // Удаление машины по марке
    public boolean deleteCar(String mark) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COLUMN_MARK + " = ?", new String[]{mark});
        db.close();
        return result > 0;
    }
    // Поиск машины по марке
    public List<Car> findCar(String mark) {
        List<Car> cars = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_MARK,
                        COLUMN_YearOfRelease, COLUMN_COLOR, COLUMN_PRICE},
                COLUMN_MARK + " = ?", new String[]{mark}, null,
                null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Car car = new Car(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
                cars.add(car);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return cars;
    }

    public boolean updateCar(String oldId, String newPrice,
                                 String newId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, newId);
        cv.put(COLUMN_PRICE, newPrice);
        // Обновляем запись, где марка машины =  oldMark
        int result = db.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[]{oldId});
        db.close();
        return result > 0;
    }

    // Получение всех машин
    public List<Car> getAllCars() {
        List<Car> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Car car = new Car(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
                contactList.add(car);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactList;
    }

}

