package com.example.bitpthchnh03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bitpthchnh03.ui.theme.BàiTậpThựcHành03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BàiTậpThựcHành03Theme {
                baiThucHanh()
            }
        }
    }
}

@Composable
fun baiThucHanh(){
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    fun calculate(operation: (Double, Double) -> Double) {
        val n1 = number1.toDoubleOrNull()
        val n2 = number2.toDoubleOrNull()

        if (n1 == null || n2 == null) {
            result = "hãy nhập đủ 2 số"
        } else {
            val res = operation(n1, n2)
            result = if (res % 1.0 == 0.0) {
                res.toLong().toString()
            } else {
                res.toString()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Thực Hành 03",
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(45.dp))
        TextField(
            value = number1,
            onValueChange = {number1 = it},
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {calculate { a,b -> a + b}},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(75.dp)
                    .height(75.dp)
            ){
                Text("+", fontSize = 25.sp)
            }
            Button(
                onClick = { calculate { a, b -> a - b } },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE5A642)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(75.dp)
                    .height(75.dp)
            ){
                Text("-", fontSize = 25.sp)

            }
            Button(
                onClick = {calculate { a, b -> a * b } },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E35B1)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(75.dp)
                    .height(75.dp)
            ){
                Text("*", fontSize = 25.sp)
            }
            Button(onClick = {
                val n1 = number1.toDoubleOrNull()
                val n2 = number2.toDoubleOrNull()
                if (n1 == null || n2 == null) {
                    result = "hãy nhập đủ 2 số"
                } else if (n2 == 0.0) {
                    result = "Không thể chia cho 0"
                } else {
                    val res = n1 / n2
                    result = if (res % 1.0 == 0.0) res.toLong().toString() else res.toString()
                }
            },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF212121)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(75.dp)
                    .height(75.dp)
            ) {
                Text("/", fontSize = 25.sp)
            }
        }
        Spacer(modifier = Modifier.height(35.dp))
        TextField(
            value = number2,
            onValueChange = {number2 = it},
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Text(
                text = "Kết quả: $result",
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
            )
        }
    }

}