package com.yesia.mvpapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yesia.mvpapp.R
import com.yesia.mvpapp.helper.Helper.Companion.DATAWISATA
import com.yesia.mvpapp.model.modelWisata.DataItem
import com.yesia.mvpapp.view.activity.DetailActivity
import kotlinx.android.synthetic.main.tampilanwisata.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class AdapterWisata(
    var activity: FragmentActivity?,
    var dataWisata: List<DataItem?>?
) : RecyclerView.Adapter<AdapterWisata.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterWisata.MyViewHolder {
        var v = LayoutInflater.from(activity).inflate(R.layout.tampilanwisata, parent, false)
        return MyViewHolder(v)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(item: DataItem,activity: FragmentActivity) {
            Picasso.get().load(item.gambar).placeholder(R.drawable.android)
                .error(R.drawable.android).into(itemView.itemImg)

            itemView.itemJudul.text = item.namaTempat
            itemView.itemDesk.text = item.deskripsi

            itemView.setOnClickListener {
                activity.startActivity<DetailActivity>(DATAWISATA to item)
            }
        }

    }

    //untuk menghitung total data yang akan di load
    override fun getItemCount(): Int = dataWisata!!.size

    override fun onBindViewHolder(holder: AdapterWisata.MyViewHolder, position: Int) =
        holder.bindItem(dataWisata?.get(position)!!,activity!!)
}