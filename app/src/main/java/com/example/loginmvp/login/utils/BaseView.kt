package com.example.loginmvp.login.utils

interface BaseView<T> {
    var presenter : T
    fun bindViews()
}