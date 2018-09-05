package com.android.alucard.services;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btStart, btStop, btIntentService;
    Animation animFadeIn;
    Spinner spinner;

    //service lifecycle .. created / destroyed

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btStart = findViewById(R.id.btStart);
        btStop = findViewById(R.id.btStop);
        btIntentService = findViewById(R.id.btIntentService);
        animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        spinner = findViewById(R.id.spinner);
        spinner.setPrompt("Countries");

//        // Spinner Drop down elements
//        List<String> countries = new ArrayList<>();
//        //countries.add("Countries");
//        countries.add("Romania");
//        countries.add("United Kingdom");
//        countries.add("Germany");
//        countries.add("Holland");
//        countries.add("Canada");

        //spinner.setPrompt("Select a Country!");

        //create adapter for spinner
        ArrayAdapter<String> spinnerApapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                        View v = super.getView(position, convertView, parent);
                        if (position == getCount()) {
                            ((TextView)v.findViewById(android.R.id.text1)).setText("");
                            ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                        }
                        return v;
                    }

                    @Override
                    public int getCount() {
                        return super.getCount() -1; // you don't display last item. It is used as hint.
                    }
                };

        spinnerApapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerApapter.add("Romania");
        spinnerApapter.add("United Kingdom");
        spinnerApapter.add("Germany");
        spinnerApapter.add("Holland");
        spinnerApapter.add("Canada");
        spinnerApapter.add("Choose a Country"); //This is the text that will be displayed as hint.



        //setting the adapter
        spinner.setAdapter(spinnerApapter);
        spinner.setSelection(spinnerApapter.getCount());


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position !=0 ) {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btStart.startAnimation(animFadeIn);

                //explicit
                Intent i = new Intent(MainActivity.this, MyService.class);
                startService(i);

            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MyService.class);
                stopService(i);
            }
        });

        btIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MyIntentService.class);
                i.putExtra("drago", "Dragos");
                startService(i);
            }
        });


    }
}
