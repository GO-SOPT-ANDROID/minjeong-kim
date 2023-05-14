package org.android.go.sopt

import androidx.lifecycle.ViewModel
import org.android.go.sopt.adapter.Title

class HomeViewModel : ViewModel() {
    val mockTitleList = listOf(
        Title(
            title = "Follower List"
        )
    )
}