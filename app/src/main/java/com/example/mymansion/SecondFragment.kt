package com.example.mymansion

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SecondFragment : Fragment() {

    private val myViewModel: MansionViewModel by activityViewModels()
    private lateinit var mansionName: String
    private var mansionId = 0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.mansionSelection.observe(viewLifecycleOwner, { id ->

            mansionId = id
            Log.d("id", id.toString())

            myViewModel.getOneMansionDetails(mansionId).observe(viewLifecycleOwner, {

                it?.let {

                    val imageSelected = view.findViewById<ImageView>(R.id.imageDetail)
                    Glide.with(this).load(it.photo).fitCenter().into(imageSelected)

                    val price = view.findViewById<TextView>(R.id.tvPrice)
                    val name = view.findViewById<TextView>(R.id.tvName)
                    val description = view.findViewById<TextView>(R.id.tvDescription)
                    val reason = view.findViewById<TextView>(R.id.tvReason)

                    price.text = it.price.toString()
                    name.text = it.name
                    description.text = it.description
                    reason.text = it.cause

                    mansionName = it.name

                }
            })
        })

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {

            val address = getString(R.string.email_address)
            val subject = getString(R.string.email_subject) +
                    " " + mansionName +
                    " id " + mansionId
            val text = getString(R.string.email_text1) +
                    " " + mansionName +
                    " " + getString(R.string.email_text2)
            composeEmail(address, subject, text)

        }

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }

    private fun composeEmail(address: String, subject: String, text: String) {

        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, address)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)

        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "e.message", Toast.LENGTH_LONG).show()
        }

    }

}