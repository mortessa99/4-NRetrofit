package com.example.a4_nretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a4_nretrofit.databinding.ActivityMainBinding
import com.example.a4_nretrofit.model.ResponseMoviesList
import com.example.a4_nretrofit.server.ApiClient
import com.example.a4_nretrofit.server.ApiServices
import com.example.a4_nretrofit.utils.MoviesAdapter
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val moviesAdapter by lazy { MoviesAdapter() }
    private val api: ApiServices by lazy {
        ApiClient().getClient().create(ApiServices::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            //show progress
            progressBar.visibility = View.VISIBLE

            //call api
            val call = api.getMoviesList(1)

            //call back (responses)
            call.enqueue(object : Callback<ResponseMoviesList>{
                override fun onResponse(call: Call<ResponseMoviesList>, response: Response<ResponseMoviesList>) {
                    progressBar.visibility = View.GONE
                    if (response.isSuccessful) {
                        response.body()?.let { itBody->
                            itBody.data?.let { itData->
                                if (itData.isNotEmpty()) {
                                    moviesAdapter.differ.submitList(itData)
                                    recyclerView.apply {
                                        layoutManager = LinearLayoutManager(this@MainActivity)
                                        adapter = moviesAdapter
                                    }
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseMoviesList>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    Log.i("Fail","${t.message}")
                }

            })
        }
    }
}