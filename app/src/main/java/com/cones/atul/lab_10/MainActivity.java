package com.cones.atul.lab_10;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.InputType;
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
        if (phone.getText().toString().equals("")){
            Toast.makeText(this,"Cant message empty number",Toast.LENGTH_SHORT).show();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter message");
        final EditText input = new EditText(MainActivity.this);
        input.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
        builder.setView(input);
        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SmsManager mgr = SmsManager.getDefault();
                mgr.sendTextMessage(phone.getText().toString(), null, input.getText().toString(), null, null);
                Toast.makeText(getApplicationContext(), "Sent successfully!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }
}
