package com.kevinnt.movapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kevinnt.movapp.models.Actors
import com.kevinnt.movapp.models.Film
import java.util.ArrayList

class RecyclerViewPemeranAdapter(dataActor:ArrayList<Actors>): RecyclerView.Adapter<RecyclerViewPemeranAdapter.ViewHolderActors>() {

    private var aktor = dataActor
    private lateinit var contextAdapter : Context


    class ViewHolderActors(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_pemeran:ImageView = itemView.findViewById(R.id.iv_pemeran)
        var tv_nama_pemeran:TextView = itemView.findViewById(R.id.tv_nama_pemeran)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderActors {
        contextAdapter = parent.context
        var view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_actor, parent, false)
        return ViewHolderActors(view)
    }

    override fun onBindViewHolder(holder: ViewHolderActors, position: Int) {
        holder.tv_nama_pemeran.setText(aktor.get(position).nama)

        Glide.with(contextAdapter)
            .load(aktor.get(position).url)
                .apply(RequestOptions.circleCropTransform())
            .into(holder.iv_pemeran)

//        holder.iv_pemeran.setOnClickListener {
//            listener(aktor.get(position))
//        }
    }

    override fun getItemCount() = aktor.size
}