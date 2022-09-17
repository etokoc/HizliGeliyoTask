package com.etoertugrul.hizligeliyotask.util

import android.content.Context
import android.widget.Toast

fun String.show(context: Context) {
    Toast.makeText(context.applicationContext, this, Toast.LENGTH_SHORT).show()
}