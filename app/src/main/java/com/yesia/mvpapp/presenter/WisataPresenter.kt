package com.yesia.mvpapp.presenter

import com.imastudio.customerapp.network.InitRetrofit
import com.yesia.mvpapp.base.BasePresenter
import com.yesia.mvpapp.model.modelWisata.ResponseWisata
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WisataPresenter(var wisataView: WisataContract.View? = null) :
    BasePresenter<WisataContract.View>, WisataContract.Presenter {
    override fun onAttach(view: WisataContract.View) {
        wisataView = view
    }

    override fun onDettach() {
        wisataView = null
    }

    override fun getDataWisata() {

        wisataView?.showLoading()
        InitRetrofit.getInstancewisata().getWisata().enqueue(
            object : Callback<ResponseWisata> {
                override fun onFailure(call: Call<ResponseWisata>, t: Throwable) {
                    wisataView?.showError(t.localizedMessage)
                    wisataView?.hideLoading()
                }

                override fun onResponse(
                    call: Call<ResponseWisata>,
                    response: Response<ResponseWisata>
                ) {

                    wisataView?.hideLoading()
                    if (response.isSuccessful) {
                        var status: Int? = response.body()?.statusCode
                        var msg: String? = response.body()?.message
                        var dataWisata = response.body()?.data
                        if (status == 200) {
                            wisataView?.showMsg(msg)
                            wisataView?.pindahHalaman(dataWisata)
                        } else {
                            wisataView?.showMsg(msg)
                        }
                    }
                }

            }
        )
    }
}