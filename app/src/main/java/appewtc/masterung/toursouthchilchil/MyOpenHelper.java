package appewtc.masterung.toursouthchilchil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masterUNG on 3/24/16 AD.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    public static final String database_name = "tour.db";
    private static final int database_version = 1;

    private static final String create_user_table = "create table userTABLE (" +
            "_id integer primary key, " +
            "User text, " +
            "Password text, " +
            "Name text, " +
            "Email text);";

    private static final String create_tour_table = "create table tourTABLE (" +
            "_id integer primary key, " +
            "Province text, " +
            "District text, " +
            "Name text, " +
            "Category text, " +
            "Description text, " +
            "Image text, " +
            "Lat text, " +
            "Lng text, " +
            "Range text);";


    public MyOpenHelper(Context context) {
        super(context, database_name, null, database_version);
    }   //Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_user_table);
        sqLiteDatabase.execSQL(create_tour_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}   // Main Class
