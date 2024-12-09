package com.example.practice2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "employees.db";
    private static final int DATABASE_VERSION = 5;

    // Table and column names
    public static final String TABLE_EMPLOYEES = "employees";

    // Personal Details
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_MIDDLENAME = "middlename";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";

    // Contact Details
    public static final String COLUMN_PERSONAL_PHONE = "personal_phone";
    public static final String COLUMN_WORK_PHONE = "work_phone";
    public static final String COLUMN_PERSONAL_EMAIL = "personal_email";
    public static final String COLUMN_WORK_EMAIL = "work_email";
    public static final String COLUMN_ADDRESS_LINE_1 = "address_line_1";
    public static final String COLUMN_ADDRESS_LINE_2 = "address_line_2";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_POSTCODE = "postcode";
    public static final String COLUMN_COUNTY = "county";
    public static final String COLUMN_COUNTRY = "country";

    // Emergency Contact Details
    public static final String COLUMN_EMERGENCY_CONTACT_FIRSTNAME = "emergency_contact_firstname";
    public static final String COLUMN_EMERGENCY_CONTACT_LASTNAME = "emergency_contact_lastname";
    public static final String COLUMN_EMERGENCY_CONTACT_PHONE = "emergency_contact_phone";
    public static final String COLUMN_EMERGENCY_CONTACT_EMAIL = "emergency_contact_email";

    // Salary Info
    public static final String COLUMN_CURRENT_SALARY = "current_salary";
    public static final String COLUMN_EMPLOYMENT_START_DATE = "employment_start_date";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EMPLOYEES_TABLE = "CREATE TABLE " + TABLE_EMPLOYEES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_FIRSTNAME + " TEXT NOT NULL, "
                + COLUMN_MIDDLENAME + " TEXT, "
                + COLUMN_LASTNAME + " TEXT NOT NULL, "
                + COLUMN_DATE_OF_BIRTH + " TEXT NOT NULL, "
                + COLUMN_PERSONAL_PHONE + " TEXT NOT NULL, "
                + COLUMN_WORK_PHONE + " TEXT, "
                + COLUMN_PERSONAL_EMAIL + " TEXT NOT NULL, "
                + COLUMN_WORK_EMAIL + " TEXT, "
                + COLUMN_ADDRESS_LINE_1 + " TEXT NOT NULL, "
                + COLUMN_ADDRESS_LINE_2 + " TEXT, "
                + COLUMN_CITY + " TEXT NOT NULL, "
                + COLUMN_POSTCODE + " TEXT NOT NULL, "
                + COLUMN_COUNTY + " TEXT, " // Optional
                + COLUMN_COUNTRY + " TEXT NOT NULL, "
                + COLUMN_EMERGENCY_CONTACT_FIRSTNAME + " TEXT NOT NULL, "
                + COLUMN_EMERGENCY_CONTACT_LASTNAME + " TEXT NOT NULL, "
                + COLUMN_EMERGENCY_CONTACT_PHONE + " TEXT NOT NULL, "
                + COLUMN_EMERGENCY_CONTACT_EMAIL + " TEXT, "
                + COLUMN_CURRENT_SALARY + " REAL NOT NULL, "
                + COLUMN_EMPLOYMENT_START_DATE + " TEXT NOT NULL"
                + ")";
        db.execSQL(CREATE_EMPLOYEES_TABLE);
    }

    // Handle Database Version Upgrades
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        onCreate(db);
    }

    /**
     * Insert a new employee into the database.
     * @param values ContentValues containing employee details.
     * @return Row ID of the newly inserted employee, or -1 if failed.
     */
    public long addEmployee(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(TABLE_EMPLOYEES, null, values);
        db.close();
        return result;
    }

    /**
     * Retrieve all employees from the database.
     * @return Cursor containing all employee records.
     */
    public Cursor getAllEmployees() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_EMPLOYEES, null);
    }

    /**
     * Retrieve a specific employee by ID.
     * @param id ID of the employee to retrieve.
     * @return Cursor containing the employee record.
     */
    public Cursor getEmployeeById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                TABLE_EMPLOYEES,
                null,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
    }

    /**
     * Delete an employee by ID.
     * @param id ID of the employee to delete.
     * @return Number of rows affected.
     */
    public int deleteEmployee(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_EMPLOYEES, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected;
    }

    /**
     * Update an employee's record.
     * @param id ID of the employee to update.
     * @param values ContentValues containing updated details.
     * @return Number of rows affected.
     */
    public int updateEmployee(long employeeId, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.update(TABLE_EMPLOYEES, values, COLUMN_ID + "=?", new String[]{String.valueOf(employeeId)});
        db.close();
        return rowsAffected;
    }

}
