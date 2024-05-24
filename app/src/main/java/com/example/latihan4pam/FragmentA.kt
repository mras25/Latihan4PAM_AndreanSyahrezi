package com.example.latihan4pam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.latihan4pam.R

class FragmentA : Fragment() {

    private val swipeThreshold = 100 // Adjust this threshold as needed

    private var initialX: Float = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    initialX = event.x
                    true
                }
                MotionEvent.ACTION_UP -> {
                    val deltaX = initialX - event.x
                    if (deltaX > swipeThreshold) {
                        navigateToFragmentB()
                        true
                    } else {
                        false
                    }
                }
                else -> false
            }
        }
    }

    private fun navigateToFragmentB() {
        val navController = findNavController()
        navController.navigate(R.id.action_fragmentA_to_fragmentB)
    }
}
