package com.furkansezgin.bringfood.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import com.furkansezgin.bringfood.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chart, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val action = FoodFragmentDirections.actionFoodFragmentToBagFragment()
        Navigation.findNavController(this, R.id.fragmentContainerView).navigate(action)
        return super.onOptionsItemSelected(item)
    }
}