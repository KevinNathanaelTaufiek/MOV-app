package com.kevinnt.movapp

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevinnt.movapp.models.Checkout
import com.kevinnt.movapp.models.Film
import com.kevinnt.movapp.utils.Preferences
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_select_seat.*

class CheckoutActivity : AppCompatActivity() {

    private var bookinglist = ArrayList<Checkout>()
    private var total = 0
    lateinit var preferences:Preferences
    lateinit private var data:Film

    var bayar = View.OnClickListener {
        var intent = Intent(this, CheckoutSuccessActivity::class.java)
        startActivity(intent)
    }

    var back = View.OnClickListener {
        onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        btn_bayar.setOnClickListener(bayar)
        btn_batal.setOnClickListener(back)
        iv_back_page.setOnClickListener(back)

        preferences = Preferences(this)
        bookinglist = intent.getParcelableArrayListExtra<Checkout>("bookingList") as ArrayList<Checkout>
        data = intent.getParcelableExtra<Film>("data")!!
        tv_judul_checkout.text = data.judul

        for ( i in bookinglist){
            total += i.price!!
        }

        rv_booked.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        var adapter = RecyclerViewSeatAdapter(this)
        adapter.bookSeat = bookinglist
        rv_booked.adapter = adapter


        tv_total.text = total.toString()

        tv_balance.text = "Rp. " + preferences.getValue("saldo")

    }

}