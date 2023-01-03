package com.latemen.upventure.home.messages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.latemen.upventure.R
import com.latemen.upventure.databinding.FragmentMessagesBinding

class MessagesFragment : Fragment(R.layout.fragment_messages) {

    private var _binding: FragmentMessagesBinding? = null
    private val binding by lazy { _binding!! }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMessagesBinding.bind(view)
        // todo
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}