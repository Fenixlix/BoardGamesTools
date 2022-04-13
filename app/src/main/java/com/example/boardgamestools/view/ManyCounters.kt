package com.example.boardgamestools.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.boardgamestools.databinding.ActivityManyCountersBinding

import kotlinx.coroutines.flow.flow

class ManyCounters : AppCompatActivity() {
    lateinit var binding : ActivityManyCountersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManyCountersBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    // todo: Hacer una rama en git para añadir los cambios
    // todo: Realizar el diseño de la pantalla de los contadores
    // todo: Realizar la coneccion viewModel para los mismos
    // todo: Considero que no va a ser necesario tener un modelo de datos pero hay que pensarlo
    // todo: probar la app y hacer mejoras.
}