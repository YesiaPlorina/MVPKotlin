package com.yesia.mvpapp.presenter

import android.os.Handler
import com.airbnb.lottie.LottieAnimationView
import com.imastudio.customerapp.helper.SessionManager
import com.yesia.mvpapp.MainActivity
import com.yesia.mvpapp.base.BasePresenter
import com.yesia.mvpapp.view.activity.AuthActivity

class SplashPrensenter(var splashView: SplashContract.View? = null) :
    BasePresenter<SplashContract.View>, SplashContract.Prensenter {
    override fun onAttach(view: SplashContract.View) {
        splashView = view
    }

    override fun onDettach() {
        splashView = null
    }

    override fun delaySplash(
        timer: Long,
        lottie1: LottieAnimationView,
        session: SessionManager
    ) {
        lottie1.setAnimation("Mobilo/B.json")
        lottie1.loop(true)
        lottie1.playAnimation()

        Handler().postDelayed(Runnable {

            if (session.isLogin) {
                splashView?.pindahHalaman(MainActivity::class.java)
                splashView?.welcome()

            } else {
                splashView?.pindahHalaman(AuthActivity::class.java)
                splashView?.welcome()
            }


        }, timer)
    }
}