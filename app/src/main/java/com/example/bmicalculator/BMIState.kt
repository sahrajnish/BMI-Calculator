package com.example.bmicalculator

data class BMIState(
    val sheetTitle: String = "",
    val sheetItemList: List<String> = emptyList(),
    val weightUnit: String = "Kilogram",
    val heightUnit: String = "Centimeter",
    val weightValueStage: WeightValueStage = WeightValueStage.ACTIVE,
    val heightValueStage: HeightValueStage = HeightValueStage.INACTIVE,
    val weightValue: String = "60",
    val heightValue: String = "170",
    val shouldBMICardShow: Boolean = false,
    val bmi: Double = 0.0,
    val bmiStage: String = "",
)

enum class WeightValueStage {
    ACTIVE,
    INACTIVE,
    RUNNING
}

enum class HeightValueStage {
    ACTIVE,
    INACTIVE,
    RUNNING
}