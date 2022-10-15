package be.helha.desmette.mobileprojetmanagement.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

import be.helha.desmette.mobileprojetmanagement.db.DbSchema.StudentTable;
import be.helha.desmette.mobileprojetmanagement.db.DbSchema.ProjectTable;
import be.helha.desmette.mobileprojetmanagement.db.DbSchema.StepTable;

public class StudentBaseHelper extends SQLiteOpenHelper implements Serializable {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "database.db";

    public StudentBaseHelper(Context context){
        super(context, DATABASE_NAME,null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ StudentTable.NAME + "("
                + StudentTable.cols.UUID + " PRIMARY KEY NOT NULL,"
                + StudentTable.cols.FirstName + ")"
        );

        db.execSQL("CREATE TABLE "+ ProjectTable.NAME + "("
                + ProjectTable.cols.UUID + " PRIMARY KEY NOT NULL,"
                + ProjectTable.cols.Name + ","
                + ProjectTable.cols.Description +","
                + ProjectTable.cols.Average +","
                + ProjectTable.cols.OwnerID
                +" , FOREIGN KEY (" + ProjectTable.cols.OwnerID + ") REFERENCES " + StudentTable.NAME +" ( " + StudentTable.cols.UUID + "))"
        );

        db.execSQL("CREATE TABLE "+ StepTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, "
                + StepTable.cols.Name + ", " + StepTable.cols.Cotation + ", "
                + StepTable.cols.UUID + ","
                + StepTable.cols.ProjectID
                +" , FOREIGN KEY (" + StepTable.cols.ProjectID + ") REFERENCES " + ProjectTable.NAME +" ( " + ProjectTable.cols.UUID + "))"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
