package com.charusat.sqlitedemo


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.card.view.*

class FruitAdapter(val context:Context,var arr:ArrayList<Fruit>)
    :RecyclerView.Adapter<FruitAdapter.ViewHolder>()

{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater=LayoutInflater.from(parent.context)
        var view= inflater.inflate(R.layout.card,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arr[position])
        holder.view.btnDelete.setOnClickListener {

            if(context is ViewAll)
            {
                context.delete(position)
            }
        }
        holder.view.btnUpdate.setOnClickListener {

            if(context is ViewAll)
            {
                context.update(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return  arr.size
    }

    class ViewHolder(var view:View):RecyclerView.ViewHolder(view)
    {
        fun bind(p:Fruit)
        {
            view.tvFrName.setText(p.Fr_Name)
            view.tvFrDes.setText(p.Fr_Des)
            view.tvFrPrice.setText(p.Fr_price.toString())

        }
    }
}