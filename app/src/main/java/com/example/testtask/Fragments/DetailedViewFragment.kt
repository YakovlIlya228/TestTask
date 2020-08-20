package com.example.testtask.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.testtask.R
import kotlinx.android.synthetic.main.fragment_detailed_view.*


class DetailedViewFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        firstNameFrag.text = bundle?.getString("firstName")
        lastNameFrag.text = bundle?.getString("lastName")
        emailFrag.text = bundle?.getString("email")
        Glide.with(this)
            .load(bundle?.getString("avatar"))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .into(profileImageFrag)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_view, container, false)

    }

}