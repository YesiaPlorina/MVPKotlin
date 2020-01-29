package com.yesia.mvpapp.network

import com.yesia.mvpapp.model.modelAuth.ResponseAuth
import com.yesia.mvpapp.model.modelWisata.ResponseWisata
import retrofit2.Call
import retrofit2.http.*

interface RestApi {

    @FormUrlEncoded
    @POST("registeruser.php")
    fun registerUser(
        @Field("vsnama") vsnama:String?,
        @Field("vsalamat")vsalamat:String?,
        @Field("vsjenkel")vsjenkel:String?,
        @Field("vsnotelp")vsnotelp:String?,
        @Field("vsusia")vsusia:String?,
        @Field("vsusername")vsusername:String?,
        @Field("vslevel")vslevel:String?,
        @Field("vspassword")vspassword:String?

    ) : Call<ResponseAuth>

    @FormUrlEncoded
    @POST("loginuser.php")
    fun loginUser(
        @Field("edtusername") edtusername:String?,
        @Field("edtpassword")edtpassword:String?,
        @Field("vslevel")vslevel:String?

    ) : Call<ResponseAuth>

    @GET("?action=findAll")
    fun getWisata(): Call<ResponseWisata>
}