package com.example.graphgenerator;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
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
    Button button_submitCount, button_addNode, button_addEdge, submitDot, button_createNew;
    TextView textView_que1, textView_Name, textView_Shape, textView_toNode, textView_fromNode;
    Spinner spinner1, spinner_fromNode, spinner_toNode;
    WebView webView_graph;
    CardView cardView;

    String nodeName, nodeShape, fromNode, toNode;
    String dotGenerated = "-------------------------------- Node Details -------------------------------";
    String myCreateDot = "digraph G {";
    String finalDot;
    boolean inputDot = false;

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
        submitDot = findViewById(R.id.submit);
        button_createNew = findViewById(R.id.button_createNew);
        button_createNew.setVisibility(View.INVISIBLE);

        webView_graph = findViewById(R.id.webView_graph);
        webView_graph.setVisibility(View.INVISIBLE);

        cardView = findViewById(R.id.cardView);

        radioChange(false);

        submitDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView_graph.setVisibility(View.VISIBLE);
                webView_graph.setInitialScale(1);
                webView_graph.getSettings().setLoadWithOverviewMode(true);
                webView_graph.getSettings().setUseWideViewPort(true);

                submitDot.setVisibility(View.INVISIBLE);

                if(inputDot)
                    finalDot = editText_dot.getText().toString();
                else
                    finalDot = myCreateDot + "}";

                System.out.println(finalDot);
                String url = "http://34.73.253.18:8080/";
                webView_graph.setBackgroundColor(Color.WHITE);
                webView_graph.postUrl(url, finalDot.getBytes());

                spinner1.setVisibility(View.INVISIBLE);
                spinner_fromNode.setVisibility(View.INVISIBLE);
                spinner_toNode.setVisibility(View.INVISIBLE);

                textView_que1.setVisibility(View.INVISIBLE);
                textView_Name.setVisibility(View.INVISIBLE);
                textView_Shape.setVisibility(View.INVISIBLE);
                textView_fromNode.setVisibility(View.INVISIBLE);
                textView_toNode.setVisibility(View.INVISIBLE);

                editText_nodeCount.setVisibility(View.INVISIBLE);
                editText_nodeName.setVisibility(View.INVISIBLE);
                editText_myDot.setVisibility(View.INVISIBLE);
                editText_dot.setVisibility(View.INVISIBLE);

                button_addNode.setVisibility(View.INVISIBLE);
                button_addEdge.setVisibility(View.INVISIBLE);
                button_submitCount.setVisibility(View.INVISIBLE);
                submitDot.setVisibility(View.INVISIBLE);
                button_createNew.setVisibility(View.VISIBLE);

                cardView.setVisibility(View.INVISIBLE);
            }
        });
    }


    public void radioDot(View view) {

        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        if(dot == false) {
            dot = true;
            radioChange(true);
        }
    }


    public void radioNoDot(View view) {

        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        if(dot == true) {
            dot = false;
            radioChange(false);
        }
    }


    public void submitCount(View view) {

        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        nodeCount = Integer.parseInt(editText_nodeCount.getText().toString());

        if (nodeCount > 0) {
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

            currentNode = 1;

            nodeNameList.clear();
            nodeShapeList.clear();
        }
        else
            Toast.makeText(this, "Total nodes should be greater than 0!", Toast.LENGTH_LONG).show();
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

            inputDot = true;

            textView_que1.setText("Enter dot language input of your graph -");
            editText_dot.setVisibility(View.VISIBLE);
            editText_nodeCount.setVisibility(View.INVISIBLE);
            button_submitCount.setVisibility(View.INVISIBLE);
        }
        else {

            inputDot = false;

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
    }


    public void createNew(View view) {

        webView_graph.setVisibility(View.INVISIBLE);
        submitDot.setVisibility(View.VISIBLE);

        textView_que1.setVisibility(View.VISIBLE);
        submitDot.setVisibility(View.VISIBLE);
        button_createNew.setVisibility(View.INVISIBLE);

        cardView.setVisibility(View.VISIBLE);

        if(inputDot) {
            editText_dot.setVisibility(View.VISIBLE);
            editText_dot.setText("");
        }
        else {
            textView_que1.setVisibility(View.VISIBLE);
            editText_nodeCount.setVisibility(View.VISIBLE);
            button_submitCount.setVisibility(View.VISIBLE);
        }
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
