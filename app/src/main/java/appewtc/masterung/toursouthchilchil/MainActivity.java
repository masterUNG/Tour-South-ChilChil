package appewtc.masterung.toursouthchilchil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);

        //Request Database
        myManage = new MyManage(this);

        //Test Add Value
        //testAddValue();

        //Delete All data
        deleteAllData();

        //Syn JSON to SQLIte
        synJSONtoSQLite();

    }   // Main Method

    public void clickSignInMain(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") || passwordString.equals("")) {
            //Have Space
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this, "มีช่องว่าง", "กรุณากรอกทุกช่อง คะ");

        } else {
            //No Space
            checkUser();
        }


    }   // clickSingIN

    private void checkUser() {

        try {

            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE WHERE User = " + "'" + userString + "'", null);
            cursor.moveToFirst();
            String[] resultStrings = new String[cursor.getColumnCount()];
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                resultStrings[i] = cursor.getString(i);
            }   // for
            cursor.close();

            //Check Password
            if (passwordString.equals(resultStrings[2])) {

                Toast.makeText(this, "ยินดีต้อนรับ " + resultStrings[3],
                        Toast.LENGTH_SHORT).show();

            } else {

                MyAlertDialog myAlertDialog = new MyAlertDialog();
                myAlertDialog.myDialog(this, "Password False",
                        "กรุณาพิมพ์ใหม่ คุณพิมพ์ Password ผิด");

            }

        } catch (Exception e) {
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this, "ไม่มี User นี้",
                    "ไม่มี " + userString + " ในฐานข้อมูลของเรา");
        }

    }   // checkUser


    private void synJSONtoSQLite() {

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        int intTimes = 0;
        while (intTimes <= 1) {

            //1 Create InputStream
            InputStream inputStream = null;
            String[] urlStrings = {"http://swiftcodingthai.com/saa/php_get_user_phichalai.php",
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
