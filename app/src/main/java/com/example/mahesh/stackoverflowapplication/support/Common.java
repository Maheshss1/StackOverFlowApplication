package com.example.mahesh.stackoverflowapplication.support;

import com.example.mahesh.stackoverflowapplication.adapters.QuestionsAdapter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Common {

    public static final String TAB_TITLE = "tab_title";
    public static final String TAB_TITLE_TRENDING = "#YOURS";
    public static final String TAB_TITLE_HOT = "HOT";
    public static final String TAB_TITLE_WEEK = "WEEK";
    public static final String SITE = "stackoverflow";
    public static final String QUESTIONS_LIST = "QUESTION_LIST";
    public static String sort = "activity";

    public static QuestionsAdapter questionsAdapter = new QuestionsAdapter();




    private static Retrofit retrofitOverflow = null;
    private static Retrofit retrofitExchange = null;
    public static final String STACKOVERFLOWURL = "https://stackoverflow.com/";
    public static final String STACKEXCHANGEURL = "https://api.stackexchange.com/";
    public static String TAGGED = "Java";
    public static String Question_Url;

    public static Retrofit getRetrofitForStackOverFlow(String url){
        if (retrofitOverflow == null){
            retrofitOverflow = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitOverflow;
    }

    public static Retrofit getRetrofitForStackExchange(String url){
        if (retrofitExchange == null){
            retrofitExchange = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitExchange;
    }

    public static StackOverFlowAPI getStackOverFlowAPI(String url){
        if (url.equals(STACKOVERFLOWURL)){
            getRetrofitForStackOverFlow(url);
            return retrofitOverflow.create(StackOverFlowAPI.class);
        }
        else{
            getRetrofitForStackExchange(url);
            return retrofitExchange.create(StackOverFlowAPI.class);
        }


    }
}
