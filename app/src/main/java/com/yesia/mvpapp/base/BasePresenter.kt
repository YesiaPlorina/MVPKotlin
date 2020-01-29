package com.yesia.mvpapp.base

interface BasePresenter <T:BaseView>{

    fun onAttach(view:T)
    fun onDettach()

}