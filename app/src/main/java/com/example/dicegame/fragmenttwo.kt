package com.example.dicegame

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.FragmentTransaction
import java.net.URI
import kotlin.random.Random


class fragmenttwo : Fragment() {
    private var userNumber: Int = 0
    private var pressCount: Int = 0
    private lateinit var  vv_video: VideoView
    private  var time:Int=0

companion object{
    fun newInstanceWithPressCount(pressCount: Int): fragmenttwo {
        val fragment = fragmenttwo()
        val args = Bundle()
        args.putInt("pressCount", pressCount)
        fragment.arguments = args
        return fragment
    }
}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_fragmenttwo, container, false)
        //// video
        vv_video =view.findViewById(R.id.id_videv)
        val packageName = requireActivity().packageName
        val uri= Uri.parse("android.resource://$packageName/${R.raw.video}")
        vv_video.setVideoURI(uri)
        vv_video.start()

        ////to display number of try
        val tvnum=view.findViewById<TextView>(R.id.id_tvcount)
        val pressCount: Int? = arguments?.getInt("pressCount",0)
        val userNumber: Int? = arguments?.getInt("userNumber",0)
        tvnum.text = "You win after : $pressCount tries!"

        ////to open other activity
        val btnlogout=view.findViewById<Button>(R.id.id_btnlogout)
        btnlogout.setOnClickListener {
            val i=Intent(requireActivity(),MainActivity::class.java)
            startActivity(i)
        }
        val btnplayagin=view.findViewById<Button>(R.id.id_btnagain)
        btnplayagin.setOnClickListener {
            val firstfrag = parentFragmentManager.beginTransaction()
            val f1: Fragment = fragmentone()

            val bundle = Bundle()
            bundle.putString("userNumber", userNumber.toString())
            fragmentone().arguments = bundle

            firstfrag.replace(R.id.id_flmain, f1).commit()

            ////

        }
        return view
    }

    override fun onPause() {
        super.onPause()
        time=vv_video.currentPosition
        vv_video.pause()
    }

    override fun onResume() {
        super.onResume()
        vv_video.seekTo(time)
        vv_video.start()
    }

}