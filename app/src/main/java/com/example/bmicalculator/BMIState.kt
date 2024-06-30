package com.example.bmicalculator

data class BMIState(
    val sheetTitle: String = "",
    val sheetItemList: List<String> = emptyList(),
    val weightUnit: String = "Kilogram",
    val heightUnit: String = "Centimeter"
)