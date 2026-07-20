package com.dev.imc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etPoids: EditText
    private lateinit var etTaille: EditText
    private lateinit var btnCalculer: Button
    private lateinit var tvResultat: TextView
    private lateinit var imgResultat: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // 1. Lier les vues (findViewById)
        etPoids = findViewById(R.id.poid)
        etTaille = findViewById(R.id.taille)
        btnCalculer = findViewById(R.id.btnCalculer)
        tvResultat = findViewById(R.id.tvResultat)
        imgResultat = findViewById(R.id.imgResultat)


        // 2. Écouter le clic du bouton
        btnCalculer.setOnClickListener {
            calculerIMC()
        }
    }
    private fun calculerIMC() {
        val poidsStr = etPoids.text.toString()
        val tailleStr = etTaille.text.toString()

        if (poidsStr.isEmpty() || tailleStr.isEmpty()) {
            tvResultat.text = "Remplis tous les champs !"
            return
        }

        val poids = poidsStr.toDouble()
        val tailleCm = tailleStr.toDouble()
        val tailleM = tailleCm / 100
        val imc = imc(poids, tailleM)

        when {
            imc < 18.5 -> {
                imgResultat.setImageResource(R.drawable.maigre)
                tvResultat.text = "Votre IMC est: ${"%.2f".format(imc)} (Maigreur)"
            }
            imc < 25 -> {
                imgResultat.setImageResource(R.drawable.normal)
                tvResultat.text = "Votre IMC est: ${"%.2f".format(imc)} (Poids normal)"
            }
            imc < 30 -> {
                imgResultat.setImageResource(R.drawable.surpoids)
                tvResultat.text = "Votre IMC est: ${"%.2f".format(imc)} (Surpoids)"
            }
            imc < 40 -> {
                imgResultat.setImageResource(R.drawable.obese)
                tvResultat.text = "Votre IMC est: ${"%.2f".format(imc)} (Obésité)"
            }
            else -> {
                imgResultat.setImageResource(R.drawable.t_obese)
                tvResultat.text = "Votre IMC est: ${"%.2f".format(imc)} (Obésité sévère)"
            }
        }
    }

    private fun imc(poids: Double, tailleM: Double): Double {
        return poids / (tailleM * tailleM)
    }
}