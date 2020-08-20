package com.example.testtask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.testtask.Pojo.Data


class ProfileListAdapter(context: Context, listener: ProfileListViewHolder.OnProfileListener): RecyclerView.Adapter<ProfileListAdapter.ProfileListViewHolder>() {

    var profileList = ArrayList<Data>()
    val inflater = LayoutInflater.from(context)
    var onProfileListener = listener

    class ProfileListViewHolder(itemView: View, onProfileListener: OnProfileListener): RecyclerView.ViewHolder(itemView), View.OnClickListener {
         var id: Int? = null
         lateinit var avatarUrl: String
         var firstName: TextView
         var lastName: TextView
         var email: TextView
         var avatar: ImageView
         var onProfileListener: OnProfileListener

         init {
            firstName = itemView.findViewById(R.id.firstName)
            lastName = itemView.findViewById(R.id.lastName)
            email = itemView.findViewById(R.id.email)
            avatar = itemView.findViewById(R.id.avatar)
            this.onProfileListener = onProfileListener
            itemView.setOnClickListener(this)
            avatar.clipToOutline = true
         }

        interface OnProfileListener{
            fun onProfileClick(holder: ProfileListViewHolder){}
        }

        override fun onClick(p0: View?) {
            onProfileListener.onProfileClick(this)
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileListViewHolder{
        val view = inflater.inflate(R.layout.profile_item_layout,parent,false)
        return ProfileListViewHolder(view,onProfileListener)
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    fun updateList(newData: ArrayList<Data>){
        profileList = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ProfileListViewHolder, position: Int) {
        holder.id = profileList[position].id
        holder.avatarUrl = profileList[position].avatar
        holder.firstName.text = profileList[position].firstName
        holder.lastName.text = profileList[position].last_name
        holder.email.text = profileList[position].email
        Glide.with(holder.itemView)
            .load(profileList[position].avatar)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .into(holder.avatar)
    }
}