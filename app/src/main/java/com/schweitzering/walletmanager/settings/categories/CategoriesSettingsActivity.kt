package com.schweitzering.walletmanager.settings.categories

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.schweitzering.walletmanager.R

class CategoriesSettingsActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, CategoriesSettingsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_settings)
    }
}
