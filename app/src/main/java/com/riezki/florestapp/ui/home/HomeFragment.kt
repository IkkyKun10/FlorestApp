package com.riezki.florestapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.riezki.florestapp.R
import com.riezki.florestapp.core.entity.ProductItem
import com.riezki.florestapp.core.entity.TipsContentEntity
import com.riezki.florestapp.databinding.FragmentHomeBinding
import com.riezki.florestapp.ui.home.adapter.PostProductAdapter
import com.riezki.florestapp.ui.home.adapter.PostTipsAdapter
import com.riezki.florestapp.ui.home.add.post_product.PostProductActivity
import com.riezki.florestapp.ui.home.add.post_tips.PostTipsActivity
import com.riezki.florestapp.ui.home.detail.DetailActivity
import com.riezki.florestapp.ui.home.detail.DetailTipsActivity

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

    private lateinit var firebaseFireStore: FirebaseFirestore
    private val rvTips = _binding?.rvTips
    private val rvProduct = _binding?.rvProduct
    private lateinit var postTipsAdapter: PostTipsAdapter
    private lateinit var postProductAdapter: PostProductAdapter

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

        firebaseFireStore = FirebaseFirestore.getInstance()

        postTipsAdapter = PostTipsAdapter { data ->
            val intent = Intent(context, DetailTipsActivity::class.java)
            intent.putExtra(DetailTipsActivity.INTENT_EXTRA, data)
            startActivity(intent)
        }
        postProductAdapter = PostProductAdapter { data ->
            val intent = Intent(context, DetailTipsActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, data)
            startActivity(intent)
        }

        setUpRvTips()

        setUpRvProduct()

        readAllDataProduct()

        readAllDataTips()

        addPostProduct()
    }

    private fun setUpRvProduct() {
        rvProduct?.layoutManager = GridLayoutManager(context, 2)
        rvProduct?.adapter = postProductAdapter
        rvProduct?.setHasFixedSize(true)

    }

    private fun setUpRvTips() {
        rvTips?.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
        rvTips?.adapter = postTipsAdapter
        rvTips?.setHasFixedSize(true)
    }

    private fun readAllDataProduct() {
        firebaseFireStore.collection("product")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    val product = ProductItem(
                        idProduct = doc.id,
                        titleProduct = doc.getString("title"),
                        desc = doc.getString("desc")
                    )
                    postProductAdapter.submitList(listOf(product))
                }
            }
            .addOnFailureListener { ex ->
                Toast.makeText(context, "Data gagal diambil" + ex.message, Toast.LENGTH_SHORT).show()
            }
    }

    private fun readAllDataTips() {
        firebaseFireStore.collection("tips")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (doc in task.result) {
                        val tips = TipsContentEntity(
                            idTips = doc.id,
                            titleTips = doc.getString("title"),
                            descTips = doc.getString("desc")
                        )
                        postTipsAdapter.submitList(listOf(tips))
                    }
                } else {
                    Toast.makeText(context, "Data gagal diambil", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addPostProduct() {
        binding.mainFab.setOnClickListener {
            onAddButtonClicked()
        }

        binding.secondFab.setOnClickListener {
            Intent(context, PostProductActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.thirdFab.setOnClickListener {
            Intent(context, PostTipsActivity::class.java).also {
                startActivity(it)
            }

            /*
            val snackBarShow = Snackbar.make(requireView(), "Tips and Trick Item Clicked", Snackbar.LENGTH_SHORT)
                .setAction("Ok") {

                }
            val viewSnackbar = snackBarShow.view
            viewSnackbar.layoutParams
            snackBarShow.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
            snackBarShow.show()
            */
        }
    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
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

    private fun setClickable(clicked: Boolean) {
        with(binding) {
            if (!clicked) {
                secondFab.isClickable = true
                thirdFab.isClickable = true
            } else {
                secondFab.isClickable = false
                thirdFab.isClickable = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
