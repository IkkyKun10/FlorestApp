package com.riezki.florestapp.ui.auth.login

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
import com.riezki.florestapp.MainActivity
import com.riezki.florestapp.R
import com.riezki.florestapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        textChangeListener()
        binding.loginButton.setOnClickListener {
            if (validateInput()) {
                val email = binding.emailInput.text.toString()
                val password = binding.password.text.toString()
                signInFirebaseAuth(email, password)
            }
        }
    }

    private fun signInFirebaseAuth(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Berhasil Login", Toast.LENGTH_SHORT).show()
                    Intent(context, MainActivity::class.java).also { its ->
                        startActivity(its)
                        activity?.finish()
                    }
                } else {
                    Toast.makeText(context, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun textChangeListener() {
        binding.emailInput.addTextChangedListener(object : TextWatcher {
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
    }

    private fun validateEmail(text: String) {
        with(binding) {
            if (text.trim().isEmpty()) {
                emailInput.error = "Username tidak boleh kosong"
            } else {
                emailInput.error = null
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

    private fun validateInput(): Boolean {
        with(binding) {
            return when {
                emailInput.text?.trim().isNullOrEmpty() -> {
                    emailInput.error = "Silahkan isi email terlebih dahulu"
                    false
                }
                (password.text?.length ?: 0) < 6 -> {
                    password.error = "Password minimal 8 karakter"
                    false
                }
                !Patterns.EMAIL_ADDRESS.matcher(emailInput.toString()).matches() -> {
                    emailInput.error = "Email tidak valid"
                    false
                }
                else -> {
                    emailInput.error = null
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