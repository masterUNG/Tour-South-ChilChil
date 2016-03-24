package appewtc.masterung.toursouthchilchil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, emailEditText,
            userEditText, passwordEditText;
    private String nameString, emailString, userString,
            passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        bindWidget();

    }   // Main Method

    public void clickSignUpSign(View view) {

        nameString = nameEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (checkSpace()) {
            //Have Space
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(SignUpActivity.this, "มีช่องว่าง",
                    "กรุณากรอก ทุกช่องคะ");

        } else {
            //No Space

        }

    }   // clickSign

    private boolean checkSpace() {

        boolean bolResult = true;

        bolResult = nameString.equals("") || emailString.equals("") ||
                userString.equals("") || passwordString.equals("");

        return bolResult;
    }


    private void bindWidget() {
        nameEditText = (EditText) findViewById(R.id.editText3);
        emailEditText = (EditText) findViewById(R.id.editText4);
        userEditText = (EditText) findViewById(R.id.editText5);
        passwordEditText = (EditText) findViewById(R.id.editText6);
    }

}   // Main Class
