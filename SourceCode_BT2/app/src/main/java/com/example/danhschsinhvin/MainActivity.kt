@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.danhschsinhvin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.danhschsinhvin.ui.theme.DanhSáchSinhViênTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DanhSáchSinhViênTheme {
                StudentList()

            }
        }
    }
}

class Student(
    val name: String,
    val email: String?,
    val phone: String?
)

@Composable
fun StudentList(){
    val studentList = listOf(
        Student("Nguyen Van An", "an@sv.edu.vn", "0901234567"),
        Student("Tran Thi Binh", null, "0912888999"),
        Student("Le Minh Chi", "Chi@sv.edu.vn", null),
        Student("Pham Quoc Dung", null, null),
        Student("Vo Thanh Em", "em@sv.edu.vn", "0933111222")
    )
    var noEmail = 0
    for (student in studentList){
        if (student.email == null){
            noEmail++
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Danh sách sinh viên", color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = Color.White)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                for (student in studentList){
                    studentCard(student = student)
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            BotBar(
                totalCount = studentList.size,
                noEmail = noEmail
            )
        }
    }
}

@Composable
fun studentCard(student: Student){
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = student.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(6.dp))

            val displayEmail = student.email ?: "Chua cap nhat"
            val displayPhone = student.phone ?: "Chua cap nhat"

            Row {
                Text(text = "Email: ", color = Color.Gray, fontSize = 15.sp)
                Text(
                    text = displayEmail,
                    color = if (student.email == null) Color(0xFFB91C1C) else Color.Gray,
                    fontStyle = if (student.email == null) FontStyle.Italic else FontStyle.Normal,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Row {
                Text(text = "SDT: ", color = Color.Gray, fontSize = 15.sp)
                Text(
                    text = displayPhone,
                    color = if (student.phone == null) Color(0xFFB91C1C) else Color.Gray,
                    fontStyle = if (student.phone == null) FontStyle.Italic else FontStyle.Normal,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun BotBar(totalCount: Int, noEmail: Int){
    Surface (
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = Color.LightGray
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tong: $totalCount sinh vien",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Row {
                Text(text = "Thieu email: ", fontSize = 20.sp)
                Text(
                    text = "${noEmail}",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}
