package appewtc.masterung.toursouthchilchil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        //Receive Value
        districtString = getIntent().getStringExtra("District");
        districtTextView.setText(districtString);



    }   // Main Method

    public void clickBackTour(View view) {
        finish();
    }

}   // Main Class
