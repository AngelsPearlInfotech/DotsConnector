package com.api.dotsconnector

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_dots.view.*

/**
 * Created by sabbir on 8/29/2017.
 */

class Dotsadptr(private val c: Context, private val lst: List<DotsHelper>, val dotsSelection: DotsSelection) : RecyclerView.Adapter<Dotsadptr.DotsViewHolder>() {
    var height: Int = 0
    var width: Int = 0

    // lateinit var dotsSelection : DotsSelection
    class DotsViewHolder(itemView: View?, dotsSelection: DotsSelection) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView!!.linear1.setOnClickListener({ v ->
                //Top
                dotsSelection.topSelection(itemView, adapterPosition);
            })
            itemView.linear2.setOnClickListener({ v ->
                //Left
                dotsSelection.leftSelection(itemView, adapterPosition);
            })
        }


        fun bind(item: DotsHelper) = with(itemView) {

            if ((adapterPosition + 1) % 5 == 0) {
                itemView.linear3.visibility = View.VISIBLE
                itemView.txtdot2.visibility = View.VISIBLE
            } else {
                itemView.linear3.visibility = View.GONE
                itemView.txtdot2.visibility = View.GONE
            }


            if (item.top) {
                itemView.line1.setBackgroundColor(ContextCompat.getColor(itemView.context, android.R.color.black))
            } else {
                itemView.line1.setBackgroundColor(ContextCompat.getColor(itemView.context, android.R.color.white))
            }
            if (item.left) {
                itemView.line2.setBackgroundColor(ContextCompat.getColor(itemView.context, android.R.color.black))
            } else {
                itemView.line2.setBackgroundColor(ContextCompat.getColor(itemView.context, android.R.color.white))
            }

            if(item.allset){
                itemView.lineText.visibility=View.VISIBLE
            }else{
                itemView.lineText.visibility=View.GONE
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotsViewHolder {
        return DotsViewHolder(parent.inflate(R.layout.layout_dots), dotsSelection)
    }

    override fun onBindViewHolder(holder: DotsViewHolder, position: Int) {
        return holder.bind(lst.get(position))
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    interface DotsSelection {
        fun topSelection(itemView: View, position: Int)
        fun leftSelection(itemView: View, position: Int)
    }

}
