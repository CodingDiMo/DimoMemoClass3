package com.example.dimomemodemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dimomemodemo.data.MemoData
import com.example.dimomemodemo.data.ListViewModel

import kotlinx.android.synthetic.main.activity_list.*
import java.util.*

class ListActivity : AppCompatActivity() {

    private var viewModel: ListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        val fragmentTransation = supportFragmentManager.beginTransaction()
        fragmentTransation.replace(R.id.contentLayout, MemoListFragment())
        fragmentTransation.commit()

        viewModel = application!!.let {
            ViewModelProvider(viewModelStore, ViewModelProvider.AndroidViewModelFactory(it))
                .get(ListViewModel::class.java)
        }

        fab.setOnClickListener { view ->
            val intent = Intent(applicationContext, DetailActivity::class.java)
            startActivity(intent)
        }
    }

}
