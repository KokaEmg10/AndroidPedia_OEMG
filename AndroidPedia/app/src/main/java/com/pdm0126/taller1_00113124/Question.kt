package com.pdm0126.taller1_00113124

data class Question(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val funFact: String
)

val quizQuestions = listOf(
    Question(
        id = 1,
        question = "¿Quiénes fueron los fundadores originales de Android Inc. en Palo Alto?",
        options = listOf(
            "Andy Rubin, Rich Miner, Nick Sears y Chris White",
            "Steve Jobs y Elon Musk",
            "Bill Gates y Jeff Bezos",
            "Cristiano Ronaldo y Leo Messi"
        ),
        correctAnswer = "Andy Rubin, Rich Miner, Nick Sears y Chris White",
        funFact = "Originalmente, Android tenia como visión el ser un sistema operativo para cámaras."
    ),
    Question(
        id = 2,
        question = "¿Cuál era el nombre del prototipo de Android que se parecía a una BlackBerry antes del iPhone?",
        options = listOf("WhiteBerry", "Sooner", "AndroidMovil", "iPhone 0"),
        correctAnswer = "Sooner",
        funFact = "El 'Sooner' no tenía pantalla táctil. Pero tras la salida del iPhone en 2007, el equipo tuvo que rediseñar todo para competir."
    ),
    Question(
        id = 3,
        question = "¿Qué característica técnica hizo que empresas como HTC y Motorola impulsaran Android rápidamente?",
        options = listOf(
            "Era un sistema cerrado y exclusivo",
            "Su código abierto basado en el kernel de Linux facil de usar.",
            "Que solo funcionaba con servicios de Microsoft.",
            "Que podía correr Clash Royale."
        ),
        correctAnswer = "Su código abierto basado en el kernel de Linux facil de usar.",
        funFact = "Al ser accesible y gratuito para los fabricantes, Android logró expandirse rapidamente al ser impulsado por otras empresas."
    )
)