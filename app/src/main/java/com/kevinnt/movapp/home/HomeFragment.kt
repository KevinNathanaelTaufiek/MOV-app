package com.kevinnt.movapp.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kevinnt.movapp.MovieDetailActivity
import com.kevinnt.movapp.models.Film
import com.kevinnt.movapp.R
import com.kevinnt.movapp.RecyclerViewFilmAdapter
import com.kevinnt.movapp.utils.Preferences
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList

class HomeFragment : Fragment() {

    private lateinit var pref: Preferences
    private val database = Firebase.database
    private val myRef = database.getReference("Film")
    private var filmList = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = Preferences(activity!!.applicationContext)


        tv_nama.setText(pref.getValue("nama"))
        tv_saldo.setText(pref.getValue("saldo"))

        Glide.with(this)
            .load(pref.getValue("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)


        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (data in dataSnapshot.children){
                    var film = data.getValue(Film::class.java)
                    filmList.add(film!!)
                }

                rv_now_playing.layoutManager = LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.HORIZONTAL, false)
                rv_now_playing.adapter = RecyclerViewFilmAdapter(filmList,0){
                    var intent = Intent(context,MovieDetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }

                rv_comming_soon.layoutManager = LinearLayoutManager(activity!!.applicationContext)
                rv_comming_soon.adapter = RecyclerViewFilmAdapter(filmList,1){

                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity!!.applicationContext, "Data Film gagal diambil", Toast.LENGTH_SHORT).show()
            }
        })
    }

}