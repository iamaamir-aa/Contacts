package com.example.contacts;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contacts.Database.MyDatabaseHelper;
import com.example.contacts.Model.Contact;

import java.io.IOException;
import java.util.Random;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendNewMessage extends AppCompatActivity {

    String randomSix(){
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000; // Generate a random number between 100,000 and 999,999
        return String.valueOf(randomNumber);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_new_message);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.setThreadPolicy( new StrictMode.ThreadPolicy.Builder().permitAll().build() );
        }


        Button sendMessage=findViewById(R.id.sendButton);
        EditText otpEditText=findViewById(R.id.otpEditText);
        String otp=randomSix();
        otpEditText.setText("Hi. Your OTP is:"+otp+".");

        Contact contact=getIntent().getParcelableExtra("contact");
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
		String twilioNumber="+14065718189";
		String yourMessageToSend="Hi. Your OTP is: "+otp+".";
                sendSms(twilioNumber,contact.getNumber(),yourMessageToSend);
            }

            private void sendSms(String fromNumber,String toNumber,String message){

                String ACCOUNT_SID = "YOUR_SID";
                String AUTH_TOKEN = "YOUR_TOKEN";

                OkHttpClient client = new OkHttpClient();
                String url = "https://api.twilio.com/2010-04-01/Accounts/"+ACCOUNT_SID+"/Messages.json";
                String base64EncodedCredentials = "Basic " + Base64.encodeToString((ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes(), Base64.NO_WRAP);

                RequestBody body = new FormBody.Builder()
                        .add("From", fromNumber)
                        .add("To", toNumber)
                        .add("Body", message)
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .header("Authorization", base64EncodedCredentials)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    Log.d(TAG, "sendSms: "+ response.body().string());
                     if(response.isSuccessful()){
                         MyDatabaseHelper dbHelper = new MyDatabaseHelper(SendNewMessage.this);
                         SQLiteDatabase db = dbHelper.getWritableDatabase();
                         ContentValues values = new ContentValues();
                         values.put("name", contact.getName());
                         values.put("otp",otp);
                         values.put("contact",contact.getNumber());
                         long newRowId = db.insert("contacts", null, values);

                         // Check if the insertion was successful
                         if (newRowId != -1) {
                             Toast.makeText(SendNewMessage.this, "Message sent and saved to Database", Toast.LENGTH_SHORT).show();
                         } else {
                             Toast.makeText(SendNewMessage.this, "Error Saving to Database", Toast.LENGTH_SHORT).show();

                         }
                         db.close();
                     }else{
                         Toast.makeText(SendNewMessage.this, "Failed to Send", Toast.LENGTH_SHORT).show();

                     }


                } catch (IOException e) { e.printStackTrace();

                    Toast.makeText(SendNewMessage.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }

            }










        });






    }
}