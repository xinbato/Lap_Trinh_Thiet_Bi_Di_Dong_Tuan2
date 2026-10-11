@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.bngimlp

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           GradeBoard()
        }
    }
}

class Student(
    val name: String,
    val score: Double?
)

fun academicRank(score: Double?): String{
    if (score == null){
        return "Chưa có điểm"
    }
    return if (score < 0 || score >10){
        "Không hợp lệ"
    }
    else if (score >= 8.0){
        "Giỏi"
    }
    else if (score >= 6.5){
        "Khá"
    }
    else if (score >= 5.0){
        "Trung bình"
    }
    else{
        "Yếu"
    }
}

fun rankColor(rank: String): Color{
    return if (rank == "Giỏi"){
        Color.Green
    }
    else if (rank == "Khá"){
        Color.Blue
    }
    else if (rank == "Trung bình"){
        Color.Yellow
    }
    else if (rank == "Yếu"){
        Color.Red
    }
    else {
        Color.LightGray
    }
}

@Composable
fun GradeBoard(){
    val studentList = listOf(
        Student("Nguyễn Văn An", 9.0),
        Student("Trần Thị Bình", 7.2),
        Student("Lê Minh Chi", null),
        Student("Phạm Quốc Dũng", 4.5),
        Student("Võ Thanh Em",5.8)
    )
    var totalScore = 0.0
    var validScoreCount = 0
    var excellentCount = 0
    var missingScoreCount = 0
    var maxScore = 0.0

    for (student in studentList){
        if (student.score != null){
            val score = student.score
            totalScore += score
            validScoreCount++

            if (score >= 8.0){
                excellentCount++
            }
            if(score > maxScore){
                maxScore = score
            }
        }
        else{
            missingScoreCount++
        }
    }
    val avgScore = if (validScoreCount > 0) totalScore / validScoreCount else 0.0

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
                .padding(15.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Blue)
            ) {
                Column(modifier = Modifier.padding(20.dp)){
                    Text(
                        text = "Điểm trung bình lớp",
                        color = Color.White,
                        fontSize = 15.sp
                    )
                    Text(
                        text = String.format(Locale.US, "%.2f", avgScore),
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        StatItem(label = "Giỏi", value = "$excellentCount")
                        StatItem(label = "Chưa có điểm", value = "$missingScoreCount")
                        StatItem(label = "Cao Nhất", value = String.format(Locale.US, "%.1f", maxScore))
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "DANH SÁCH • ${studentList.size} SINH VIÊN"
            )
            Spacer(modifier = Modifier.height(4.dp))
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                for (student in studentList){
                StudentItemCard(student = student)
                }
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String){
    Column{
        Text(
            text = label,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun StudentItemCard(student: Student){
    val rank = academicRank(student.score)
    val rankColor = rankColor(rank)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column{
                Text(
                    text = student.name,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = rank,
                    color = rankColor
                )
            }
            Text(
                text = student.score?.let{ String.format(Locale.US,"%.1f",it)} ?: "_",
                color = if (student.score == null) Color.LightGray else Color(0xFF1F2937)
            )
        }
    }
}