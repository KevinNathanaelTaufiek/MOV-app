package com.kevinnt.movapp.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kevinnt.movapp.R
import com.kevinnt.movapp.RecyclerViewFilmAdapter
import com.kevinnt.movapp.TicketActivity
import com.kevinnt.movapp.models.Film
import com.kevinnt.movapp.utils.Preferences
import kotlinx.android.synthetic.main.fragment_ticket.*

class TicketFragment : Fragment() {

//    private val preferences = Preferences(context!!)
    private val ref = Firebase.database.getReference("Film")
    private var datalist = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_ticket_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                datalist.clear()
                for (data in dataSnapshot.children){
                    var film = data.getValue(Film::class.java)
                    datalist.add(film!!)
                }
                rv_ticket_list.adapter = RecyclerViewFilmAdapter(datalist,1){
                    var intent = Intent(context, TicketActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }

                tv_total_movie.setText("${datalist.size} Movies")
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show();
            }
        })



    }
}