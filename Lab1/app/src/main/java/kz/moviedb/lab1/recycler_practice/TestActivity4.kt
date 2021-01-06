package kz.moviedb.lab1.recycler_practice

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.lab1.R
import kz.moviedb.lab1.lesson2_sandbox.showToast

/**
 * Created by Sarsenov Yerlan on 31.12.2020.
 */
class TestActivity4 : AppCompatActivity(R.layout.activity_recycler) {

    private val labAdapter = LabListAdapter(::onButtonClicked, ::onCheckBoxChecked, ::onLongClicked)

    var list = mutableListOf(
            GeneralType.TypeSimple("Text"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text"),
            GeneralType.TypeSimple("Text"),
            GeneralType.TypeButton(),
            GeneralType.TypeSimple("Text"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text"),
            GeneralType.TypeSimple("Text"),
            GeneralType.TypeCheckBox("Text"),
            GeneralType.TypeSimple("Text"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text"),
            GeneralType.TypeSimple("Text"),
            GeneralType.TypeButton(),
            GeneralType.TypeSimple("Text"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text"),
            GeneralType.TypeSimple("Text"),
            GeneralType.TypeCheckBox("Text"),
            GeneralType.TypeSimple("Text"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text"),
            GeneralType.TypeSimple("Text"),
            GeneralType.TypeButton(),
            GeneralType.TypeSimple("Text"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text"),
            GeneralType.TypeSimple("Text"),
            GeneralType.TypeCheckBox("Text"),
            GeneralType.TypeSimple("Text"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text"),
            GeneralType.TypeSimple("Text"),
            GeneralType.TypeButton(),
            GeneralType.TypeSimple("Text"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text"),
            GeneralType.TypeSimple("Text"),
            GeneralType.TypeCheckBox("Text")
    )

    private fun onButtonClicked(position: Int) {
        showToast("Button $position")
    }

    private fun onCheckBoxChecked(position: Int, isChecked: Boolean) {
        val isCheckedText = if (isChecked) {
            "checked"
        } else {
            "unchecked"
        }
        showToast("$isCheckedText $position")
        (list[position] as GeneralType.TypeCheckBox).isChecked = isChecked
    }

    private fun onLongClicked(position: Int) : Boolean{
        (list[position] as GeneralType.TypeSimple).name = "longClickedText"
        labAdapter.notifyItemChanged(position)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recView = findViewById<RecyclerView>(R.id.recycler_view_)
        //recView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        val spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (labAdapter.getItemViewType(position)) {
                    R.layout.it_recycler_practice_1 -> 1
                    R.layout.it_recycler_practice_2 -> 3
                    R.layout.it_recycler_practice_3 -> 1
                    else -> throw NotImplementedError()
                }
            }
        }
        layoutManager.spanSizeLookup = spanSizeLookup
        recView.layoutManager = layoutManager
        recView.adapter = labAdapter
        recView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        labAdapter.submitList(list)
    }

}
