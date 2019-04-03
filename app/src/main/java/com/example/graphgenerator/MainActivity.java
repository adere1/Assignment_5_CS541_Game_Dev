package com.example.graphgenerator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);







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

        TextView textView_que1 = findViewById(R.id.textView_que1);
        textView_que1.setText("Enter dot language input of your graph -");

        EditText editText_dot = findViewById(R.id.editText_dot);
        editText_dot.setVisibility(View.VISIBLE);
    }

    public void radioNoDot(View view) {

        TextView textView_que1 = findViewById(R.id.textView_que1);
        textView_que1.setText("How many nodes your graph have?");

        EditText editText_dot = findViewById(R.id.editText_dot);
        editText_dot.setVisibility(View.INVISIBLE);
    }



}
