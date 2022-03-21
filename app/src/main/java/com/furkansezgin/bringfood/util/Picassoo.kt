package com.furkansezgin.bringfood.util

import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_item_row.view.*

 class Picassoo {

     companion object   {
         val BASE_URL = "http://kasimadalan.pe.hu/yemekler/resimler/"
         fun putImageWithPicasso(url: String, imageView: ImageView){
             Picasso.get()
                 .load(BASE_URL+url)
                 .resize(300, 300)
                 .centerCrop()
                 .into(imageView)

     }

    }
}