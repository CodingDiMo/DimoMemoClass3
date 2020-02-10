package com.example.dimomemodemo

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dimomemodemo.data.DetailViewModel
import com.example.dimomemodemo.data.ListViewModel
import com.example.dimomemodemo.data.MemoData
import com.example.dimomemodemo.data.MemoListAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.dialog_title.*
import kotlinx.android.synthetic.main.fragment_memo_list.*

class DetailActivity : AppCompatActivity() {

    private var viewModel: DetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        viewModel = application!!.let {
            ViewModelProvider(viewModelStore, ViewModelProvider.AndroidViewModelFactory(it))
                .get(DetailViewModel::class.java)
        }

        viewModel!!.let {
            it.title.observe(this, Observer { supportActionBar?.title = it } )
            it.content.observe(this, Observer { contentEdit.setText(it) } )
        }

        val memoId = intent.getStringExtra("MEMO_ID")
        if(memoId != null) viewModel!!.loadMemo(memoId)

        toolbarLayout.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.dialog_title, null)
            val titleEdit = view.findViewById<EditText>(R.id.titleEdit)

            AlertDialog.Builder(this)
                .setTitle("제목을 입력하세요")
                .setView(view)
                .setNegativeButton("취소", null)
                .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                    supportActionBar?.title = titleEdit.text.toString()
                }).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        viewModel?.addOrUpdateMemo(
            supportActionBar?.title.toString(),
            contentEdit.text.toString()
        )
    }
}
