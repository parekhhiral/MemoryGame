package com.parekhhiral.memorygame

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.min

class MemoryGameAdapter(val context: Context, val numCount: Int):
    RecyclerView.Adapter<MemoryGameAdapter.ViewHolder>() {

    companion object {
        private const val MARGIN_SIZE = 10
        private const val TAG = "MemoryGameAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // we want to fill up the whole screen with total cards evenly,
        // so taking parent view's width divide by number of columns
        // and parent view's height divide by number of rows
        // we are also subtracting (2*margins) (top and bottom margins) to keep some space in between
        val cardWidth = parent.width / 2 - (2 * MARGIN_SIZE)
        val cardHeight = parent.height / 4 - (2 * MARGIN_SIZE)
        val minMeasurement = min(cardWidth, cardHeight)

        // setting the min of height, width to the cardview and set margins on its layout params
        val view: View = LayoutInflater.from(context).inflate(R.layout.memory_card, parent, false)
        val layoutParams = view.findViewById<CardView>(R.id.cardView).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.height = minMeasurement
        layoutParams.width = minMeasurement
        layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)
        return ViewHolder(view)
    }

    override fun getItemCount() = numCount

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(position)
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            TODO("Not yet implemented")
        }
    }
}
