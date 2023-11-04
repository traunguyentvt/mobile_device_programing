package com.tvt.assignment_4.models

import java.io.Serializable

data class User(val firstName: String, val lastName: String, val email: String, val password: String)
    :Serializable
