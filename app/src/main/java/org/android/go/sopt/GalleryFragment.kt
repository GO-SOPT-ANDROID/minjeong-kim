package org.android.go.sopt

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import coil.load
import org.android.go.sopt.adapter.GalleryAdapter
import org.android.go.sopt.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding) { "앗! binding이 NUll이 아니다" }

    private val launcher = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        imageUri: Uri? ->
        binding.ivGalleryImage.load(imageUri)
    }


    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        isGranted: Boolean ->
        if (isGranted) {

        } else {

        }
    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btGallery.setOnClickListener{
            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }

        binding.root.setOnClickListener {
            requestPermissionLauncher.launch("android.permission.ACCESS_COARSE_LOCATION")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}