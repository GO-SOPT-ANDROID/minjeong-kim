package org.android.go.sopt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import org.android.go.sopt.adapter.RepoViewAdapter
import org.android.go.sopt.adapter.TitleAdapter
import org.android.go.sopt.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗! binding이 NUll이 아니다"}

    private val viewModel by viewModels<HomeViewModel>()

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

        val titleAdapter = TitleAdapter(requireContext())
        val repoViewAdapter = RepoViewAdapter(requireContext())
        titleAdapter.setList(viewModel.mockTitleList)
        repoViewAdapter.setList(viewModel.mockRepoList)

        val adapter = ConcatAdapter(titleAdapter, repoViewAdapter)
        binding.rvHome.adapter = adapter
        binding.rvHome.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}