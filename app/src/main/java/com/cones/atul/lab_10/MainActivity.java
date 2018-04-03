package com.cones.atul.lab_10;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = findViewById(R.id.phone);
    }

    public void placeCall(View v){
        String phoneUri = "tel:"+phone.getText().toString();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(phoneUri));

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Failed! Grant permission to continue",Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(callIntent);
    }

    public void sendSMS(View v){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"Failed! Grant permission to continue",Toast.LENGTH_SHORT).show();
            return;
        }
        SmsManager mgr = SmsManager.getDefault();
        mgr.sendTextMessage(phone.getText().toString(),null,"This is an automated message.",null,null);
        Toast.makeText(this,"Sent successfully!",Toast.LENGTH_SHORT).show();
    }
}
