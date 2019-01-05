package com.example.dhvanil.quizapp_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    public Map<String,String>myMap;
    public List<String> arrayList;
    public int count=0;
    ListView listView;
    List answerList;
    Random random;
    String rightdefn;
    ArrayAdapter<String> itemsAdapter;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        myMap=new HashMap<>();
        readfilesdata();
        goodfunction();
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
                String defn = parent.getItemAtPosition( position ).toString();
                if(defn==rightdefn){
                    Toast.makeText(getApplicationContext(),"Yeah you are right",Toast.LENGTH_SHORT ).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Booooh you are not right",Toast.LENGTH_SHORT ).show();
                }
                goodfunction();
            }
        } );
    }

    private void readfilesdata() {
        arrayList= new ArrayList<>();
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.stringdata));
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String[]  parts = line.split( "," ) ;
            myMap.put(parts[0], parts[1]);
            arrayList.add(parts[0]);
        }
        try {
            Scanner scan2 = new Scanner(openFileInput( "new_added.txt" ) );
            while(scan2.hasNextLine()) {
                String line = scan2.nextLine();
                String[]  parts = line.split( "," ) ;
                myMap.put(parts[0], parts[1]);
                arrayList.add(parts[0]);
            }
        } catch (Exception e) {
            //do nothing
        }
    }

    private void goodfunction() {
        Button button = findViewById( R.id.button );
        random = new Random();
        count = random.nextInt(myMap.size());
        button.setText(arrayList.get(count));
        answerList = new ArrayList<String>( myMap.values() );
        rightdefn=myMap.get( arrayList.get( count ) );
        answerList.remove( rightdefn );
        Collections.shuffle(answerList);
        answerList = answerList.subList( 0,4 );
        answerList.add( rightdefn );
        Collections.shuffle(answerList);
        itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, answerList);
        listView = (ListView)findViewById( R.id.listview);

        listView.setAdapter(itemsAdapter);
    }
}
