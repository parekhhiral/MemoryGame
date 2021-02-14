package com.parekhhiral.memorygame

import android.animation.ArgbEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.parekhhiral.memorygame.models.BoardSize
import com.parekhhiral.memorygame.models.Game

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var clRoot : ConstraintLayout
    private lateinit var numMoves: TextView
    private lateinit var numPairs: TextView
    private lateinit var rvBoard: RecyclerView

    private lateinit var adapter: MemoryGameAdapter
    private lateinit var game: Game
    private var boardSize: BoardSize = BoardSize.HARD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numMoves = findViewById(R.id.numMoves)
        numPairs = findViewById(R.id.numPairs)
        rvBoard = findViewById(R.id.rvboard)
        clRoot = findViewById(R.id.clRoot)

        setupBoard()
    }

    private fun setupBoard() {
        game = Game(boardSize)
        adapter = MemoryGameAdapter(this, boardSize, game.cards,
            object : MemoryGameAdapter.CardClickListener {
                override fun onCardClicked(position: Int) {
                    updateGameWithFlip(position)
                }
            })
        rvBoard.adapter = adapter
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())
    }

    private fun updateGameWithFlip(position: Int) {
        if (game.haveWonGame()) {
            Snackbar.make(clRoot,"You already won!", Snackbar.LENGTH_LONG).show()
            return
        }

        if (game.isCardFaceUp(position)) {
            Snackbar.make(clRoot, "Invalid move!", Snackbar.LENGTH_LONG).show()
            return
        }

        if (game.flipCard(position)) {
            val color = ArgbEvaluator().evaluate(
                game.numPairsFound.toFloat() / boardSize.getNumPairs(),
                ContextCompat.getColor(this, R.color.color_progress_none),
                ContextCompat.getColor(this, R.color.color_progress_full)
            ) as Int

            numPairs.setTextColor(color)
            numPairs.text = "Pairs : ${game.numPairsFound} / ${boardSize.getNumPairs()}"
            if (game.haveWonGame()) {
                Snackbar.make(clRoot, "You won! Congratulations", Snackbar.LENGTH_LONG).show()
            }
        }
        numMoves.text = "Moves : ${game.getMoves()}"
        adapter.notifyDataSetChanged()
    }
}