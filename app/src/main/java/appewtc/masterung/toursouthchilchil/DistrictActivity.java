package appewtc.masterung.toursouthchilchil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DistrictActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);

        //Receive from Intent
        String[] districtStrings = getIntent().getStringArrayExtra("District");
        String strProvince = getIntent().getStringExtra("Province");

        //Show Province
        TextView provinceTextView = (TextView) findViewById(R.id.textView8);
        provinceTextView.setText("อำเภอใน จังหวัด " + strProvince);


    }   // Main Method
}   // Main Class
