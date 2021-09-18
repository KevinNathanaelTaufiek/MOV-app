package com.kevinnt.movapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kevinnt.movapp.R
import com.kevinnt.movapp.utils.Preferences
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {

    private lateinit var preferences : Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        preferences = Preferences(context!!)
        var url = preferences.getValue("url");

        Glide.with(context!!)
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(iv_profile)

        tv_name.text = preferences.getValue("nama");
        tv_email.text = preferences.getValue("email");

    }

}