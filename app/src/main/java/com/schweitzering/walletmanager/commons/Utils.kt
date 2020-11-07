package com.schweitzering.walletmanager.commons

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.schweitzering.walletmanager.R

fun addItemsToSpinner(context: Context, spinner: Spinner, items: List<String>) {
    val arrayAdapter =
        ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, items)
    spinner.adapter = arrayAdapter
}