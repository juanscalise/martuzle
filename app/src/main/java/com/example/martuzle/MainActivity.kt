package com.example.martuzle

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.martuzle.BaseApplication.Companion.prefs
import com.example.martuzle.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.thread
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val INTENTS = 60 * 3 + 1
    private lateinit var binding: ActivityMainBinding
    private var digitsPressed = 0
    private var intents = INTENTS
    private lateinit var images: List<LinearLayout>
    private lateinit var imagesDiscovered: ArrayList<Int>
    private var number = 0
    private var userNumber = 0
    private var win = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        thread(start = true) {
            while (true) {
                if (win) {
                    intents = INTENTS
                    win = false
                }
                intents--
                runOnUiThread {
                    binding.tvIntents.text = "$intents seg"
                }
                if (intents == 0) {
                    intents = INTENTS
                    this.number = resetNumber()
                    runOnUiThread {
                        binding.tvSolution.text = "$number"
                    }
                }
                Thread.sleep(1000)
            }
        }

        binding.lldg5.setOnClickListener {
            intents = INTENTS
            this.number = resetNumber()
            binding.tvSolution.text = "$number"
        }

        images = arrayListOf(
            binding.ll01,
            binding.ll02,
            binding.ll03,
            binding.ll04,
            binding.ll05,
            binding.ll06,
            binding.ll07,
            binding.ll08,
            binding.ll09,
            binding.ll10,
            binding.ll11,
            binding.ll12,
            binding.ll13,
            binding.ll14,
            binding.ll15,
            binding.ll16,
            binding.ll17,
            binding.ll18,
            binding.ll19,
            binding.ll20,
            binding.ll21,
            binding.ll22,
            binding.ll23,
            binding.ll24,
            binding.ll25,
            binding.ll26,
            binding.ll27,
            binding.ll28,
            binding.ll29,
            binding.ll30,
            binding.ll31,
            binding.ll32,
            binding.ll33,
            binding.ll34,
            binding.ll35,
            binding.ll36
        )

        imagesDiscovered = prefs.getImagesDiscovered()
        for (i in 0 until imagesDiscovered.size)
            images[imagesDiscovered[i]].visibility = View.VISIBLE

        number = resetNumber()
        binding.tvSolution.text = "$number"

        binding.d0.setOnClickListener {
            fillDisplay("0")
        }
        binding.d1.setOnClickListener {
            fillDisplay("1")
        }
        binding.d2.setOnClickListener {
            fillDisplay("2")
        }
        binding.d3.setOnClickListener {
            fillDisplay("3")
        }
        binding.d4.setOnClickListener {
            fillDisplay("4")
        }
        binding.d5.setOnClickListener {
            fillDisplay("5")
        }
        binding.d6.setOnClickListener {
            fillDisplay("6")
        }
        binding.d7.setOnClickListener {
            fillDisplay("7")
        }
        binding.d8.setOnClickListener {
            fillDisplay("8")
        }
        binding.d9.setOnClickListener {
            fillDisplay("9")
        }
        binding.btnAward.setOnClickListener {
            val intent = Intent(this@MainActivity, GiftActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillDisplay(numberPressed: String) {
        binding.dg1.setTextColor(getColor(R.color.white))
        binding.dg2.setTextColor(getColor(R.color.white))
        binding.dg3.setTextColor(getColor(R.color.white))
        binding.dg4.setTextColor(getColor(R.color.white))
        binding.dg5.setTextColor(getColor(R.color.white))
        if (digitsPressed == 0) {
            binding.dg1.text = "#"
            binding.dg2.text = "#"
            binding.dg3.text = "#"
            binding.dg4.text = "#"
            binding.dg5.text = "#"
        }

        digitsPressed++
        when (digitsPressed) {
            1 -> {
                binding.dg1.text = numberPressed
                userNumber = numberPressed.toInt() * 10000
            }
            2 -> {
                binding.dg2.text = numberPressed
                userNumber += numberPressed.toInt() * 1000
            }
            3 -> {
                binding.dg3.text = numberPressed
                userNumber += numberPressed.toInt() * 100
            }
            4 -> {
                binding.dg4.text = numberPressed
                userNumber += numberPressed.toInt() * 10
            }
            5 -> {
                binding.dg5.text = numberPressed
                userNumber += numberPressed.toInt() * 1
                verifyNumber()
            }
            else -> {

                userNumber = 0
                digitsPressed = 1
                binding.dg1.text = numberPressed
                userNumber += numberPressed.toInt() * 10000
                binding.dg2.text = "#"
                binding.dg3.text = "#"
                binding.dg4.text = "#"
                binding.dg5.text = "#"
                binding.tvCorrects.text = "- COR"
                binding.tvPositioned.text = "- POS"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun verifyNumber() {
        var strNumber = number.toString()
        while (strNumber.length < 5)
            strNumber = "0$strNumber"
        var strUserNumber = userNumber.toString()
        while (strUserNumber.length < 5)
            strUserNumber = "0$strUserNumber"

        var aux = strUserNumber
        var corrects = 0
        for (i in 0..4) {
            while (aux.contains(strNumber[i])) {
                val coincidences =
                    strNumber.length - strNumber.replace(strNumber[i].toString(), "").length
                val userCoincidences = aux.length - aux.replace(strNumber[i].toString(), "").length
                aux = aux.replace(strNumber[i].toString(), "")
                corrects += if (coincidences <= userCoincidences) coincidences else userCoincidences
            }
        }
        binding.tvCorrects.text = "$corrects COR"

        var posisioned = 0
        for (i in 0..4) {
            if (strNumber[i] == strUserNumber[i])
                posisioned++
        }
        binding.tvPositioned.text = "$posisioned POS"

        if (corrects == 5 && posisioned == 5) {
            binding.dg1.setTextColor(getColor(R.color.teal_700))
            binding.dg2.setTextColor(getColor(R.color.teal_700))
            binding.dg3.setTextColor(getColor(R.color.teal_700))
            binding.dg4.setTextColor(getColor(R.color.teal_700))
            binding.dg5.setTextColor(getColor(R.color.teal_700))
            win = true

            number = resetNumber()
            binding.tvSolution.text = "$number"

            if (imagesDiscovered.size < 36)
                award(randomElement())
            else
                binding.btnAward.visibility = View.VISIBLE
        } else {
            binding.dg1.setTextColor(getColor(R.color.error))
            binding.dg2.setTextColor(getColor(R.color.error))
            binding.dg3.setTextColor(getColor(R.color.error))
            binding.dg4.setTextColor(getColor(R.color.error))
            binding.dg5.setTextColor(getColor(R.color.error))
        }
    }

    private fun award(randomElement: Int) {
        images[randomElement].visibility = View.VISIBLE
        imagesDiscovered.add(randomElement)
        prefs.addImageDiscovered(randomElement)
        if (imagesDiscovered.size == 36)
            binding.btnAward.visibility = View.VISIBLE
    }

    private fun resetNumber(): Int {
        val random = Random(Calendar.getInstance().timeInMillis)
        return random.nextInt(0, 99999)
    }

    private fun randomElement(): Int {
        val randomIndex = Random.nextInt(images.size)
        return if (imagesDiscovered.contains(randomIndex)) {
            randomElement()
        } else
            return randomIndex
    }
}