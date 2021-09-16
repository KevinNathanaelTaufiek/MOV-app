package com.kevinnt.movapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kevinnt.movapp.models.Checkout

class RecyclerViewSeatBookedAdapter(context: Context) : RecyclerView.Adapter<RecyclerViewSeatBookedAdapter.ViewHolderBookedSeat>() {

    var bookSeat = ArrayList<Checkout>()
    private var context = context


    class ViewHolderBookedSeat(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var seat = itemView.findViewById<TextView>(R.id.tv_seat_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBookedSeat {
        var view = LayoutInflater.from(context).inflate(R.layout.item_booked_seat, parent, false)
        return ViewHolderBookedSeat(view)
    }

    override fun onBindViewHolder(holder: ViewHolderBookedSeat, position: Int) {
        holder.seat.text = "Seat No. " + bookSeat.get(position).seat
    }

    override fun getItemCount(): Int = bookSeat.size
}