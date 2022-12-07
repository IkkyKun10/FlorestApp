package com.riezki.florestapp.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.riezki.florestapp.databinding.FragmentSignUpBinding
import com.riezki.florestapp.ui.auth.login.LoginFragment

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        textChangeListener()

        binding.registerButton.setOnClickListener {
            if (validateInput()) {
                val email = binding.email.text.toString()
                val password = binding.password.text.toString()
                registerFirebaseAuth(email, password)
            }
        }
    }

    private fun registerFirebaseAuth(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()){
                if (it.isSuccessful){
                    Toast.makeText(context, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    Intent(context, LoginFragment::class.java).also { its ->
                        startActivity(its)
                        activity?.finish()
                    }
                } else {
                    Toast.makeText(context, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun textChangeListener() {
        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(input: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validateEmail(input.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(input: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validatePassword(input.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.retypePassword.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validatePassword(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun validateEmail(text: String) {
        with(binding) {
            if (text.trim().isEmpty()) {
                email.error = "Email tidak boleh kosong"
            } else {
                email.error = null
            }
        }
    }

    private fun validatePassword(text: String) {
        with(binding) {
            if (text.length < 8) {
                password.error = "Password minimal 8 karakter"
            } else {
                password.error = null
            }
        }
    }

    private fun validateInput() : Boolean {

        with(binding) {
            val rePassword = retypePassword.text.toString()
            val firstPassword = password.text.toString()
            return when {
                email.text?.trim().isNullOrEmpty() -> {
                    email.error = "Silahkan isi email terlebih dahulu"
                    email.requestFocus()
                    false
                }
                (rePassword != firstPassword) -> {
                    retypePassword.error = "Password tidak valid"
                    false
                }
                (password.text?.length ?: 0) < 6 -> {
                    password.error = "Password minimal 8 karakter"
                    password.requestFocus()
                    false
                }
                !Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches() -> {
                    email.error = "Email tidak valid"
                    false
                }
                else -> {
                    retypePassword.error = null
                    password.error = null
                    true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}