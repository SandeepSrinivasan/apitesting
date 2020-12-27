package com.dynamicdudes.apitesting

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dynamicdudes.apitesting.databinding.ActivityMainBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var email : String? = null
    private var password : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkLogin()

        binding.registerTextView.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)

        }
    }

    private fun checkLogin(){
        if(AppPreferences.isLogin){
            performIntent()
        }
        else{
            binding.loginButton.setOnClickListener{
                if (AppPreferences.isLogin) {
                    AppPreferences.isLogin = false
                    AppPreferences.email = ""
                    AppPreferences.password = ""
                }else{
                email = binding.emailEditText.text.toString()
                password = binding.passwordEditText.text.toString()
                signin(email!!,password!!)
                }
            }
        }
    }

    private fun performIntent(){
        val intent = Intent(this,HomePage::class.java)
        startActivity(intent)
    }

    private fun signin(email: String, password: String){
        val retIn = RetrofitInstance.getRetrofitInstance().create(Api::class.java)
        val signInInfo = SignInBody(email, password)
        retIn.signin(signInInfo).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 201) {
                    AppPreferences.isLogin = true
                    AppPreferences.email = email
                    AppPreferences.password = password
                    performIntent()
                    Toast.makeText(this@MainActivity, "Login success!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Login failed!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}