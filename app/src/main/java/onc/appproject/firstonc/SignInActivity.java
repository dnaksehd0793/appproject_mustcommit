package onc.appproject.firstonc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity  {
    //define view objects
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextName;
    Button buttonSignup;
    TextView textviewMessage;
    ProgressDialog progressDialog;

    RadioButton buttonSex_Male;
    RadioButton buttonSex_Female;
    Spinner spinner_height;
    Spinner spinner_location;
    //define firebase object
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    String region;
    String height;
    String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextName = (EditText) findViewById(R.id.editTextName);
        //textviewMessage = (TextView) findViewById(R.id.textviewMessage);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        progressDialog = new ProgressDialog(this);
        buttonSex_Male = (RadioButton) findViewById(R.id.buttonSex_Male);
        buttonSex_Female = (RadioButton) findViewById(R.id.buttonSex_Female);
        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup1);


        spinner_location = (Spinner)findViewById(R.id.spinner_location);
        region = (String)spinner_location.getSelectedItem();
        spinner_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1,
                                       int position, long id) {
                region = (String)spinner_location.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_height = (Spinner)findViewById(R.id.spinner_height);
        height = (String)spinner_height.getSelectedItem();
        spinner_height.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1,
                                       int position, long id) {
                height = (String)spinner_height.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.buttonSex_Male){
                    sex = buttonSex_Male.getText().toString();
                }else{
                    sex = buttonSex_Female.getText().toString();
                }
            }
        });
        //button click event
        buttonSignup.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }
    //Firebse creating a new user
    private void registerUser(){
        //사용자가 입력하는 email, password를 가져온다.
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        //email과 password가 비었는지 아닌지를 체크 한다.
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password를 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }

        //email과 password가 제대로 입력되어 있다면 계속 진행된다.
        progressDialog.setMessage("등록중입니다. 기다려 주세요...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            FirebaseUser user = task.getResult().getUser();
                            User userModel = new User(user.getEmail(),editTextName.getText().toString(),null,sex,region,height );
                          //  User inputUser = new User(user.getEmail(),editTextName.getText().toString(),null,null,region.getText().toString(),height.getText().toString());
                            // User(String useremail, String username, Team sosockteam, String sex, String downtown, String userheight)

                            databaseReference.child("users").child(user.getUid()).setValue( userModel );
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            //에러발생시
                            textviewMessage.setText("에러유형\n - 이미 등록된 이메일  \n -암호 최소 6자리 이상 \n - 서버에러");
                            Toast.makeText(SignInActivity.this, "등록 에러!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }


}
