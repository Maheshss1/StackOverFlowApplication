package com.example.mahesh.stackoverflowapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.mahesh.stackoverflowapplication.support.Common;

public class AnswersOfQuestions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers_of_questions);

        Intent intent = getIntent();
        String url;
        if (intent!=null) {
            url = intent.getStringExtra(Common.Question_Url);
            WebView webView = findViewById(R.id.web_view);
            webView.loadUrl(url);
        }
    }
}
