package com.riezki.florestapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.riezki.florestapp.R
import com.riezki.florestapp.databinding.FragmentHomeBinding
import com.riezki.florestapp.ui.home.detail.DetailActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.to_bottom_anim) }

    private var clicked: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addPostProduct()
    }

    private fun addPostProduct() {
        binding.mainFab.setOnClickListener {
            onAddButtonClicked()
        }

        binding.secondFab.setOnClickListener {
            val snackBarShow = Snackbar.make(requireView(), "Post Product Item Clicked", Snackbar.LENGTH_SHORT)
                .setAction("Ok") {

                }
            val viewSnackbar = snackBarShow.view
            viewSnackbar.layoutParams
            snackBarShow.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
            snackBarShow.show()
        }

        binding.thirdFab.setOnClickListener {
            val snackBarShow = Snackbar.make(requireView(), "Tips and Trick Item Clicked", Snackbar.LENGTH_SHORT)
                .setAction("Ok") {

                }
            val viewSnackbar = snackBarShow.view
            viewSnackbar.layoutParams
            snackBarShow.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
            snackBarShow.show()
        }
    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {
        with(binding) {
            if (!clicked) {
                secondFab.startAnimation(fromBottom)
                thirdFab.startAnimation(fromBottom)
                mainFab.startAnimation(rotateOpen)
            } else {
                secondFab.startAnimation(toBottom)
                thirdFab.startAnimation(toBottom)
                mainFab.startAnimation(rotateClose)
            }
        }
    }

    private fun setVisibility(clicked: Boolean) {
        with(binding) {
            if (!clicked) {
                secondFab.visibility = View.VISIBLE
                thirdFab.visibility = View.VISIBLE
            } else {
                secondFab.visibility = View.GONE
                thirdFab.visibility = View.GONE
            }
        }
    }

    private fun setClickable(clicked: Boolean){
        with(binding){
            if (!clicked) {
                secondFab.isClickable = true
                thirdFab.isClickable = true
            } else {
                secondFab.isClickable = false
                thirdFab.isClickable = false
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
