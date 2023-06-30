package org.android.go.sopt.feature.home.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.android.go.sopt.data.datasource.model.ResponseUserDTO
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.feature.adapter.UserAdapter
import org.android.go.sopt.util.ViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗! binding이 NUll이 아니다" }

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }

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

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.makeList()
        makeUserList()
    }

    private fun makeUserList() {
        viewModel.userResult.observe(viewLifecycleOwner) { response ->
            response.data.let { userList ->
                makeAdapter(userList)
            }
        }
    }

    private fun makeAdapter(userList: List<ResponseUserDTO.UserData>) {
        val userViewAdapter = UserAdapter(requireContext())
        userViewAdapter.submitList(userList)

        binding.rvHome.adapter = userViewAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
