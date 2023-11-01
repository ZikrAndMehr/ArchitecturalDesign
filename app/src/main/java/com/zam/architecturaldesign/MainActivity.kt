package com.zam.architecturaldesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zam.architecturaldesign.data.QuoteDataSource
import com.zam.architecturaldesign.data.QuoteDatabase
import com.zam.architecturaldesign.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quoteDatabase by lazy { QuoteDatabase.getInstance(this) }
    val quoteDataSource by lazy { QuoteDataSource(quoteDatabase.quoteDao()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}