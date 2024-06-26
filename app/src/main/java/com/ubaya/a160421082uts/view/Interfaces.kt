package com.ubaya.a160421082uts.view

import android.view.View
import android.widget.CompoundButton
import com.ubaya.a160421082uts.model.User
import com.ubaya.a160421082uts.model.News
import java.util.Objects

interface UserRegisClick{
    fun onRegisClick(v: View)
}

interface UserLoginClick{
    fun onLoginClick(v:View)
}
interface NewsDetailClick{
    fun onNewsDetailClick(v:View)
}
interface UserUpdateProfileClick{
    fun onUserUpdateProfile(v: View, obj: User)
}