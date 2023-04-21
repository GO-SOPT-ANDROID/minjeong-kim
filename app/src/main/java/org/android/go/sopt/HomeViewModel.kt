package org.android.go.sopt

import androidx.lifecycle.ViewModel
import org.android.go.sopt.adapter.Repo
import org.android.go.sopt.adapter.Title

class HomeViewModel : ViewModel() {
    val mockTitleList = listOf<Title>(
        Title(
            title = "Emjay's Repositories"
        )
    )

    val mockRepoList = listOf<Repo>(
        Repo(
            image = R.drawable.github,
            name = "Repo1",
            author = "emjay"
        ),
        Repo(
            image = R.drawable.github,
            name = "Repo2",
            author = "emjay"
        ),
        Repo(
            image = R.drawable.github,
            name = "Repo3",
            author = "emjay"
        ),
        Repo(
            image = R.drawable.github,
            name = "Repo4",
            author = "emjay"
        ),
        Repo(
            image = R.drawable.github,
            name = "Repo5",
            author = "emjay"
        ),
        Repo(
            image = R.drawable.github,
            name = "Repo6",
            author = "emjay"
        ),
        Repo(
            image = R.drawable.github,
            name = "Repo7",
            author = "emjay"
        ),
        Repo(
            image = R.drawable.github,
            name = "Repo8",
            author = "emjay"
        ),
        Repo(
            image = R.drawable.github,
            name = "Repo9",
            author = "emjay"
        ),
    )
}