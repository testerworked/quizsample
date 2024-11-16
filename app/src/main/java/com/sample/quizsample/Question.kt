package com.sample.quizsample

data class Question(
    val question: String,
    val correctAnswer: String,
    val options: List<String>
)