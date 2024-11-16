package com.sample.quizsample

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("score", 0)

        val resultText = when {
            score == 500 -> "Отличный знаток истории!"
            score >= 400 -> "Хороший знаток истории."
            score >= 300 -> "Удовлетворительный уровень знаний."
            score >= 200 -> "Неплохой, но можно лучше."
            else -> "Плохой результат, следует учиться больше."
        }

        findViewById<TextView>(R.id.descriptionText).text = "Ваши баллы: $score\n$resultText"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}