package com.jetbrains

interface FilmApi {
    fun getTopNHighestRatedFilms(n: Int): List<Film>

    fun getAllDirectors(): List<Director>

    fun getFilmsByLanguageAndGenre(language: String, genre: Genre): List<Film>

    fun getFilmsWithMostCommonLanguage(): List<Film>

    fun findDirectorWithMostFilms(): Director
}
