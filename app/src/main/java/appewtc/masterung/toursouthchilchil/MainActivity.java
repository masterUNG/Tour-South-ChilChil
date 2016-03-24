package appewtc.masterung.toursouthchilchil;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Request Database
        myManage = new MyManage(this);

        //Test Add Value
        //testAddValue();

        //Delete All data
        deleteAllData();

        //Syn JSON to SQLIte
        synJSONtoSQLite();


    }   // Main Method

    private void synJSONtoSQLite() {

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        int intTimes = 0;
        while (intTimes <= 1) {

            //1 Create InputStream
            InputStream inputStream = null;
            String[] urlStrings = {"http://swiftcodingthai.com/saa/php_get_user_master.php",
                    "http://swiftcodingthai.com/saa/php_get_tour_master.php"};

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(urlStrings[intTimes]);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();

            } catch (Exception e) {
                Log.d("test", "Input => " + e.toString());
            }

            //2 Create JSON String
            String strJSON = null;
            try {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                String strLine = null;

                while ((strLine = bufferedReader.readLine()) != null) {
                    stringBuilder.append(strLine);
                }
                inputStream.close();
                strJSON = stringBuilder.toString();


            } catch (Exception e) {
                Log.d("test", "JSON => " + e.toString());
            }

            //3 Update SQLite
            try {

                JSONArray jsonArray = new JSONArray(strJSON);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    switch (intTimes) {
                        case 0:
                            //for userTABLE
                            String strUser = jsonObject.getString(MyManage.column_User);
                            String strPassword = jsonObject.getString(MyManage.column_Password);
                            String strName = jsonObject.getString(MyManage.column_Name);
                            String strEmail = jsonObject.getString(MyManage.column_Email);

                            myManage.addUser(strUser, strPassword, strName, strEmail);

                            break;
                        case 1:
                            //for tourTABLE
                            String strPrivince = jsonObject.getString(MyManage.column_Province);
                            String strDistrict = jsonObject.getString(MyManage.column_District);
                            String strName1 = jsonObject.getString(MyManage.column_Name);
                            String strCategory = jsonObject.getString(MyManage.column_Category);
                            String strDescription = jsonObject.getString(MyManage.column_Description);
                            String strImage = jsonObject.getString(MyManage.column_Image);
                            String strLat = jsonObject.getString(MyManage.column_Lat);
                            String strLng = jsonObject.getString(MyManage.column_Lng);
                            String strRange = jsonObject.getString(MyManage.column_Range);

                            myManage.addTour(strPrivince, strDistrict, strName1, strCategory, strDescription,
                                    strImage, strLat, strLng, strRange);

                            break;
                    }   // switch

                }   //for

            } catch (Exception e) {
                Log.d("test", "Update => " + e.toString());
            }

            intTimes += 1;
        }   // while


    }   //synJSON

    private void deleteAllData() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.user_table, null, null);
        sqLiteDatabase.delete(MyManage.tour_table, null, null);
    }

    private void testAddValue() {
        myManage.addUser("user", "pass", "name", "email");
        myManage.addTour("province", "district", "name", "Cat", "descrip", "image",
                "lat", "lng", "range");
    }

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }


}   // Main Class
