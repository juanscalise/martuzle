package com.example.martuzle

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.LinearLayout
import androidx.transition.Visibility
import com.example.martuzle.BaseApplication.Companion.prefs
import com.example.martuzle.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var digitsPressed = 0
    private var intents = 10
    private lateinit var images: List<LinearLayout>
    private lateinit var imagesDiscovered: ArrayList<Int>
    private var number = 0
    private var userNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        images = arrayListOf(binding.ll01, binding.ll02, binding.ll03, binding.ll04, binding.ll05, binding.ll06, binding.ll07, binding.ll08, binding.ll09, binding.ll10,
            binding.ll11, binding.ll12, binding.ll13, binding.ll14, binding.ll15, binding.ll16, binding.ll17, binding.ll18, binding.ll19, binding.ll20,
            binding.ll21, binding.ll22, binding.ll23, binding.ll24, binding.ll25, binding.ll26, binding.ll27, binding.ll28, binding.ll29, binding.ll30,
            binding.ll31, binding.ll32, binding.ll33, binding.ll34, binding.ll35, binding.ll36)

        imagesDiscovered = prefs.getImagesDiscovered()
        number = resetNumber()
        binding.tvSolution.text = "$number"

        binding.tvIntents.text = "$intents" + " INTENTOS"

        binding.d0.setOnClickListener{
            fillDisplay("0")
        }
        binding.d1.setOnClickListener{
            fillDisplay("1")
        }
        binding.d2.setOnClickListener{
            fillDisplay("2")
        }
        binding.d3.setOnClickListener{
            fillDisplay("3")
        }
        binding.d4.setOnClickListener{
            fillDisplay("4")
        }
        binding.d5.setOnClickListener{
            fillDisplay("5")
        }
        binding.d6.setOnClickListener{
            fillDisplay("6")
        }
        binding.d7.setOnClickListener{
            fillDisplay("7")
        }
        binding.d8.setOnClickListener{
            fillDisplay("8")
        }
        binding.d9.setOnClickListener{
            fillDisplay("9")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillDisplay(number: String) {
        digitsPressed++
        if(digitsPressed == 1)
            binding.dg0.text = number
        else if(digitsPressed == 2)
            binding.dg1.text = number
        else if(digitsPressed == 3)
            binding.dg2.text = number
        else if(digitsPressed == 4)
            binding.dg3.text = number
        else if(digitsPressed == 5) {
            binding.dg4.text = number
            verifyNumber()
        }
        else{
            digitsPressed = 1
            binding.dg0.text = number
            binding.dg1.text = "#"
            binding.dg2.text = "#"
            binding.dg3.text = "#"
            binding.dg4.text = "#"
            intents--

            if (intents == 0) {
                intents = 10
                resetNumber()
                binding.tvSolution.text = "$number"
            }
            binding.tvIntents.text = "$intents" + " INTENTOS"
        }
    }

    private fun verifyNumber() {
        val win = true
        if(win)
            award(randomElement())
    }

    private fun award(randomElement: Int) {
        images[randomElement].visibility = View.VISIBLE
        imagesDiscovered.add(randomElement)
        if(imagesDiscovered.size == 36)
            binding.btnAward.visibility = View.VISIBLE
    }

    private fun resetNumber(): Int {
        return Random.nextInt(0, 99999)
    }

    fun randomElement(): Int {
        val randomIndex = Random.nextInt(images.size)
        return if (imagesDiscovered.contains(randomIndex)) {
            randomElement()
        } else
            return randomIndex
    }
}