package com.pdm0126.taller1_00113124

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AndroidPediaApp()
                }
            }
        }
    }
}

@Composable
fun AndroidPediaApp() {
    var currentScreen by remember { mutableStateOf("WELCOME") }
    var score by remember { mutableStateOf(0) }

    when (currentScreen) {
        "WELCOME" -> WelcomeScreen(onStart = { currentScreen = "QUIZ" })
        "QUIZ" -> QuizScreen(
            onFinish = { finalScore ->
                score = finalScore
                currentScreen = "RESULT"
            }
        )
        "RESULT" -> ResultScreen(
            score = score,
            onRestart = {
                score = 0
                currentScreen = "WELCOME"
            }
        )
    }
}

@Composable
fun WelcomeScreen(onStart: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("AndroidPedia", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3DDC84))
        Text("¿Cuánto sabes de Android?", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(40.dp))
        Text("Estudiante: Oscar Eduardo Mercado Guerra", fontWeight = FontWeight.Medium)
        Text("Carnet: 00113124")
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = onStart, modifier = Modifier.fillMaxWidth()) {
            Text("Comenzar Quiz")
        }
    }
}

@Composable
fun QuizScreen(onFinish: (Int) -> Unit) {
    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var answered by remember { mutableStateOf(false) }

    val currentQuestion = quizQuestions[currentIndex]

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Pregunta ${currentIndex + 1} de ${quizQuestions.size}")
            Text("Puntaje: $score / ${quizQuestions.size}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text(
                text = currentQuestion.question,
                modifier = Modifier.padding(16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        currentQuestion.options.forEach { option ->
            val isCorrect = option == currentQuestion.correctAnswer
            val buttonColor = when {
                !answered -> MaterialTheme.colorScheme.primary
                isCorrect -> Color(0xFF4CAF50)
                option == selectedOption && !isCorrect -> Color(0xFFF44336)
                else -> Color.Gray
            }

            Button(
                onClick = {
                    if (!answered) {
                        selectedOption = option
                        answered = true
                        if (isCorrect) score++
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                enabled = !answered || option == selectedOption || isCorrect
            ) {
                Text(option)
            }
        }

        if (answered) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Dato curioso: ${currentQuestion.funFact}", textAlign = TextAlign.Center, color = Color.DarkGray)
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    if (currentIndex < quizQuestions.size - 1) {
                        currentIndex++
                        answered = false
                        selectedOption = null
                    } else {
                        onFinish(score)
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(if (currentIndex < quizQuestions.size - 1) "Siguiente" else "Ver Resultado")
            }
        }
    }
}

@Composable
fun ResultScreen(score: Int, onRestart: () -> Unit) {
    val message = when (score) {
        3 -> "¡Excelente! Eres un experto en Android."
        2 -> "¡Muy bien! Conoces bastante la historia de Android."
        1 -> "Buen intento, pero aun hace falta estudiar un poco mas."
        else -> "Aun falta mucho por estudiar, pero solo al equivocarse uno llega a aprender."
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Obtuviste $score de 3", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(message, textAlign = TextAlign.Center, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = onRestart) {
            Text("Reiniciar Quiz")
        }
    }
}