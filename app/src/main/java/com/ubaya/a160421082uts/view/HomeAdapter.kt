package com.ubaya.a160421082uts.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.a160421082uts.databinding.ItemHomeBinding
import com.ubaya.a160421082uts.model.News


class HomeAdapter(val newsList:ArrayList<News>)  : RecyclerView.Adapter<HomeAdapter.NewsViewHolder>(),NewsDetailClick {

    class NewsViewHolder(var binding: ItemHomeBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemHomeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)//cuma nama variabel. nentuin binding ke studentlistitem
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.txtJudulBerita.text = newsList[position].title
        holder.binding.txtAuthor.text = newsList[position].author_name
        holder.binding.txtDeskripsi.text = newsList[position].description

        holder.binding.btnRead.setOnClickListener {
            val action = HomeFragmentDirections.actionBaca(newsList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(newsList[position].imageURL)
            .into(holder.binding.imageView, object: Callback {
                override fun onSuccess() {
                    holder.binding.progressBarImage.visibility = View.INVISIBLE
                    holder.binding.imageView.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    Log.e("picasso_error", e.toString())
                }
            })
        //function untuk tiap proses yang akan dimasukkan ke dalam list item. Masukkin per itemnya datanya ke textnya gitu.
    }
    fun updateNewsList(newNewsList: List<News>) {
        newsList.clear()
        newsList.addAll(newNewsList)
        notifyDataSetChanged()
    }

    override fun onNewsDetailClick(v: View) {
        val id = v.tag.toString().toInt()
        val action = HomeFragmentDirections.actionBaca(id.toString())

        Navigation.findNavController(v).navigate(action)

    }
}