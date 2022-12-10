package com.riezki.florestapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.riezki.florestapp.databinding.FragmentHomeBinding
import com.riezki.florestapp.ui.home.detail.DetailActivity
import com.riezki.florestapp.ui.home.detail.DetailTipsActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToDetail.setOnClickListener {
            val intent = Intent(activity, DetailActivity::class.java)
            startActivity(intent)
        }
        binding.btnToDetailTips.setOnClickListener{
            val intent = Intent(activity, DetailTipsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}