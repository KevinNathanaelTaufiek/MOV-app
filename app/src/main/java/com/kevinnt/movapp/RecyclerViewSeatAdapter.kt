package com.kevinnt.movapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kevinnt.movapp.models.Checkout

class RecyclerViewSeatAdapter(context: Context) : RecyclerView.Adapter<RecyclerViewSeatAdapter.ViewHolderBookedSeat>() {

    var bookSeat = ArrayList<Checkout>()
    private var context = context


    class ViewHolderBookedSeat(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var seat = itemView.findViewById<TextView>(R.id.tv_seat_list)
        var price = itemView.findViewById<TextView>(R.id.tv_seat_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBookedSeat {
        var view = LayoutInflater.from(context).inflate(R.layout.item_booking_list, parent, false)
        return ViewHolderBookedSeat(view)
    }

    override fun onBindViewHolder(holder: ViewHolderBookedSeat, position: Int) {
        holder.seat.text = bookSeat.get(position).seat
        holder.price.text = bookSeat.get(position).price.toString()
    }

    override fun getItemCount(): Int = bookSeat.size
}