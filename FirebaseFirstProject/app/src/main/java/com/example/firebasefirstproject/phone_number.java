package com.example.firebasefirstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class phone_number extends AppCompatActivity {
    EditText mobile;
    Button button;
    String no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_layout);

        mobile = (EditText) findViewById(R.id.mobile);

        button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                no = mobile.getText().toString();
                if(no.isEmpty() || no.length() < 10){
                    Toast.makeText(phone_number.this,"Enter a valid phone number",Toast.LENGTH_SHORT).show();
                }
                else
                {
                Intent intent = new Intent(phone_number.this,VerifyMobile.class);
                intent.putExtra("mobile",no);
                startActivity(intent);
                Toast.makeText(phone_number.this,no,Toast.LENGTH_LONG).show();
            }}
        });


    }

//    private void validNo(String no){
//        if(no.isEmpty() || no.length() < 10){
//            mobile.setError("Enter a valid mobile");
//            mobile.requestFocus();
//            return;
//        }
//    }
}

