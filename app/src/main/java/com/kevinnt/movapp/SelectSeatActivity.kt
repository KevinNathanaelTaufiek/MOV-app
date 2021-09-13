package com.kevinnt.movapp

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.kevinnt.movapp.models.Checkout
import com.kevinnt.movapp.models.Film
import kotlinx.android.synthetic.main.activity_select_seat.*

class SelectSeatActivity : AppCompatActivity() {

    private var statusA3 = false
    private var statusA4 = false
    private var total = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_seat)

        val data = intent.getParcelableExtra<Film>("data")
        tv_title.text = data!!.judul

        var bookingList = ArrayList<Checkout>()

        btn_beli.setOnClickListener {
            var intent = Intent(this, CheckoutActivity::class.java).putExtra("data",data)
            intent.putExtra("bookingList",bookingList)
            startActivity(intent)
        }

        a3.setOnClickListener {
            if(statusA3){
                a3.setImageResource(R.drawable.ic_empty)
                total--
                if(total == 0){
                    btn_beli.visibility = View.INVISIBLE
                }
                else{
                    btn_beli.visibility = View.VISIBLE
                    btn_beli.setText("Beli tiket ($total)")
                }
                statusA3 = !statusA3
                bookingList.remove(Checkout("a3", 70000))
            }
            else{
                a3.setImageResource(R.drawable.ic_selected)
                total++
                btn_beli.visibility = View.VISIBLE
                btn_beli.setText("Beli tiket ($total)")
                statusA3 = !statusA3
                bookingList.add(Checkout("a3", 70000))
            }
        }

        a4.setOnClickListener {
            if(statusA4){
                a4.setImageResource(R.drawable.ic_empty)
                total--
                if(total == 0){
                    btn_beli.visibility = View.INVISIBLE
                }
                else{
                    btn_beli.visibility = View.VISIBLE
                    btn_beli.setText("Beli tiket ($total)")
                }
                statusA4 = !statusA4

                bookingList.remove(Checkout("a4", 70000))
            }
            else{
                a4.setImageResource(R.drawable.ic_selected)
                total++
                btn_beli.visibility = View.VISIBLE
                btn_beli.setText("Beli tiket ($total)")
                statusA4 = !statusA4
                bookingList.add(Checkout("a4", 70000))
            }
        }

    }
}