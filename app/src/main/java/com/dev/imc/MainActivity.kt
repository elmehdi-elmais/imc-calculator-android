package com.dev.imc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

        // 2. Écouter le clic du bouton
        btnCalculer.setOnClickListener {
            calculerIMC()
        }
    }
    private fun calculerIMC() {
        // 3. Récupérer le texte et le convertir en nombre
        val poidsStr = etPoids.text.toString()
        val tailleStr = etTaille.text.toString()

        if (poidsStr.isEmpty() || tailleStr.isEmpty()) {
            tvResultat.text = "Remplis tous les champs !"
            return
        }

        val poids = poidsStr.toDouble()
        val taille = tailleStr.toDouble()

        // 4. Calcul de l'IMC = poids / (taille * taille)
        val imc = poids / (taille * taille)

        // 5. Afficher le résultat (formaté à 2 décimales)
        tvResultat.text = "Votre IMC est : ${"%.2f".format(imc)}"
    }
}