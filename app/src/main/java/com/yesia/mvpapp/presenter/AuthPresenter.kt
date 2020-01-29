package com.yesia.mvpapp.presenter

import com.imastudio.customerapp.network.InitRetrofit
import com.yesia.mvpapp.base.BasePresenter
import com.yesia.mvpapp.model.modelAuth.ResponseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthPresenter(var authView: AuthContract.View? = null) : BasePresenter<AuthContract.View>,
    AuthContract.Presenter {
    override fun onAttach(view: AuthContract.View) {
        authView = view
    }

    override fun onDettach() {
        authView = null
    }

    override fun prosesRegister(
        email: String,
        nama: String,
        nohp: String,
        alamat: String,
        password: String,
        strJenkel: String?,
        usia: String,
        strLevel: String?
    ) {
        authView?.showLoading()
        InitRetrofit.getInstance().registerUser(
            vsusername = email,
            vsnama = nama,
            vsnotelp = nohp,
            vsalamat = alamat,
            vspassword = password,
            vsjenkel = strJenkel,
            vsusia = usia,
            vslevel = strLevel
        ).enqueue(
            object : Callback<ResponseAuth> {
                override fun onFailure(call: Call<ResponseAuth>, t: Throwable) {
                    authView?.showError(t.localizedMessage.toString())
                    authView?.hideLoding()
                }

                override fun onResponse(
                     call: Call<ResponseAuth>,
                    response: Response<ResponseAuth>
                ) {
                    authView?.hideLoding()
                    if (response.isSuccessful) {
                        var result: String? = response.body()?.result
                        var msg: String? = response.body()?.msg
                        if (result.equals("1")) {
                            authView?.showMsg(msg)
                            authView?.hideDialog()
                        } else {
                            authView?.showMsg(msg)
                        }
                    }
                }

            }
        )
    }

    override fun prosesLogin(username: String, password: String, strLevel: String?) {
        authView?.showLoading()
        InitRetrofit.getInstance().loginUser(
            edtusername = username,
            edtpassword = password,
            vslevel = strLevel
        ).enqueue(object : Callback<ResponseAuth> {
            override fun onFailure(call: Call<ResponseAuth>, t: Throwable) {
                authView?.showError(t.localizedMessage.toString())
                authView?.hideLoding()
            }

            override fun onResponse(call: Call<ResponseAuth>, response: Response<ResponseAuth>) {
                authView?.hideLoding()
                if (response.isSuccessful){
                    var result = response.body()?.result
                    var msg = response.body()?.msg
                    var dataLogin =response.body()?.user


                        if (result.equals("1")) {
                        authView?.showMsg(msg)
                        authView?.hideDialog()
                        authView?.pindahHalaman(dataLogin)
                    } else {
                        authView?.showMsg(msg)
                    }
                }
                }

        } )



    }
}




