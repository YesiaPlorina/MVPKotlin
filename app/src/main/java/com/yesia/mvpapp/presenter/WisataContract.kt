package com.yesia.mvpapp.presenter

import com.yesia.mvpapp.base.BaseView
import com.yesia.mvpapp.model.modelWisata.DataItem

interface WisataContract {
    interface Presenter {
        fun getDataWisata()
    }

    interface View : BaseView{
        fun showMsg(msg: String?)
        fun showError(localizedMessage: String?)
        fun showLoading()
        fun hideLoading()
        fun pindahHalaman(dataWisata: List<DataItem?>?)
    }
}