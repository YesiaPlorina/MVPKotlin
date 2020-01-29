package com.yesia.mvpapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imastudio.customerapp.helper.SessionManager
import com.yesia.mvpapp.R
import com.yesia.mvpapp.presenter.SplashContract
import com.yesia.mvpapp.presenter.SplashPrensenter
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SplashScreen : AppCompatActivity(), SplashContract.View {

    lateinit var presenter: SplashPrensenter
    lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        session = SessionManager(this)
        initPresenter()

        presenter.delaySplash(5000, lottie1, session)

    }

    private fun initPresenter() {
        presenter = SplashPrensenter(this)

    }

    override fun pindahHalaman(java: Class<*>) {
        startActivity(Intent(this,java))
        finish()
    }

    override fun welcome() {
       if (session.isLogin)
           toast("Selamat Datang Kembali ${session.getToken()}\n")
       else
           toast("Selamat Datang")

    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettach()
    }

    override fun onStart() {
        super.onStart()
        onAttachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDettachView()
    }

}
