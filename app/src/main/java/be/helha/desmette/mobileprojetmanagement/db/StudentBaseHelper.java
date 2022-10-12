package be.helha.desmette.mobileprojetmanagement.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import be.helha.desmette.mobileprojetmanagement.db.DbSchema.StudentTable;
import be.helha.desmette.mobileprojetmanagement.db.DbSchema.ProjectTable;
import be.helha.desmette.mobileprojetmanagement.db.DbSchema.StepTable;

public class StudentBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "StudentTable.db";

    public StudentBaseHelper(Context context){
        super(context, DATABASE_NAME,null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ StudentTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, "
                + StudentTable.cols.FirstName + ", " + StudentTable.cols.UUID + ", "
                + StudentTable.cols.ProjectList + ")"
        );

        db.execSQL("CREATE TABLE "+ ProjectTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, "
                + ProjectTable.cols.Name + ", " + ProjectTable.cols.UUID + ", "
                + ProjectTable.cols.description + "," + ProjectTable.cols.Owner + ")"
        );

        db.execSQL("CREATE TABLE "+ StepTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, "
                + StepTable.cols.Name + ", " + StepTable.cols.cotation + ", "
                + StepTable.cols.Owner + ")"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
