package com.kevinnt.movapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.kevinnt.movapp.models.Checkout
import com.kevinnt.movapp.models.Film
import kotlinx.android.synthetic.main.activity_ticket.*

class TicketActivity : AppCompatActivity() {

    private lateinit var data:Film

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)
        data = intent.getParcelableExtra<Film>("data")!!
    }

    override fun onStart() {
        super.onStart()

        Glide.with(this)
            .load(data.poster)
            .into(iv_poster)

        tv_title.setText(data!!.judul)
        tv_genre.setText(data!!.genre)
        tv_rating.setText(data!!.rating)

        iv_back.setOnClickListener { onBackPressed() }

        rv_booked_seat.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        var seatlist = ArrayList<Checkout>()
        seatlist.add(Checkout("C1",70000))
        seatlist.add(Checkout("C2",70000))

        var adapter = RecyclerViewSeatBookedAdapter(this)
        rv_booked_seat.adapter = adapter
        adapter.bookSeat = seatlist
    }
}