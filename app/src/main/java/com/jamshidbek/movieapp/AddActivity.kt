package com.jamshidbek.movieapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.BaseAdapter
import android.widget.Toast
import com.jamshidbek.movieapp.Adapter.MovieAdapter
import com.jamshidbek.movieapp.Model.MovieModel
import com.jamshidbek.movieapp.Utils.MySharedPreference
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_item.*

class AddActivity : AppCompatActivity() {

    lateinit var list: ArrayList<MovieModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        MySharedPreference.init(this)

        list = ArrayList()
        list = MySharedPreference.objectString

        val model = intent.getSerializableExtra("model")
        val position = intent.getIntExtra("position", -1)

        if (model != null && position != -1) {

            var title = list[position].title
            var authors = list[position].actors
            var about = list[position].desc
            var date = list[position].date

            edt_title.setText(title)
            edt_authors.setText(authors)
            edt_aboutMovie.setText(about)
            edt_date.setText(date)
        }

        btn_save.setOnClickListener {
            val title = edt_title.text.toString().trim()
            val actors = edt_authors.text.toString().trim()
            val about = edt_aboutMovie.text.toString().trim()
            val date = edt_date.text.toString().trim()

            if (title != "" && actors != "" && about != "" && date != "") {
                list = MySharedPreference.objectString
                list.add(MovieModel(title, date, actors, about))
                MySharedPreference.objectString = list

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Fill the form!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}