package com.riezki.florestapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.riezki.florestapp.ListTipsData
import com.riezki.florestapp.R
import com.riezki.florestapp.adapter.ListTipsAdapter
import com.riezki.florestapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val list : ArrayList<ListTipsData> = arrayListOf()
    private lateinit var rvListTips: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvListTips = binding.listTipsRv
        rvListTips.setHasFixedSize(true)

        list.addAll(listTips)
        showRecycleList()
    }

    private val listTips: ArrayList<ListTipsData>
    get() {
        val judul = resources.getStringArray(R.array.judul)
        val deskripsi = resources.getStringArray(R.array.deskripsi)

        val listTips = ArrayList<ListTipsData>()
        for (i in judul.indices) {
            val list = ListTipsData(judul[i], deskripsi[i])
            listTips.add(list)
        }
        return listTips
    }

    private fun showRecycleList() {
        rvListTips.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val listTipsAdapter = ListTipsAdapter(list)
        rvListTips.adapter = listTipsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}