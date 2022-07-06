package com.example.martuzle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.martuzle.databinding.ActivityGiftBinding


class GiftActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGiftBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGiftBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.llShowLetter.setOnClickListener{
            if(binding.etPassword.text.toString() == "bebe")
                binding.tvLetter.visibility = View.VISIBLE
        }
    }
}