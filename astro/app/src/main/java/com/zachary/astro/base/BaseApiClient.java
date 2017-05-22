package com.zachary.astro.base;

import com.zachary.astro.service.AstroService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tongcheefei on 22/05/2017.
 */

public class BaseApiClient {
    private static AstroService service;

    public static AstroService getAstroService(){
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ams-api.astro.com.my/ams/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(AstroService.class);
        }
        return service;
    }
}
