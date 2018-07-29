package com.example.mayank.shoocal.Model

data class ApiResponse(
        var sucess : Boolean,
        var error : Boolean,
        var message : ArrayList<Message>
)