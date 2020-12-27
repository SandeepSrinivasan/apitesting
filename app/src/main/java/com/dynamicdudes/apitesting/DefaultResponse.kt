package com.dynamicdudes.apitesting

data class DefaultResponse(val message : String)


data class UserBody(val first_name : String, val last_name : String , val email: String, val password : String)

data class SignInBody(var email : String , val password : String)
