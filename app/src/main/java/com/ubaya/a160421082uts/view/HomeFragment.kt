package com.ubaya.a160421082uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160421082uts.databinding.FragmentHomeBinding
import com.ubaya.a160421082uts.viewmodel.ListViewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel:ListViewModel
    private val homeListAdapter  = HomeAdapter(arrayListOf())
    private lateinit var binding:FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
//        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = homeListAdapter

        observeViewModel()
        viewModel.refresh()

        binding.RefreshLayout.setOnRefreshListener {
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            binding.RefreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.newsLD.observe(viewLifecycleOwner, Observer {
            homeListAdapter.updateNewsList(it)
        })
        viewModel.newsLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.txtError?.visibility = View.VISIBLE
            } else {
                binding.txtError?.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recView.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })

    }


}