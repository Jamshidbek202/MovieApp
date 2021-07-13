package com.jamshidbek.movieapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jamshidbek.movieapp.AddActivity
import com.jamshidbek.movieapp.EditActivity
import com.jamshidbek.movieapp.Model.MovieModel
import com.jamshidbek.movieapp.R
import com.jamshidbek.movieapp.Utils.MySharedPreference
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(var context: Context, var list: ArrayList<MovieModel>) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    inner class MyViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(model: MovieModel, position: Int) {
            itemView.txt_title.text = list[position].title
            itemView.txt_actors.text = list[position].actors
            itemView.txt_date.text = list[position].date

            itemView.btn_edit.setOnClickListener {
                val intent = Intent(context, EditActivity::class.java)
                intent.putExtra("model", model)
                intent.putExtra("position", position)

                context.startActivity(intent)
            }

            itemView.btn_delete.setOnClickListener {
                MySharedPreference.init(context)

                list.remove(list[position])

                MySharedPreference.objectString = list

                notifyDataSetChanged()

                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}