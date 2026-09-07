package com.jetbrains

import com.jetbrains.common.FileReader

private const val CSV_COLUMN_SEPARATOR: String = ","

fun parseFilms(filmCSVLines: List<String>): List<Film> {
    TODO("Task 2: Parse the CSV lines into films as described in README.md.")
}

fun newFilmCatalog(films: List<Film>): FilmApi {
    TODO("Task 3.1: Instantiate and return a FilmCatalog with the supplied films.")
}

fun main() {
    val filmCSVLines = FileReader.readFileInResources("films.csv")
    val films = parseFilms(filmCSVLines)
    val filmApi = newFilmCatalog(films)

    println(filmApi.getTopNHighestRatedFilms(5))
}
