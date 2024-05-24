package com.example.latihan4pam

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.latihan4pam.R

class FragmentB : Fragment() {

    private val swipeThreshold = 100 // Adjust this threshold as needed

    private var initialX: Float = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonOpenFragmentA = view.findViewById<Button>(R.id.button_open_fragment_a)

        // Set click listener for the button
        buttonOpenFragmentA.setOnClickListener {
            // Create deep link URI for FragmentA
            val deepLinkUri = Uri.parse("exampleapp://host/fragmentA/123")

            // Navigate to FragmentA using deep link
            findNavController().navigate(deepLinkUri)
        }

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        view.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    initialX = event.x
                    true
                }
                MotionEvent.ACTION_UP -> {
                    val deltaX = event.x - initialX
                    if (deltaX > swipeThreshold) {
                        navigateToFragmentA()
                        true
                    } else {
                        false
                    }
                }
                else -> false
            }
        }
    }

    private fun navigateToFragmentA() {
        val navController = findNavController()
        navController.navigate(R.id.action_fragmentB_to_fragmentA)
    }
}
