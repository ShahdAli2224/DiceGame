package com.example.dicegame

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import kotlin.random.Random


class fragmentone : Fragment() {
    private var userNumber: Int = 0
    private var pressCount: Int = 0

    companion object {
        fun newInstance(userNumber: Int): fragmentone {
            val fragment = fragmentone()
            val args = Bundle()
            args.putInt("userNumber", userNumber)
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragmentone, container, false)
        val btnimage = view?.findViewById<ImageButton>(R.id.id_btnimage)
        val tvnumber = view?.findViewById<TextView>(R.id.id_tvrandnum)
        val tvresult = view.findViewById<TextView>(R.id.id_tvresult)
        userNumber = arguments?.getInt("userNumber") ?: 0

        btnimage?.setOnClickListener {
            pressCount++
            val randnumber = Random.nextInt(1, 7)
            tvnumber?.text = "Random Number is $randnumber"
            if (userNumber == randnumber) {
                tvresult.text = "You Win!"
                //Toast.makeText(requireContext(), "You win!", Toast.LENGTH_SHORT).show()




                val fragmentTransaction = parentFragmentManager.beginTransaction()
                val f2 = fragmenttwo.newInstanceWithPressCount(pressCount)

                val bundle = Bundle()
                bundle.putInt("userNumber", userNumber)
                fragmenttwo().arguments = bundle

                fragmentTransaction.replace(R.id.id_flmain, f2)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
                pressCount = 0
               // val receivedInt: Int? = arguments?.getInt("userNumber",0)
            } else {
                tvresult.text = "Try again!"
                //Toast.makeText(requireContext(), "Try again!", Toast.LENGTH_SHORT).show()
            }


        }
        return view
    }


}