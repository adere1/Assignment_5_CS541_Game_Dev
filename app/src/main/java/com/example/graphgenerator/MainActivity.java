package com.example.graphgenerator;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    boolean dot = false;
    int nodeCount = 0;
    int currentNode = 1;
    List<String> list = new ArrayList<String>(Arrays.asList("Circle", "Box", "Diamond", "Ellipse", "Oval", "Doublecircle", "Square", "Parallelogram", "Trapezium", "Egg"));

    EditText editText_nodeCount, editText_nodeName, editText_dot, editText_myDot;
    Button button_submitCount, button_addNode, button_addEdge;
    TextView textView_que1, textView_Name, textView_Shape, textView_toNode, textView_fromNode;
    Spinner spinner1, spinner_fromNode, spinner_toNode;

    String nodeName, nodeShape, fromNode, toNode;
    String dotGenerated = "-------------------------------- Node Details -------------------------------";
    String myCreateDot = "digraph G {";
    int nodeID = 97;

    List<String> nodeNameList = new ArrayList<>();
    List<String> nodeShapeList = new ArrayList<>();


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

        spinner_fromNode = findViewById(R.id.spinner_fromNode);
        spinner_toNode = findViewById(R.id.spinner_toNode);

        textView_que1 = findViewById(R.id.textView_que1);
        textView_Name = findViewById(R.id.textView_Name);
        textView_Shape = findViewById(R.id.textView_Shape);
        textView_fromNode = findViewById(R.id.textView_fromNode);
        textView_toNode = findViewById(R.id.textView_toNode);

        editText_nodeCount = findViewById(R.id.editText_nodeCount);
        editText_nodeName = findViewById(R.id.editText_nodeName);
        editText_myDot = findViewById(R.id.editText_myDot);
        editText_dot = findViewById(R.id.editText_dot);

        button_addNode = findViewById(R.id.button_addNode);
        button_addEdge = findViewById(R.id.button_addEdge);
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

        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        if( dot == false) {
            dot = true;
            radioChange(true);
        }
    }


    public void radioNoDot(View view) {

        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        if( dot == true) {
            dot = false;
            radioChange(false);
        }
    }


    public void submitCount(View view) {

        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        nodeCount = Integer.parseInt(editText_nodeCount.getText().toString());

        if (nodeCount > 0 && nodeCount < 27) {
            textView_que1.setText("Please enter details for node " + currentNode + ", total nodes are " + nodeCount + " - ");

            textView_Name.setVisibility(View.VISIBLE);
            textView_Shape.setVisibility(View.VISIBLE);
            textView_fromNode.setVisibility(View.INVISIBLE);
            textView_toNode.setVisibility(View.INVISIBLE);

            spinner1.setVisibility(View.VISIBLE);
            spinner_fromNode.setVisibility(View.INVISIBLE);
            spinner_toNode.setVisibility(View.INVISIBLE);

            editText_nodeCount.setVisibility(View.INVISIBLE);
            editText_myDot.setVisibility(View.VISIBLE);
            editText_nodeName.setVisibility(View.VISIBLE);

            button_submitCount.setVisibility(View.INVISIBLE);
            button_addNode.setVisibility(View.VISIBLE);
            button_addEdge.setVisibility(View.INVISIBLE);

            dotGenerated = "-------------------------------- Node Details -------------------------------";
            editText_nodeName.setText("");
            editText_myDot.setText("");

            nodeID = 97;
            currentNode = 1;

            nodeNameList.clear();
            nodeShapeList.clear();
        }
        else
            Toast.makeText(this, "Total nodes should be between 1 and 27!", Toast.LENGTH_LONG).show();
    }


    public void radioChange(Boolean dotSelected) {

        currentNode = 1;

        textView_Name.setVisibility(View.INVISIBLE);
        textView_Shape.setVisibility(View.INVISIBLE);
        textView_fromNode.setVisibility(View.INVISIBLE);
        textView_toNode.setVisibility(View.INVISIBLE);

        editText_nodeName.setVisibility(View.INVISIBLE);
        editText_myDot.setVisibility(View.INVISIBLE);

        spinner1.setVisibility(View.INVISIBLE);
        spinner_fromNode.setVisibility(View.INVISIBLE);
        spinner_toNode.setVisibility(View.INVISIBLE);

        button_addNode.setVisibility(View.INVISIBLE);
        button_addEdge.setVisibility(View.INVISIBLE);

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

        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        if (currentNode <= nodeCount) {

            nodeName = editText_nodeName.getText().toString();

            if(nodeName.length() != 0) {

                textView_que1.setText("Please enter details for node " + currentNode + ", total nodes are " + nodeCount + " - ");
                currentNode++;
                textView_que1.setText("Please enter details for node " + currentNode + ", total nodes are " + nodeCount + " - ");

                dotGenerated = dotGenerated + "\nNode Title: " + nodeName + ", \tShape: " + nodeShape;

                myCreateDot = myCreateDot + "\"" + nodeName + "\"" + "[shape=\"" + nodeShape.toLowerCase() + "\"]; ";

                nodeNameList.add(nodeName);
                nodeShapeList.add(nodeShape);

                editText_myDot.setText(dotGenerated);
                editText_nodeName.setText("");
            }
            else
                Toast.makeText(this, "Please enter node name!", Toast.LENGTH_LONG).show();
        }


        if (currentNode > nodeCount) {

            textView_que1.setText("Please enter edge details between nodes - ");

            dotGenerated = dotGenerated + "\n\n----------------------------------- Edges ---------------------------------------";

            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item, nodeNameList);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_fromNode.setAdapter(adapter1);
            spinner_fromNode.setOnItemSelectedListener(this);

            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, nodeNameList);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_toNode.setAdapter(adapter2);
            spinner_toNode.setOnItemSelectedListener(this);

            textView_Name.setVisibility(View.INVISIBLE);
            textView_Shape.setVisibility(View.INVISIBLE);
            textView_fromNode.setVisibility(View.VISIBLE);
            textView_toNode.setVisibility(View.VISIBLE);

            spinner1.setVisibility(View.INVISIBLE);
            spinner_fromNode.setVisibility(View.VISIBLE);
            spinner_toNode.setVisibility(View.VISIBLE);

            editText_nodeCount.setVisibility(View.INVISIBLE);
            editText_myDot.setVisibility(View.VISIBLE);
            editText_nodeName.setVisibility(View.INVISIBLE);

            button_submitCount.setVisibility(View.INVISIBLE);
            button_addNode.setVisibility(View.INVISIBLE);
            button_addEdge.setVisibility(View.VISIBLE);
        }
    }


    public void  submitEdge(View view) {

        dotGenerated = dotGenerated + "\n" + fromNode + " -> " + toNode;
        editText_myDot.setText(dotGenerated);

        myCreateDot = myCreateDot + "\"" + fromNode + "\" -> \"" + toNode + "\"; ";
        System.out.println(myCreateDot);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {

            case R.id.spinner1:
                nodeShape = parent.getItemAtPosition(position).toString();
                break;

            case R.id.spinner_fromNode:
                fromNode = parent.getItemAtPosition(position).toString();
                break;

            case R.id.spinner_toNode:
                toNode = parent.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
