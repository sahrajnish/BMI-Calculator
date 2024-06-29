package com.example.bmicalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.ui.theme.CustomOrange
import com.example.bmicalculator.ui.theme.GrayBackground

@Composable
fun BMIScreen(modifier: Modifier = Modifier) {
    ScreenContent()
}

@Composable
fun ScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBackground)
            .padding(15.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "BMI Calculator",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                UnitItem(
                    text = "Weight",
                    onClick = {}
                )
                InputUnitValue(
                    inputValue = "60",
                    inputUnit = "Kilograms",
                    inputColor = CustomOrange,
                    onUnitValueClicked = {}
                )

            }

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                UnitItem(
                    text = "Height",
                    onClick = {}
                )
                InputUnitValue(
                    inputValue = "170",
                    inputUnit = "Centimeters",
                    inputColor = CustomOrange,
                    onUnitValueClicked = {}
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            Divider()
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
            ) {
                NumberKeyboard(
                    modifier = Modifier,
                    onNumberClick = {}
                )
                Column(
                    modifier = Modifier
                ) {
                    SymbolButton(
                        symbol = "AC",
                        onClick = {}
                    )
                    SymbolButtonWithIcon(
                        onClick = {}
                    )
                    SymbolButton(
                        symbol = "GO",
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BMIScreenPrev() {
    BMIScreen()
}