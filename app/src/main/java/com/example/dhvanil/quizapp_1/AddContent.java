package com.example.dhvanil.quizapp_1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class AddContent extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_content );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();
            }
        } );
    }

    public void addnew( View view ) throws IOException {
        EditText editText = findViewById( R.id.Addtxt );
        String addtext = editText.getText().toString();
        EditText editText1 = findViewById( R.id.add_answer );
        String adddefn = editText1.getText().toString();
        try {
            PrintStream output = new PrintStream( openFileOutput( "new_added.txt",MODE_PRIVATE|MODE_APPEND));
            output.println(addtext+","+adddefn);
            output.close();
        } catch (FileNotFoundException e) {
            File file = new File( "new_added.txt" );
            file.createNewFile();
        }

        finish();
    }
}
