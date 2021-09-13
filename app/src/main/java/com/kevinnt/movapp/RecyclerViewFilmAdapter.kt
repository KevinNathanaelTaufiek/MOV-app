package com.kevinnt.movapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kevinnt.movapp.models.Film
import java.util.ArrayList

class RecyclerViewFilmAdapter(dataFilm:ArrayList<Film>, rvType:Int, private val listener: (Film)->Unit): RecyclerView.Adapter<RecyclerViewFilmAdapter.ViewHolderFilm>() {

    private var filmList = dataFilm
    private lateinit var contextAdapter : Context
    private val rvType = rvType


    class ViewHolderFilm(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_poster:ImageView = itemView.findViewById(R.id.iv_poster)
        var tv_genre:TextView = itemView.findViewById(R.id.tv_genre)
        var tv_rating:TextView = itemView.findViewById(R.id.tv_rating)
        var tv_judul:TextView = itemView.findViewById(R.id.tv_judul)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFilm {
        contextAdapter = parent.context
        var view: View
        if (rvType == 0){
            view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_now_playing, parent, false)
        }
        else{
            view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_comming_soon, parent, false)
        }
        return ViewHolderFilm(view)
    }

    override fun onBindViewHolder(holder: ViewHolderFilm, position: Int) {
        holder.tv_judul.setText(filmList.get(position).judul)
        holder.tv_genre.setText(filmList.get(position).genre)
        holder.tv_rating.setText(filmList.get(position).rating)

        Glide.with(contextAdapter)
            .load(filmList.get(position).poster)
            .into(holder.iv_poster)

        holder.iv_poster.setOnClickListener {
            listener(filmList.get(position))
        }
    }

    override fun getItemCount() = filmList.size
}