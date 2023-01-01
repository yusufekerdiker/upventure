package com.latemen.upventure.home.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.latemen.upventure.R
import com.latemen.upventure.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding by lazy { _binding!! }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)
        // todo
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}