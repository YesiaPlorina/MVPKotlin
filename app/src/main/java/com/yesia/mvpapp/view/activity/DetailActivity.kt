package com.yesia.mvpapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.yesia.mvpapp.R
import com.yesia.mvpapp.helper.Helper.Companion.DATAWISATA
import com.yesia.mvpapp.model.modelWisata.DataItem
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.tampilanwisata.view.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var data = intent.getParcelableExtra<DataItem>(DATAWISATA)

        Picasso.get().load(data.gambar).placeholder(R.drawable.android)
            .error(R.drawable.android).into(detailImg)

        detailJdl.text = data.lokasi
        detailDesk.text = data.deskripsi
    }
}
