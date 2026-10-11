@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.xploihclc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xploihclc.ui.theme.XếpLoạiHọcLựcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XếpLoạiHọcLựcTheme {
                AcademicRankUI()

            }
        }
    }
}


fun academicRank(score: Double): String{
    return if (score < 0 || score >10){
        "không hợp lệ"
    }
    else if (score >= 8.0){
        "giỏi"
    }
    else if (score >= 6.5){
        "khá"
    }
    else if (score >= 5.0){
        "trung bình"
    }
    else{
        "yếu"
    }
}

fun rankColor(rank: String): Color{
    return if (rank == "giỏi"){
        Color.Green
    }
    else if (rank == "khá"){
        Color.Blue
    }
    else if (rank == "trung bình"){
        Color.Yellow
    }
    else if (rank == "yếu"){
        Color.Red
    }
    else {
        Color.LightGray
    }
}

@Composable
fun AcademicRankUI(){
    val score = 8.5
    val rank = academicRank(score)
    val test = listOf(9.0, 7.0, 5.5, 3.0, 11.0)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Xếp loại học lực", color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
            )
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = Color.White)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Điểm trung bình",
                    )
                    Text(
                        text = "$score",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold
                    )
                    RankBadge(rank = rank, modifier = Modifier.fillMaxWidth())
                }
            }
            Text(
                text = "KIỂM TRA THÊM"
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(5.dp)) {
                    for((index, score) in test.withIndex()){
                        val rank = academicRank(score)
                        val formattedScore = if (score % 1.0 == 0.0) score.toInt().toString() else score.toString()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Text(
                                text = "Điểm $formattedScore",
                            )
                            RankBadge(rank = rank)
                        }

                    }
                }
            }
        }

    }
}

@Composable
fun RankBadge(rank: String, modifier: Modifier = Modifier) {
    val rankColor = rankColor(rank)

    Surface (
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = rankColor.copy(alpha = 0.1f)
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = rank,
                color = rankColor,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}