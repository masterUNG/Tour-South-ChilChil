package appewtc.masterung.toursouthchilchil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

        final String[] provinceStrings = new String[cursor.getCount()];
        final String[] districtStrings = new String[cursor.getCount()];
        final String[] tourStrings = new String[cursor.getCount()];
        final String[] descriptionStrings = new String[cursor.getCount()];
        final String[] imageStrings = new String[cursor.getCount()];
        final String[] latStrings = new String[cursor.getCount()];
        final String[] lngStrings = new String[cursor.getCount()];
        final String[] rangrStrings = new String[cursor.getCount()];


        for (int i = 0; i < cursor.getCount(); i++) {

            provinceStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Province));
            districtStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_District));
            tourStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Name));
            descriptionStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Description));
            imageStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Image));
            latStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Lat));
            lngStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Lng));
            rangrStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Range));

            cursor.moveToNext();
        }   //for
        cursor.close();

        Log.d("8April", "tourString Lang ==>> " + cursor.getCount());

        MyAdapter myAdapter = new MyAdapter(this, tourStrings);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(TourActivity.this, MapsActivity.class);

                intent.putExtra("Tour", tourStrings[i]);
                intent.putExtra("District", districtStrings[i]);
                intent.putExtra("Province", provinceStrings[i]);
                intent.putExtra("Image", imageStrings[i]);
                intent.putExtra("Description", descriptionStrings[i]);
                intent.putExtra("Lat", latStrings[i]);
                intent.putExtra("Lng", lngStrings[i]);
                intent.putExtra("Range", rangrStrings[i]);

                startActivity(intent);

            }   // onItem
        });


    }   // showListTour

    public void clickBackTour(View view) {
        finish();
    }

}   // Main Class
