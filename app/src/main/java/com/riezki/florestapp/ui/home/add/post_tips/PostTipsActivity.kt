package com.riezki.florestapp.ui.home.add.post_tips

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.riezki.florestapp.R
import com.riezki.florestapp.databinding.ActivityPostTipsBinding

class PostTipsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostTipsBinding

    private lateinit var storageRef: StorageReference
    private lateinit var firebaseFireStore: FirebaseFirestore
    private var imageUri: Uri? = null
    //private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostTipsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVar()
        clickEvent()
    }

    private fun clickEvent() {
        val title = binding.tieTitle.text.toString()
        val desc = binding.tieDesc.text.toString()
        binding.btnUpload.setOnClickListener {
            if ((binding.tieTitle.text?.length ?: 0) < 1 && (binding.tieDesc.text?.length ?: 0) < 1 ) {
                binding.tieTitle.error = "Field harus diisi"
                binding.tieDesc.error = "Field harus diisi"
            } else {
                uploadImage(title, desc)
            }
        }

        binding.imgPrev.setOnClickListener {
            resultLauncher.launch("image/*")
        }
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {

        imageUri = it
        binding.imgPrev.setImageURI(imageUri)
    }

    private fun initVar() {
        storageRef = FirebaseStorage.getInstance().reference.child("images")

        firebaseFireStore = FirebaseFirestore.getInstance()
    }

    private fun uploadImage(title: String, desc: String) {
        val product = hashMapOf(
            "title" to title,
            "desc" to desc
        )

        showLoad(true)
        firebaseFireStore.collection("tips")
            .add(product)
            .addOnSuccessListener {
                showLoad(false)
                Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                showLoad(false)
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }

        storageRef = storageRef.child(System.currentTimeMillis().toString())
        imageUri?.let {
            storageRef.putFile(it).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showLoad(state: Boolean) {
        if (state) binding.pb.visibility = View.VISIBLE else View.GONE
    }
}