package kz.moviedb.movieapp.ui.work_manager_ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.movieapp.R
import kz.moviedb.movieapp.db.CitationDataBase

class MainActivity : AppCompatActivity() {

    private val adapter: CitationListAdapter by lazy { CitationListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_work_manager)
        val recyclerView = findViewById<RecyclerView>(R.id.rec_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            CustomDecoration()
        )
        CitationDataBase.getDB(applicationContext).citationDao().getAllCitationsLiveData()
            .observe(this) { list ->
                adapter.submitList(list)
            }
    }

}