package com.example.graphgenerator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    boolean dot = false;
    int nodeCount = 0;
    int currentNode = 1;
    List<String> list = new ArrayList<String>(Arrays.asList("Box", "Circle", "Diamond", "Ellipse", "Oval", "Doublecircle", "Square", "Parallelogram", "Trapezium", "Egg"));

    EditText editText_nodeCount, editText_nodeName, editText_dot, editText_myDot;
    Button button_submitCount, button_addNode;
    TextView textView_que1, textView_Name, textView_Shape;
    Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        textView_que1 = findViewById(R.id.textView_que1);
        textView_Name = findViewById(R.id.textView_Name);
        textView_Shape = findViewById(R.id.textView_Shape);

        editText_nodeCount = findViewById(R.id.editText_nodeCount);
        editText_nodeName = findViewById(R.id.editText_nodeName);
        editText_myDot = findViewById(R.id.editText_myDot);
        editText_dot = findViewById(R.id.editText_dot);

        button_addNode = findViewById(R.id.button_addNode);
        button_submitCount = findViewById(R.id.button_submitCount);

        radioChange(false);

        final Button submit = findViewById(R.id.submit);
       /* submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebView webview = findViewById(R.id.webview);
                webview.setVisibility(View.VISIBLE);
                submit.setVisibility(View.INVISIBLE);
                //setContentView(webview);
                String url = "http://192.168.1.179:8080";
                String postData = "digraph G {" +
                        "a -> b -> c;" +
                        "a-> c;" +
                        "b -> d;" +
                        "c-> d;" +
                        "}";

                webview.setBackgroundColor(Color.WHITE);
                webview.postUrl(url,postData.getBytes());
            }
        }); */
    }

    public void radioDot(View view) {

        if( dot == false) {
            dot = true;
            radioChange(true);
        }
    }

    public void radioNoDot(View view) {

        if( dot == true) {
            dot = false;
            radioChange(false);
        }
    }


    public void submitCount(View view) {

        nodeCount = Integer.parseInt(editText_nodeCount.getText().toString());
        textView_que1.setText("Please enter details for node " + currentNode + " - ");

        textView_Name.setVisibility(View.VISIBLE);
        textView_Shape.setVisibility(View.VISIBLE);

        spinner1.setVisibility(View.VISIBLE);

        editText_nodeCount.setVisibility(View.INVISIBLE);
        editText_myDot.setVisibility(View.VISIBLE);
        editText_nodeName.setVisibility(View.VISIBLE);

        button_submitCount.setVisibility(View.INVISIBLE);
        button_addNode.setVisibility(View.VISIBLE);
    }



    public void radioChange(Boolean dotSelected) {

        textView_Name.setVisibility(View.INVISIBLE);
        textView_Shape.setVisibility(View.INVISIBLE);

        editText_nodeName.setVisibility(View.INVISIBLE);
        editText_myDot.setVisibility(View.INVISIBLE);

        spinner1.setVisibility(View.INVISIBLE);
        button_addNode.setVisibility(View.INVISIBLE);

        if (dotSelected) {

            textView_que1.setText("Enter dot language input of your graph -");
            editText_dot.setVisibility(View.VISIBLE);
            editText_nodeCount.setVisibility(View.INVISIBLE);
            button_submitCount.setVisibility(View.INVISIBLE);
        }
        else {

            textView_que1.setText("How many nodes your graph have?");
            editText_dot.setVisibility(View.INVISIBLE);
            editText_nodeCount.setVisibility(View.VISIBLE);
            button_submitCount.setVisibility(View.VISIBLE);
        }

    }



    public void submitNode(View view) {

        if (currentNode <= nodeCount) {

        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
