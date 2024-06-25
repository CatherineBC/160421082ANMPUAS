package com.ubaya.a160421082uts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.ubaya.a160421082uts.R
import com.ubaya.a160421082uts.databinding.FragmentDetailBeritaBinding
import com.ubaya.a160421082uts.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class DetailBeritaFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBeritaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBeritaBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        observeViewModel()
    }

    private fun observeViewModel() {
        var contentIndex = 0

        viewModel.newsLD.observe(viewLifecycleOwner, Observer {
            var News = it
            Picasso.get().load(viewModel.newsLD.value?.imageURL).into(binding.imageView2)
            binding.txtAuthorDetil.setText(News.author_name)
            binding.txtJudulDetil.setText(News.title)
            binding.txtIsiContent.setText(News.content)

//            if (contentIndex == 0) {
//                binding.btnBack.isEnabled = false
//            } else {
//                binding.btnBack.isEnabled = true
//            }

//            if (contentIndex == News.content.size - 1) {
//                binding.btnNext.isEnabled = false
//            } else {
//                binding.btnNext.isEnabled = true
//            }

//            binding.btnNext?.setOnClickListener {
//                if (contentIndex < News.content.size - 1) {
//                    contentIndex++
//                    binding.txtIsiContent.text = News.content[contentIndex]
//                }
//
//                if (contentIndex == News.content.size - 1) {
//                    binding.btnNext.isEnabled = false
//                }
//
//                binding.btnBack.isEnabled = true
//            }

//            binding.btnBack?.setOnClickListener {
//                if (contentIndex > 0) {
//                    contentIndex--
//                    binding.txtIsiContent.text = News.content[contentIndex]
//                }
//
//                if (contentIndex == 0) {
//                    binding.btnBack.isEnabled = false
//                }
//                binding.btnNext.isEnabled = true
//            }
        })

    }
}