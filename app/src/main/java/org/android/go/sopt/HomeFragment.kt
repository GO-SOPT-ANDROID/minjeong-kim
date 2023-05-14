package org.android.go.sopt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.adapter.UserAdapter
import org.android.go.sopt.adapter.TitleAdapter
import org.android.go.sopt.data.remote.model.ResponseUserDTO
import org.android.go.sopt.data.remote.UserServicePool
import org.android.go.sopt.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Response


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗! binding이 NUll이 아니다"}

    private val viewModel by viewModels<HomeViewModel>()
    private val usersListService = UserServicePool.userService

    // 뷰 생성
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 구현부
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 대부분의 로직을 여기에 구현

        makeUserList()

    }


    private fun makeUserList() {

        usersListService.getUser().enqueue(
            object: retrofit2.Callback<ResponseUserDTO> {
                override fun onResponse(
                    call: Call<ResponseUserDTO>,
                    response: Response<ResponseUserDTO>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data.let {
                            if (it != null) {
                                makeAdapter(it)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseUserDTO>, t: Throwable) {
                    t.printStackTrace()
                    Snackbar.make(binding.root, "실패", Snackbar.LENGTH_SHORT).show()
                }
            }
        )
    }

    private fun makeAdapter(userList: List<ResponseUserDTO.UserData> ) {
        val titleAdapter = TitleAdapter(requireContext())
        val userViewAdapter = UserAdapter(requireContext())
        titleAdapter.setList(viewModel.mockTitleList)
        userViewAdapter.submitList(userList)

        val adapter = ConcatAdapter(titleAdapter, userViewAdapter)
        binding.rvHome.adapter = adapter
        binding.rvHome.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}