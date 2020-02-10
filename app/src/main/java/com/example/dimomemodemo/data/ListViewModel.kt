package com.example.dimomemodemo.data

import androidx.lifecycle.ViewModel
import io.realm.Realm
import io.realm.RealmResults

class ListViewModel : ViewModel() {
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    private val memoDao: MemoDao by lazy {
        MemoDao(realm)
    }

    val memoLiveData: RealmLiveData<MemoData> by lazy {
        RealmLiveData<MemoData> (memoDao.getAllMemos())
    }

    override fun onCleared() {
        super.onCleared()
        realm.close()
    }
}