package com.example.bmicalculator

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class BMIScreenViewModal: ViewModel() {

    var state by mutableStateOf(BMIState())

    fun onAction(userAction: UserAction) {
        when(userAction) {
            UserAction.weightTextClicked -> {
                state = state.copy(
                    sheetTitle = "Weight",
                    sheetItemList = listOf("Kilogram", "Pound")
                )
            }

            UserAction.heightTextClicked -> {
                state = state.copy(
                    sheetTitle = "Height",
                    sheetItemList = listOf("Centimeter", "Meter", "Foot", "Inch")
                )
            }

            is UserAction.OnSheetItemClicked -> {
                changeWeightOrHeightUnit(userAction.sheetItem)
            }

            is UserAction.OnNumberClicked -> {
                enterNumber(userAction.number)
            }

            UserAction.HeightValueClicked -> {
                state = state.copy(
                    heightValueStage = HeightValueStage.ACTIVE,
                    weightValueStage = WeightValueStage.INACTIVE,
                    shouldBMICardShow = false
                )
            }

            UserAction.WeightValueClicked -> {
                state = state.copy(
                    heightValueStage = HeightValueStage.INACTIVE,
                    weightValueStage = WeightValueStage.ACTIVE,
                    shouldBMICardShow = false
                )
            }

            UserAction.AllClearButtonClicked -> {
                allClearToZero()
            }

            UserAction.DeleteButtonClicked -> {
                deleteLastDigit()
            }

            is UserAction.GoButtonClicked -> {
                calculateBMI(userAction.context)
            }
        }
    }

    private fun calculateBMI(
        context: Context
    ) {
        val weightInKgs: Double = when(state.weightUnit) {
            "Pound" -> state.weightValue.toDouble().times(0.4536)
            else -> state.weightValue.toDouble()
        }

        val heightInMeters: Double = when(state.heightUnit) {
            "Centimeter" -> state.heightValue.toDouble().times(0.01)
            "Foot" -> state.heightValue.toDouble().times(0.3048)
            "Inch" -> state.heightValue.toDouble().times(0.0254)
            else -> state.heightValue.toDouble()
        }

        try {
            val bmiValue = weightInKgs / (heightInMeters * heightInMeters)
            val bmiValueWithDecimal = (bmiValue * 10).roundToInt() / 10.0
            val bmiState = when(bmiValueWithDecimal) {
                in 0.0.. 18.49 -> "Underweight"
                in 18.5.. 24.99 -> "Normal"
                in 25.00..100.00 -> "Overweight"
                else -> "Invalid"
            }

            state = state.copy(
                bmi = bmiValueWithDecimal,
                bmiStage = bmiState,
                shouldBMICardShow = true
            )
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Invalid Inputs",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun deleteLastDigit() {
        if(state.weightValueStage != WeightValueStage.INACTIVE) {
            state = state.copy(
                weightValue = if (state.weightValue.length == 1) "0"
                else state.weightValue.dropLast(1)
            )
        } else if (state.heightValueStage != HeightValueStage.INACTIVE) {
            state = state.copy(
                heightValue = if (state.heightValue.length == 1) "0"
                else state.heightValue.dropLast(1)
            )
        }
    }

    private fun allClearToZero() {
        if(state.weightValueStage != WeightValueStage.INACTIVE) {
            state = state.copy(
                weightValue = "0",
                weightValueStage = WeightValueStage.ACTIVE
            )
        } else if(state.heightValueStage != HeightValueStage.INACTIVE) {
            state = state.copy(
                heightValue = "0",
                heightValueStage = HeightValueStage.ACTIVE
            )
        }
    }

    private fun changeWeightOrHeightUnit(sheetItem: String) {
        if(state.sheetTitle == "Weight") {
            state = state.copy(weightUnit = sheetItem)
        } else if(state.sheetTitle == "Height") {
            state = state.copy(heightUnit = sheetItem)
        }
    }

    private fun enterNumber(number: String) {
        when {
            state.weightValueStage == WeightValueStage.ACTIVE -> {
                state = state.copy(
                    weightValue = if(number == ".") "0." else number,
                    weightValueStage = WeightValueStage.RUNNING
                )
            }

            state.weightValueStage == WeightValueStage.RUNNING -> {
                if(
                    state.weightValue.contains(".").not() &&
                    state.weightValue.length <= 3
                ) {
                    if(state.weightValue.length <= 2 && number != ".") {
                        state = state.copy(
                            weightValue = state.weightValue + number,
                            weightValueStage = WeightValueStage.RUNNING
                        )
                    } else if(number == ".") {
                        state = state.copy(
                            weightValue = state.weightValue + number,
                            weightValueStage = WeightValueStage.RUNNING
                        )
                    }
                } else if(
                    state.weightValue.contains(".") &&
                    state.weightValue.reversed().indexOf(".") < 2
                ) {
                    state = state.copy(
                        weightValue = state.weightValue + number,
                        weightValueStage = WeightValueStage.RUNNING
                    )
                }
            }

            state.heightValueStage == HeightValueStage.ACTIVE -> {
                state = state.copy(
                    heightValue = if(number == ".") "0." else number,
                    heightValueStage = HeightValueStage.RUNNING
                )
            }

            state.heightValueStage == HeightValueStage.RUNNING -> {
                if(
                    state.heightValue.contains(".").not() &&
                    state.heightValue.length <= 3
                ) {
                    if(state.heightValue.length <= 2 && number != ".") {
                        state = state.copy(
                            heightValue = state.heightValue + number,
                            heightValueStage = HeightValueStage.RUNNING
                        )
                    } else if (number == ".") {
                        state = state.copy(
                            heightValue = state.heightValue + number,
                            heightValueStage = HeightValueStage.RUNNING
                        )
                    }
                } else if(
                    state.heightValue.contains(".") &&
                    state.heightValue.reversed().indexOf(".") < 2
                ) {
                    state = state.copy(
                        heightValue = state.heightValue + number,
                        heightValueStage = HeightValueStage.RUNNING
                    )
                }
            }
        }
    }
}

sealed class UserAction {
    object weightTextClicked: UserAction()
    object heightTextClicked: UserAction()
    object WeightValueClicked: UserAction()
    object HeightValueClicked: UserAction()
    object AllClearButtonClicked: UserAction()
    object DeleteButtonClicked: UserAction()
    data class GoButtonClicked(val context: Context): UserAction()
    data class OnNumberClicked(val number: String): UserAction()
    data class OnSheetItemClicked(val sheetItem: String): UserAction()
}