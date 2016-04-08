package appewtc.masterung.toursouthchilchil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class DistrictActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);

        //Receive from Intent
        final String[] districtStrings = getIntent().getStringArrayExtra("District");
        String strProvince = getIntent().getStringExtra("Province");

        //Show Province
        TextView provinceTextView = (TextView) findViewById(R.id.textView8);
        provinceTextView.setText("อำเภอใน จังหวัด " + strProvince);

        //Show ListView
        MyAdapter myAdapter = new MyAdapter(this, districtStrings);
        ListView listView = (ListView) findViewById(R.id.listView2);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(DistrictActivity.this, TourActivity.class);
                intent.putExtra("District", districtStrings[i]);
                startActivity(intent);

            }   // onItem
        });

    }   // Main Method

    public void clickBackDistrict(View view) {
        finish();
    }


}   // Main Class
