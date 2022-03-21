package com.furkansezgin.bringfood.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkansezgin.bringfood.model.BagModel
import com.furkansezgin.bringfood.model.Food
import com.furkansezgin.bringfood.service.FoodAPI
import com.furkansezgin.bringfood.util.Url.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodDetailViewModel : ViewModel() {

    val foodLiveData = MutableLiveData<Food>()
    var detailLoader = MutableLiveData<Boolean>()
    var isAdd = MutableLiveData<Boolean>()
    private lateinit var bagModel: BagModel

    fun settingFood(food: Food) {
        foodLiveData.value = food
    }

    fun addFood(
        yemekAdi: String,
        yemekResimAdi: String,
        yemekFiyat: Int,
        yemekSiparisAdet: Int,
        kullaniciAdi: String
    ) {
        isAdd.value = false
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(FoodAPI::class.java)
        val call =
            service.setBagFood(yemekAdi, yemekResimAdi, yemekFiyat, yemekSiparisAdet, kullaniciAdi)
        call.enqueue(object : Callback<BagModel> {
            override fun onResponse(call: Call<BagModel>, response: Response<BagModel>) {
                if (response.isSuccessful) {
                    detailLoader.value = false
                    bagModel = response.body()!!
                    isAdd.value = true
                }
            }

            override fun onFailure(call: Call<BagModel>, t: Throwable) {
                isAdd.value = false
            }
        })
    }
}