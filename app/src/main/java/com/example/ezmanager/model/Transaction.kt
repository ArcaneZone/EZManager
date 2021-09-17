package com.example.ezmanager.model

import java.util.*

data class Transaction(
    val id:String,
    val title:String,
    val amount: Int,
    val date:String,
    val type:String
)