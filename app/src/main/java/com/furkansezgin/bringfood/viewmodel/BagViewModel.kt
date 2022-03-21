package com.furkansezgin.bringfood.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkansezgin.bringfood.model.Bag
import com.furkansezgin.bringfood.model.BagModel
import com.furkansezgin.bringfood.service.FoodAPI
import com.furkansezgin.bringfood.util.Url.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BagViewModel : ViewModel() {
    val bagFoods = MutableLiveData<List<Bag>>()
    var isDelete = MutableLiveData<Boolean>()
    var bagLoader = MutableLiveData<Boolean>()
    private lateinit var bagModel: BagModel
    fun getBag() {
        bagLoader.value = true
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(FoodAPI::class.java)
        val call = service.getBagFood("furkan_sezgin")
        call.enqueue(object : Callback<BagModel> {
            override fun onResponse(call: Call<BagModel>, response: Response<BagModel>) {
                if (response.isSuccessful) {
                    bagModel = response.body()!!
                    bagFoods.value = bagModel.bag
                    bagLoader.value = false
                }else{
                    println("abcdef ${bagFoods.value}")
                }
            }

            override fun onFailure(call: Call<BagModel>, t: Throwable) {
                bagLoader.value = false
                bagFoods.value = emptyList()
            }
        })
    }
    fun deleteFood(sepetYemekId: Int, kullaniciAdi: String) {
        isDelete.value = false
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(FoodAPI::class.java)
        val call = service.deleteFood(sepetYemekId, kullaniciAdi)
        call.enqueue(object : Callback<BagModel> {
            override fun onResponse(call: Call<BagModel>, response: Response<BagModel>) {
                if (response.isSuccessful) {
                    isDelete.value = true
                    bagLoader.value = false
                }
            }

            override fun onFailure(call: Call<BagModel>, t: Throwable) {
                isDelete.value = false
            }
        })
    }

}