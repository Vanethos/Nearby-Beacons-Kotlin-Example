package com.vanethos.nearbyservice.presentation.main

import android.arch.lifecycle.LiveData
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vanethos.nearbyservice.R
import com.vanethos.nearbyservice.databinding.ItemBeaconBinding
import com.vanethos.nearbyservice.domain.models.Beacon

class BeaconsAdapter(var beacons : List<Beacon>, var clickListener : (Beacon) -> Unit) : RecyclerView.Adapter<BeaconsAdapter.BeaconViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeaconViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemBeaconBinding>(inflater, R.layout.item_beacon, parent, false)
        return BeaconViewHolder(binding, clickListener)
    }

    override fun getItemCount(): Int {
        return beacons.count()
    }

    override fun onBindViewHolder(holder: BeaconViewHolder, position: Int) {
        holder.bind(beacons.get(position))
    }

    fun setData(newData: List<Beacon>?) {
        if (newData != null) {
            this.beacons = newData
            notifyDataSetChanged()
        }
    }


    class BeaconViewHolder(val binding: ItemBeaconBinding, val clickListener: (Beacon) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(beacon : Beacon) {
            binding.item = beacon
            binding.root.clipToOutline = true
            binding.root.setOnClickListener{ clickListener(beacon) }
        }
    }
}