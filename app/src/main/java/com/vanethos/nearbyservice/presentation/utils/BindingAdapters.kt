package com.vanethos.nearbyservice.presentation.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.threeten.bp.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalField

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun setImageUrl(view: ImageView, url: String) {
        GlideApp.with(view.context).load(url).into(view)
    }

    @JvmStatic
    @BindingAdapter("app:dateText")
    fun setImageUrl(view: TextView, date: LocalDateTime) {
        view.setText("${date.dayOfMonth} of ${date.month}, ${date.year}")
    }
}