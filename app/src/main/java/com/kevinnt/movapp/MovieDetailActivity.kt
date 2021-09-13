package com.kevinnt.movapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kevinnt.movapp.models.Actors
import com.kevinnt.movapp.models.Film
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        var data = intent.getParcelableExtra<Film>("data")

        val myRef = Firebase.database.getReference("Film")
                .child(data!!.judul.toString())
                .child("play")

        var actors = ArrayList<Actors>()

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children){
                    var a: Actors? = ds.getValue(Actors::class.java)
                    actors.add(a!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        iv_back.setOnClickListener {
            onBackPressed()
        }

        tv_judul_detail.setText(data!!.judul)
        tv_genre_detail.setText(data!!.genre)
        tv_sinopsis.setText(data!!.desc)

        Glide.with(this)
                .load(data.poster)
                .into(iv_poster_detail)

        btn_pilih_bangku.setOnClickListener {
            var intent = Intent(this, SelectSeatActivity::class.java).putExtra("data",data)
            startActivity(intent)
        }

        rv_pemeran.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_pemeran.adapter = RecyclerViewPemeranAdapter(actors)
    }
}