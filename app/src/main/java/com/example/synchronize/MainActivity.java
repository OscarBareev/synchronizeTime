package com.example.synchronize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Calendar morningTime;
    private Calendar dayTime;
    private Calendar eveningTime;
    private Calendar currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    private void init() {

        morningTime = new GregorianCalendar();
        morningTime.set(Calendar.HOUR_OF_DAY, 6);
        morningTime.set(Calendar.MINUTE, 0);
        morningTime.set(Calendar.SECOND, 0);

        dayTime = new GregorianCalendar();
        dayTime.set(Calendar.HOUR_OF_DAY, 14);
        dayTime.set(Calendar.MINUTE, 0);
        dayTime.set(Calendar.SECOND, 0);

        eveningTime = new GregorianCalendar();
        eveningTime.set(Calendar.HOUR_OF_DAY, 15);
        eveningTime.set(Calendar.MINUTE, 0);
        eveningTime.set(Calendar.SECOND, 0);

        currentTime = Calendar.getInstance();

        Button synchronizeBtn = findViewById(R.id.button);
        synchronizeBtn.setOnClickListener(synchronizeListener);
    }

    View.OnClickListener synchronizeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(Intent.ACTION_SYNC);

            if (currentTime.compareTo(morningTime) >= 0 && currentTime.compareTo(dayTime) < 0) {
                intent.setData(Uri.parse("http://morning"));

            } else if (currentTime.compareTo(dayTime) >= 0 && currentTime.compareTo(eveningTime) < 0) {
                intent.setData(Uri.parse("http://afternoon"));

            } else {
                intent.setData(Uri.parse("http://evening"));
            }

            startActivity(intent);
        }
    };

}