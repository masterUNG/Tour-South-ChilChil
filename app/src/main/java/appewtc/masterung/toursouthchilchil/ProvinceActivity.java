package appewtc.masterung.toursouthchilchil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ProvinceActivity extends AppCompatActivity {

    //Explicit
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);

       //Create ListView
        String[] provinceStrings = getResources().getStringArray(R.array.province);
        MyAdapter myAdapter = new MyAdapter(this, provinceStrings);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(myAdapter);

    }   // Main Method

}   // Main Class
