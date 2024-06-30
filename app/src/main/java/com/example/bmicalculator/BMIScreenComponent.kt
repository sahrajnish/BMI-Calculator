package com.example.bmicalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.ui.theme.CustomBlue
import com.example.bmicalculator.ui.theme.CustomGray
import com.example.bmicalculator.ui.theme.CustomGreen
import com.example.bmicalculator.ui.theme.CustomOrange
import com.example.bmicalculator.ui.theme.CustomRed

@Composable
fun UnitItem(
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 22.sp
        )
        androidx.compose.material3.Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Select Icon"
        )
    }
}

@Composable
fun InputUnitValue(
    inputValue: String,
    inputUnit: String,
    inputColor: Color,
    onUnitValueClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = inputValue,
            fontSize = 40.sp,
            color = inputColor,
            modifier = Modifier
                .clickable { onUnitValueClicked() }
        )
        Text(
            text = inputUnit,
            fontSize = 12.sp
        )
    }
}

@Composable
fun NumberButton(
    modifier: Modifier = Modifier,
    number: String,
    onClick: (String) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(vertical = 20.dp)
            .clip(CircleShape)
            .clickable { onClick(number) }
    ) {
        Text(
            text = number,
            fontSize = 40.sp
        )
    }
}

@Composable
fun ColumnScope.SymbolButton(
    symbol: String,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(CustomGray)
            .weight(1f)
            .aspectRatio(1f)
            .clickable { onClick() }
    ) {
        Text(
            text = symbol,
            fontSize = 26.sp,
            color = CustomOrange
        )
    }
}

@Composable
fun ColumnScope.SymbolButtonWithIcon(
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(CustomGray)
            .weight(1f)
            .aspectRatio(1f)
            .clickable { onClick() }

    ) {
        androidx.compose.material3.Icon(
            painter = painterResource(id = R.drawable.ic_backspace),
            contentDescription = "Delete Icon",
            tint = CustomOrange
        )
    }
}

@Composable
fun NumberKeyboard(
    modifier: Modifier = Modifier,
    onNumberClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.7f)
    ) {
        val numberButtonList = listOf(
            "7","8","9","4","5","6","1","2","3","","0",".")
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .aspectRatio(0.7f)
        ) {
            items(numberButtonList) { item ->
                NumberButton(
                    modifier = modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    number = item,
                    onClick = onNumberClick
                )
            }
        }
    }
}

@Composable
fun BMIResultCard(
    bmi: Double,
    bmiStage: String = "Normal",
    bmiStageColor: Color = CustomGreen
) {
    Column(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(15.dp)
            )
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "$bmi",
                fontSize = 70.sp,
                color = CustomOrange
            )

            Spacer(modifier = Modifier.width(15.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "BMI",
                    fontSize = 40.sp,
                    color = Color.Gray
                )
                Text(
                    text = bmiStage,
                    fontSize = 18.sp,
                    color = bmiStageColor
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Divider(
            modifier = Modifier
                .background(Color.Gray)
                .shadow(elevation = 5.dp),
            thickness = 5.dp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Information",
            fontSize = 20.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Underweight",
                color = CustomBlue
            )
            Text(
                text = "Normal",
                color = CustomGreen
            )
            Text(
                text = "Overweight",
                color = CustomRed
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Divider(
                modifier = Modifier
                    .weight(1f),
                thickness = 5.dp,
                color = CustomBlue
            )
            Divider(
                modifier = Modifier
                    .weight(1f),
                thickness = 5.dp,
                color = CustomGreen
            )
            Divider(
                modifier = Modifier
                    .weight(1f),
                thickness = 5.dp,
                color = CustomRed
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "16.0",
                color = Color.DarkGray,
                fontSize = 18.sp
            )
            Text(
                text = "18.5",
                color = Color.DarkGray,
                fontSize = 18.sp
            )
            Text(
                text = "25.0",
                color = Color.DarkGray,
                fontSize = 18.sp
            )
            Text(
                text = "40.0",
                color = Color.DarkGray,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun ShareButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = CustomOrange
        )
    ) {
        Text(
            text = "Share",
            fontSize = 18.sp,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
fun BottomSheetContent(
    sheetTitle: String,
    sheetItemList: List<String>,
    onItemClicked: (String) -> Unit,
    onCancelClicked: () -> Unit
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        text = sheetTitle,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
    sheetItemList.forEach { item ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClicked(item) }
        ) {
            Text(
                text = item,
                modifier = Modifier
                    .padding(15.dp)
            )
        }
    }
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        onClick = onCancelClicked,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        )
    ) {
        Text(
            text = "Cancel"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Prev() {
    ShareButton {

    }
}