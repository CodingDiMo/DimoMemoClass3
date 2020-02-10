package com.example.dimomemodemo.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import java.util.*

class DetailViewModel: ViewModel() {
    val title: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val content: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }

    private var memoData = MemoData()

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    private val memoDao: MemoDao by lazy {
        MemoDao(realm)
    }

    override fun onCleared() {
        super.onCleared()
        realm.close()
    }

    fun loadMemo(id: String) {
        memoData = memoDao.selectMemo(id)
        title.value = memoData.title
        content.value = memoData.content
    }

    fun addOrUpdateMemo(title: String, content: String) {
        memoDao.addOrUpdateMemo(memoData, title, content)
    }

}