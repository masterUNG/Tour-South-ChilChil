package appewtc.masterung.toursouthchilchil;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView tourTextView, provinceTextView, descripTextView;
    private ImageView imageView;
    private RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        //Bind Widget
        bindWidget();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Show View
        showView();


    }   // Main Method

    private void showView() {

        String strTour = getIntent().getStringExtra("Tour");
        tourTextView.setText(strTour);

        String strDistrict = getIntent().getStringExtra("District");
        String strProcivce = getIntent().getStringExtra("Province");
        provinceTextView.setText(strDistrict + " : " + strProcivce);

        String strDescrip = getIntent().getStringExtra("Description");
        descripTextView.setText(strDescrip);


    }   // showView

    private void bindWidget() {

        tourTextView = (TextView) findViewById(R.id.textView10);
        provinceTextView = (TextView) findViewById(R.id.textView11);
        descripTextView = (TextView) findViewById(R.id.textView13);
        imageView = (ImageView) findViewById(R.id.imageView3);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

    }   // bindWidget


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }   // onMap

}   // Main Class
