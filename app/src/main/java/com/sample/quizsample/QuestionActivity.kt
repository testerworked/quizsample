package com.sample.quizsample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.util.Pair


class QuestionActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private var score: Int = 0
    private val questList = listOf(
        Question(
            "Когда была основана первая Болгарская империя?",
            "681 год",
            listOf("681 год", "864 год", "927 год", "1185 год")
        ),
        Question(
            "Кто был первым болгарским царём?",
            "Борис I",
            listOf("Симеон I", "Борис I", "Асен I", "Калоян")
        ),
        Question(
            "Какое событие произошло в 1396 году в истории Болгарии?",
            "Начало османского завоевания",
            listOf("Начало османского завоевания", "Принятие христианства", "Освобождение от византийского владычества", "Создание Тырновской конституции")
        ),
        Question(
            "Кто является святым покровителем Болгарии?",
            "Св. Иван Рильский",
            listOf("Св. Иван Рильский", "Св. Симеон", "Св. Кирилл", "Св. Христофор")
        ),
        Question(
            "Когда Болгария объявила независимость от Османской империи?",
            "1908 год",
            listOf("1878 год", "1908 год", "1944 год", "1989 год")
        ),
        Question(
            "Какой важный культурный и исторический центр находится в Старой Загоре?",
            "Пловдив",
            listOf("Пловдив", "Тырново", "Велико Тырново", "Несебр")
        ),
        Question(
            "Какое событие знали как 'Восстание Апрельское'?",
            "Восстание против османского владычества в 1876 году",
            listOf("Восстание против римского владычества", "Восстание против османского владычества в 1876 году", "Восстание во время Второй мировой войны", "Восстание против советской власти в 1945 году")
        ),
        Question(
            "Кто подписал Сан-Стефанский мирный договор, который положил конец русско-турецкой войне 1877-1878 годов?",
            "Александр II",
            listOf("Александр II", "Фердинанд I", "Борис III", "Иван Вазов")
        )
    )

    private val correctAnswers = listOf(
        "681 год",
        "Борис I",
        "Начало османского завоевания",
        "Св. Иван Рильский",
        "1908 год",
        "Пловдив",
        "Восстание против османского владычества в 1876 году",
        "Александр II"
    )

    private var questionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question)

        radioGroup = findViewById(R.id.radioGroup)
        score = intent.getIntExtra("score", 0)

        loadQuestion()
        findViewById<Button>(R.id.nextButton).setOnClickListener { checkAnswer() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadQuestion() {
        if (questionIndex < questList.size) {
            val question = questList[questionIndex]
            findViewById<TextView>(R.id.questions).text = question.question

            radioGroup.removeAllViews()
            for (answer in question.options) {
                val radioButton = RadioButton(this)
                radioButton.text = answer
                radioGroup.addView(radioButton)
            }
        } else {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("score", score)
            startActivity(intent)
            finish()
        }
    }

    private fun checkAnswer() {
        val selectedId = radioGroup.checkedRadioButtonId
        if (selectedId != -1) {
            val selectedButton = findViewById<RadioButton>(selectedId)
            val correctAnswer = correctAnswers[questionIndex]

            if (selectedButton.text == correctAnswer) {
                score += 100
                Toast.makeText(this, "Правильный ответ!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Неправильный ответ. Правильный ответ: $correctAnswer", Toast.LENGTH_SHORT).show()
            }

            questionIndex++
            loadQuestion()
        } else {
            Toast.makeText(this, "Выберите ответ", Toast.LENGTH_SHORT).show()
        }
    }
}