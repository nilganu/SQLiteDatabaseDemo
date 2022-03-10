package com

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cahrusat.sqlitedemodiv1.MainActivity
import com.cahrusat.sqlitedemodiv1.R
import kotlinx.android.synthetic.main.card_item_layout.view.*


class PersonAdapter(val context:Context,var arr:ArrayList<Person>)
    :RecyclerView.Adapter<PersonAdapter.PersonViewHolde>()

{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolde {
       var inflater=LayoutInflater.from(parent.context)
       var view= inflater.inflate(R.layout.card_item_layout,parent,false)
        return PersonViewHolde(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolde, position: Int) {
        holder.bind(arr[position])
        holder.itemView.imgDetele.setOnClickListener {
            if(context is MainActivity)
            {
                context.delete(position)
            }
        }
        holder.itemView.imgUpdate.setOnClickListener {
            if(context is MainActivity)
            {
                context.update(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return  arr.size
    }

    class PersonViewHolde(var view:View):RecyclerView.ViewHolder(view)
    {
        fun bind(p:Person)
        {
            view.txtID.setText(p.id.toString())
            view.txtName.setText(p.name)
            view.txtAge.setText(p.age.toString())

        }
    }
}