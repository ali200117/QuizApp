package com.example.quizapp.data
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange

class QuizStore {

    var question by mutableStateOf<quizQuestions?>(null)
        private set

    var selectedOption by mutableStateOf<String?>(null)
        private set

    var isCorrect by mutableStateOf<Boolean?>(null)
        private set

    private var items : List<GalleryItem> = emptyList()
    private val optionCount = 4

    var scoreCounter by mutableStateOf(0)
        private set


    //start quizzen -- alt nullstilt
    fun start (sourceItems: List<GalleryItem>) {
        items = sourceItems
        selectedOption = null
        isCorrect = null
        scoreCounter = 0
        newQuestion()

    }


    //sjekke answer og det bruker trykker
    fun answer (option : String) {

        val q = question?: return
        if (selectedOption != null) return
        selectedOption = option
        val correctName = q.correctItem.name
        val riktigSvar = option == correctName
        isCorrect = riktigSvar

        if(riktigSvar){
            scoreCounter += 1
        }

    }


    fun next () {
        selectedOption = null
        isCorrect = null
        newQuestion()
    }

    //hvis spørsmål er mindre enn antall options
    private fun newQuestion() {
        if (items.size < optionCount) {
            question = null
            return
        }

        val correctItem = items.random()
        val wrongAnswers = items
            .asSequence()
            .filter { it.name != correctItem.name }
            .map { it.name }
            //ingen duplicate
            .distinct()
            .shuffled()
            //henter antall options minus den riktige
            .take(optionCount - 1)
            //legge alt i liste
            .toList()

        //legge og shuffle  feil og riktig options som question
        val options = (wrongAnswers + correctItem.name).shuffled()
        question = quizQuestions(correctItem, options)
    }



}