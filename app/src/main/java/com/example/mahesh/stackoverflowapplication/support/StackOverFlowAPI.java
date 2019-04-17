package com.example.mahesh.stackoverflowapplication.support;

import com.example.mahesh.stackoverflowapplication.model.ListOfQuestions;
import com.example.mahesh.stackoverflowapplication.model.ListOfTags;
import com.example.mahesh.stackoverflowapplication.model.LoginDetails;
import com.example.mahesh.stackoverflowapplication.model.Tags;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface StackOverFlowAPI {

    //client_id = 14887
    //scope = write_access
    //redirect_uri = https://stackexchange.com/oauth/login_success
    //code = xi8AA7W409i4mniwLNnGqQ))

    //https://stackoverflow.com/oauth/access_token/json?client_id=14887&client_secret=LssV5TBehHTpcGMTc4Ltpw((&code=W7Vf(uGTPt3mz11DZrdr8A))&redirect_uri=https://stackexchange.com/oauth/login_success
    @Headers(value="accept:application/json")
    @POST("oauth/access_token/json")
    //Call<LoginDetails> login(@Query("client_id") int id, @Query("scope") String scope,@Query("redirect_uri") String uri);
    Call<LoginDetails> login(@Query("client_id") int id, @Query("client_secret") String secret, @Query("code") String code, @Query("redirect_uri") String uri);

    @GET("2.2/tags?order=desc&sort=popular&site=stackoverflow")
    Call<ListOfTags> getPopularTags();

    @GET("2.2/questions?order=desc")
    Call<ListOfQuestions> getListOfQuestions(@Query("sort") String sort, @Query("tagged") String tagged, @Query("site") String siteName);
}
