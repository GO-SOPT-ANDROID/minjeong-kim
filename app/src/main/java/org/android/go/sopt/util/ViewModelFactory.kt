package org.android.go.sopt.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.android.go.sopt.data.datasource.remote.SignUpDataSource
import org.android.go.sopt.data.datasource.remote.UserDataSource
import org.android.go.sopt.data.repository.SignUpRepository
import org.android.go.sopt.data.repository.UserRepository
import org.android.go.sopt.feature.home.home.HomeViewModel
import org.android.go.sopt.feature.signup.SignUpViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(UserRepository(UserDataSource())) as T
        }
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(SignUpRepository(SignUpDataSource())) as T
        }
        throw IllegalArgumentException("뷰모델을 만들 수 없습니다")
    }
}
