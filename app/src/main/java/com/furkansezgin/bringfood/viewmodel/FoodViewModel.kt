package com.furkansezgin.bringfood.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkansezgin.bringfood.model.Food
import com.furkansezgin.bringfood.model.FoodModel
import com.furkansezgin.bringfood.service.FoodAPI
import com.furkansezgin.bringfood.util.Url.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodViewModel : ViewModel() {
    val foods = MutableLiveData<List<Food>>()
    var foodloader = MutableLiveData<Boolean>()
    private lateinit var foodModel: FoodModel

    fun getData() {
        foodloader.value = true
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(FoodAPI::class.java)
        val call = service.getFood()
        call.enqueue(object : Callback<FoodModel> {
            override fun onResponse(call: Call<FoodModel>, response: Response<FoodModel>) {
                if (response.isSuccessful) {
                    foodloader.value = false
                    foodModel = response.body()!!
                    foods.value = foodModel.food

                }
            }
            override fun onFailure(call: Call<FoodModel>, t: Throwable) {

            }
        })
    }


}