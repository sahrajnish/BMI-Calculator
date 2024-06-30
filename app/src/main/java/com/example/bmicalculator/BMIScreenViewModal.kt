package com.example.bmicalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

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
        }
    }

    private fun changeWeightOrHeightUnit(sheetItem: String) {
        if(state.sheetTitle == "Weight") {
            state = state.copy(weightUnit = sheetItem)
        } else if(state.sheetTitle == "Height") {
            state = state.copy(heightUnit = sheetItem)
        }
    }
}

sealed class UserAction {
    object weightTextClicked: UserAction()
    object heightTextClicked: UserAction()
    data class OnSheetItemClicked(val sheetItem: String): UserAction()
}