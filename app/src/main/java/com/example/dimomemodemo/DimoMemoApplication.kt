package com.example.dimomemodemo

import android.app.Application
import io.realm.Realm

class DimoMemoApplication() : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}