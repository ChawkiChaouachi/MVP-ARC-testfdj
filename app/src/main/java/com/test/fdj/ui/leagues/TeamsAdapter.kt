package com.test.fdj.ui.leagues

import android.content.Context
import android.opengl.ETC1
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.fdj.R
import com.test.fdj.modelapp.STeam
import android.util.DisplayMetrics




class TeamsAdapter constructor ( val context: Context,private val onClick: (STeam) -> Unit) :
ListAdapter<STeam, TeamsAdapter.TeamViewHolder>(TeamDiffCallback) {

    class TeamViewHolder(itemView: View, val onClick: (STeam) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private var currentTeam : STeam ?=null

        init {
            itemView.setOnClickListener {
                currentTeam?.let {
                    onClick(it)
                }
            }
        }

        fun bind(team: STeam) {
            val logoImg =itemView.findViewById<ImageView>(R.id.logo_team)
            Glide.with(itemView.context)
                .load(team.strTeamBadge)
                .override(getWidth())
                .into(logoImg);
        }
        private fun getWidth():Int{
            val displayMetrics = itemView.context.resources.displayMetrics
            return (displayMetrics.widthPixels-100)/2
        }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.team_logo_item, parent, false)
        return TeamViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = getItem(position)
        holder.bind(team)
    }



}

object TeamDiffCallback : DiffUtil.ItemCallback<STeam>() {
    override fun areItemsTheSame(oldItem: STeam, newItem: STeam): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: STeam, newItem: STeam): Boolean {
        return oldItem.idTeam == newItem.idTeam
    }
}