package com.nanoyatsu.example.clean.poke.core.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun bindImage(view: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(view.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                // todo bindImageのエラーハンドリング
//                    .placeholder(R.drawable.loading_animation)
//                    .error(R.drawable.ic_broken_image)
            )
            .into(view)
    }
}

@BindingAdapter("numberInt")
fun bindNumber(view: TextView, number: Int?) {
    number?.let {
        view.text = String.format("No.%03d", it)
    }
}

@BindingAdapter("onRefreshListener")
fun bindRefreshListener(view: SwipeRefreshLayout, listener: (() -> Unit)?) {
    listener?.let { view.setOnRefreshListener { it() } }
}

@BindingAdapter("refreshingState")
fun bindRefreshingState(view: SwipeRefreshLayout, state: Boolean?) {
    state?.let { view.isRefreshing = it }
}