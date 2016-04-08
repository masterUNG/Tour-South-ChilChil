package appewtc.masterung.toursouthchilchil;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class TourActivity extends AppCompatActivity {

    //Explicit
    private String districtString;
    private TextView districtTextView;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        //Bind Widget
        districtTextView = (TextView) findViewById(R.id.textView9);
        listView = (ListView) findViewById(R.id.listView3);

        //Receive Value
        districtString = getIntent().getStringExtra("District");
        districtTextView.setText(districtString);

        //Show List Tour
        showListTour();



    }   // Main Method

    private void showListTour() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tourTABLE WHERE District = " + "'" + districtString + "'", null);
        cursor.moveToFirst();

        String[] tourStrings = new String[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {

            tourStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Name));

            cursor.moveToNext();
        }   //for
        cursor.close();

        Log.d("8April", "tourString Lang ==>> " + cursor.getCount());

        MyAdapter myAdapter = new MyAdapter(this, tourStrings);
        listView.setAdapter(myAdapter);


    }   // showListTour

    public void clickBackTour(View view) {
        finish();
    }

}   // Main Class
