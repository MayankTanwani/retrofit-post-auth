package com.example.mayank.shoocal.Model

data class User(
       val first_name : String,
       val last_name : String,
       val phone : String,
       val address : String,
       val restrau_name : String,
        val requestby : Int
        ){
        override fun toString(): String {
                return "{\n" +
                        "\t\"first_name\" : " + first_name + ",\n" +
                        "\t\"last_name\" : " + last_name + ",\n" +
                        "\t\"phone\" :  " + phone + ",\n" +
                        "\t\"address\" : " + address + ",\n" +
                        "\t\"restrau_name\" : " + restrau_name + ",\n" +
                        "\t\"requestby\" : " + requestby + " + " +
                        "}"
        }
}
