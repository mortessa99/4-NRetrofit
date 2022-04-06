package com.example.a4_nretrofit.server

import com.example.a4_nretrofit.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    //Query
    @GET("movies")//حد فاصل بین BaseUrl تا موارد ارسالی :https://moviesapi.ir/api/v1/movies?page={page}
    fun getMoviesList(@Query("page") myPage:Int):Call<ResponseMoviesList>

    //Path
    @GET("movies/{movie_id}")
    fun getOneMovie(@Path("movie_id") movieId:Int):Call<ResponseOneMovie>

    //Query , Path
    @GET("genres/{genre_id}/movies")
    fun getMovieByGenre(@Path("genre_id") genreId:Int , @Query("page") page:Int):Call<ResponseMovieListByGenre>

    //Body
    @POST("register")
    fun userRegister(@Body bodyRegister: BodyRegister):Call<ResponseRegister>
}