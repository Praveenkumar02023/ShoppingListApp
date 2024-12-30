package com.praveen.myshoppinglist

data class LocationData(
    val latitude : Double,
    val longitude : Double,
)

data class GeocodingRespose(
    val results: List<GeocodingResult>,
    val status:  String
)

data class GeocodingResult(
    val formatted_address : String
)