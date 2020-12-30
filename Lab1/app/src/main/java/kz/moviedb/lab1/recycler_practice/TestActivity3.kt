package kz.moviedb.lab1.recycler_practice

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.moviedb.lab1.R
import kz.moviedb.lab1.lesson2_sandbox.showToast

/**
 * Created by Sarsenov Yerlan on 31.12.2020.
 */
class TestActivity3 : AppCompatActivity() {

    private val labAdapter = LabAdapter(::onButtonClicked, ::onCheckBoxChecked)

    val list = mutableListOf<GeneralType>(
            GeneralType.TypeSimple("Text1"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text1"),
            GeneralType.TypeSimple("Text2"),
            GeneralType.TypeButton(),
            GeneralType.TypeSimple("Text3"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text2"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text3"),
            GeneralType.TypeSimple("Text4"),
            GeneralType.TypeCheckBox("Text4"),
            GeneralType.TypeSimple("Text5"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text5"),
            GeneralType.TypeSimple("Text6"),
            GeneralType.TypeButton(),
            GeneralType.TypeSimple("Text7"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text6"),
            GeneralType.TypeButton(),
            GeneralType.TypeCheckBox("Text7"),
            GeneralType.TypeSimple("Text8"),
            GeneralType.TypeCheckBox("Text8")
    )

    private fun onButtonClicked(position: Int) {
        showToast("Button $position")
    }

    private fun onCheckBoxChecked(position: Int, isChecked: Boolean) {
        val isCheckedText = if (isChecked) {
            "unchecked"
        } else {
            "checked"
        }
        showToast("$isCheckedText $position")
        (list[position] as GeneralType.TypeCheckBox).isChecked = isChecked
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        showToast("here")
        val recView = findViewById<RecyclerView>(R.id.recycler_view_)
        recView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recView.adapter = labAdapter
        labAdapter.list1 = list
    }

}