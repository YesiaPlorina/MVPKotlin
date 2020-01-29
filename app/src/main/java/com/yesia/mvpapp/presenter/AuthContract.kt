package com.yesia.mvpapp.presenter

import com.yesia.mvpapp.base.BaseView
import com.yesia.mvpapp.model.modelAuth.User

interface AuthContract {
    interface Presenter{
        fun prosesRegister(
            email: String,
            nama: String,
            nohp: String,
            alamat: String,
            password: String,
            strJenkel: String?,
            usia: String,
            strLevel: String?
        )
        fun prosesLogin(
            username : String,
            password: String,
            strLevel: String?
        )
    }

    interface View:BaseView{
        fun showLoading()
        fun hideLoding()
        fun hideDialog()
        fun showError(toString: String)
        fun pindahHalaman(dataLogin: User?)
        fun showMsg(msg: String?)
    }
}