package com.example.myapplication.data.login

data class LoginData(
    val code: Int,
    val token: String,
    val userInfo: UserInfo
)

data class UserInfo(
    val avatar: String,
    val birthday: Int,
    val gender: Int,
    val nickname: String,
    val uid: String,
    val username: String
)