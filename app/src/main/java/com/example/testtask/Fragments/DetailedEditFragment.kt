package com.example.testtask.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.testtask.Database.DAO
import com.example.testtask.Database.ProfilesDatabase
import com.example.testtask.Pojo.Data
import com.example.testtask.R
import com.example.testtask.ViewModel.GeneralViewModel
import kotlinx.android.synthetic.main.fragment_detailed_edit.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DetailedEditFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao: DAO = ProfilesDatabase.getInstance(this.context!!).profileDao()
        val bundle = arguments
        lateinit var sharedViewModel: GeneralViewModel
        activity?.let {
            sharedViewModel = ViewModelProvider(it).get(GeneralViewModel::class.java)
        }
        firstNameEdit.setText(bundle?.getString("firstName"))
        lastNameEdit.setText(bundle?.getString("lastName"))
        emailEdit.setText(bundle?.getString("email"))
        Glide.with(this)
            .load(bundle?.getString("avatar"))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .into(profileImageEdit)
        profileImageEdit.clipToOutline = true
        save.setOnClickListener {
            val profile = Data(
                bundle!!.getInt("ID"),
                firstNameEdit.text.toString(),
                lastNameEdit.text.toString(),
                emailEdit.text.toString(),
                bundle.getString("avatar")!!
            )
            GlobalScope.launch {
                sharedViewModel.update(dao, profile)
                Log.d(
                    "ProfUpdate",
                    "Profile with Id:${profile.id},Name:${profile.firstName},Surname:${profile.last_name} has been updated!"
                )
            }
            fragmentManager?.popBackStack()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_edit, container, false)
    }

}
