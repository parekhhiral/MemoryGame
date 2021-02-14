package com.parekhhiral.memorygame.models

import com.parekhhiral.memorygame.utils.DEFAULT_ICONS

class Game(private val boardSize: BoardSize) {
    val cards: List<MemoryCard>
    var numPairsFound = 0

    private var numCardFlips = 0
    private var indexOfSingleSelectedCard: Int? = null

    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomCards = (chosenImages + chosenImages).shuffled()
        cards = randomCards.map { MemoryCard(it) }
    }

    fun flipCard(position: Int): Boolean {
        numCardFlips++
        val card = cards[position]

        // Three cases
        // 0 card previously flipped => restore cards + flip the selected card
        // 1 card previously flipped => flip the selected card + check if match found
        // 2 cards previously flipped => restore cads + flip the selected card
        var foundMatch = false
        if (indexOfSingleSelectedCard == null) {
            //0 or 2 cards previously flipped
            restoreCards()
            indexOfSingleSelectedCard = position
        } else {
            foundMatch = checkIfMatchFound(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
        return foundMatch
    }

    private fun checkIfMatchFound(position1: Int, position2: Int): Boolean {
        if (cards[position1].identifier != cards[position2].identifier) {
            return false
        }
        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairsFound++
        return true
    }

    private fun restoreCards() {
        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }

    fun haveWonGame(): Boolean {
        return numPairsFound == boardSize.getNumPairs()
    }

    fun isCardFaceUp(position: Int): Boolean {
        return cards[position].isFaceUp
    }

    fun getMoves(): Int {
        return numCardFlips / 2
    }
}