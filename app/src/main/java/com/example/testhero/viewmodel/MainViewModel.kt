package com.example.testhero.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testhero.model.Superhero
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _heros = MutableStateFlow<List<Superhero>?>(null)
    val heros: StateFlow<List<Superhero>?> = _heros
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage
    init {
        fetchHeros()
    }
    private fun fetchHeros(){
        viewModelScope.launch {
            try {
                val fetchedHeros = RetrofitInstance.apiServer.getHeroAll()
                _heros.value = fetchedHeros
                _errorMessage.value = null
            } catch (e: Exception){
                _heros.value = null
                _errorMessage.value = "Error feching hero: ${e.message}"
            }
        }
    }
    private val _currentHero = MutableStateFlow<Superhero?>(null)
    val currentHero: StateFlow<Superhero?> = _currentHero

    fun getHeroById(id: Int) {
        viewModelScope.launch {
            try {
                val hero = RetrofitInstance.apiServer.getProductById(id)
                _currentHero.value = hero
                _errorMessage.value = null
            } catch (e: Exception) {
                _currentHero.value = null
                _errorMessage.value = "Error fetching hero details: ${e.message}"
            }
        }
    }
}