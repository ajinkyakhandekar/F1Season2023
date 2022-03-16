package com.ajinkya.formula1.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajinkya.formula1.common.convertDate
import com.ajinkya.formula1.common.convertTime
import com.ajinkya.formula1.data.entity.Race
import com.ajinkya.formula1.databinding.RowRacesBinding

class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    private var raceList = mutableListOf<Race>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowRacesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val race = raceList[position]
            textRound.text = race.round
            textRace.text = "${race.raceName}\n${race.Circuit.Location.country}"
            val date = convertDate(race.date)
            val time = convertTime(race.time)
            textDateTime.text = "$date\n$time"
        }
    }

    override fun getItemCount(): Int = raceList.size


    fun updateData(raceList: MutableList<Race>) {
        this.raceList = raceList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RowRacesBinding) : RecyclerView.ViewHolder(binding.root)
}