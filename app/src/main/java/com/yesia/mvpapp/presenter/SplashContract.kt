package com.yesia.mvpapp.presenter

import com.airbnb.lottie.LottieAnimationView
import com.imastudio.customerapp.helper.SessionManager
import com.yesia.mvpapp.base.BaseView

interface SplashContract {
    interface Prensenter{
        fun delaySplash(
            timer: Long,
            lottie1: LottieAnimationView,
            session: SessionManager
        )
    }

    interface View : BaseView{
        fun pindahHalaman(java:Class<*>)
        fun welcome ()
    }
}