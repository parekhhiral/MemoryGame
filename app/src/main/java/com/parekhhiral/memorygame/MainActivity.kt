package com.parekhhiral.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var numMoves: TextView
    private lateinit var numPairs: TextView
    private lateinit var rvBoard: RecyclerView

    private lateinit var adapter: MemoryGameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numMoves = findViewById(R.id.numMoves)
        numPairs = findViewById(R.id.numPairs)
        rvBoard = findViewById(R.id.rvboard)

        adapter = MemoryGameAdapter(this, 8)
        rvBoard.adapter = adapter
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this, 2)
    }
}