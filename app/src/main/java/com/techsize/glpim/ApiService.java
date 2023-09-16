package com.techsize.glpim;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("/initSession/") // Substitua pela rota real da sua API de login
    Call<ApiResponse> loginUser(@Field("login") String login,
                                @Field("password") String password);
}
