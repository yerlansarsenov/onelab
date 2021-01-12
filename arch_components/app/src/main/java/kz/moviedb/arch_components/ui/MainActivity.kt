package kz.moviedb.arch_components.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.arch_components.R
import kz.moviedb.arch_components.db.CitationDataBase

class MainActivity : AppCompatActivity() {

    private val adapter: CitationListAdapter by lazy { CitationListAdapter() }

    private val viewModel: CitationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.rec_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(CustomDecoration())
//        CitationDataBase.getDB(applicationContext).citationDao().getAllCitationsLiveData()
//            .observe(this) { list ->
//                adapter.submitList(list)
//            }
        viewModel.liveDataCitation.observe(this) { list ->
            adapter.submitList(list)
        }
    }

}