package com.jhm.android.pokusme.data

data class UserData(
    var displayName: String = "제목없음",
    var email: String?,
    var isEmailVerified: Boolean?
)