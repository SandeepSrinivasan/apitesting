package com.dynamicdudes.apitesting

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dynamicdudes.apitesting.databinding.ActivityRegisterBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    private var firstName : String? = null
    private var lastName : String? = null
    private var email : String? = null
    private var password : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            firstName = binding.firstName.text.toString()
            lastName = binding.lastName.text.toString()
            email = binding.emailEditText.text.toString()
            password = binding.passwordEditText.text.toString()
            signup(firstName!!,lastName!!,email!!,password!!)

        }

    }
    private fun signup(firstName: String, lastName: String,
                       email: String, password: String){
        val retIn = RetrofitInstance.getRetrofitInstance().create(Api::class.java)
        val registerInfo = UserBody(firstName,lastName,email, password)
        retIn.registerUser(registerInfo).enqueue(object :

            Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    this@RegisterActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 201) {
                    Toast.makeText(this@RegisterActivity, "Registration success!", Toast.LENGTH_SHORT)
                        .show()

                }
                else{
                    Toast.makeText(this@RegisterActivity, "Registration failed!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }



}