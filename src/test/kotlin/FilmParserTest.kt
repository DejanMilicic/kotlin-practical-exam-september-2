import com.jetbrains.Director
import com.jetbrains.Film
import com.jetbrains.Genre
import com.jetbrains.parseFilms
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class FilmParserTest {
    private val header = "ID,TITLE,GENRE,LANGUAGE,RELEASE_YEAR,DURATION_MINUTES,RATING,COUNTRY,DIRECTOR"

    @Test
    fun `test - parse selected columns and all genre labels in input order`() {
        val lines = listOf(
            header,
            "F001,The Quiet Platform,Drama,Serbian,2021,102,7.4,Serbia,Milica Petrovic",
            "F002,An Unfinished Waltz,Comedy,French,2022,96,8,France,Chloé Martin",
            "F003,The River Postman,Documentary,Serbian,2020,78,6.5,Serbia,Milica Petrovic",
            "F004,Paper Moons,Animation,English,2023,89,9.8,United Kingdom,Nadia Rahman",
            "F005,Lanterns at Dawn,Thriller,Japanese,2024,110,9.6,Japan,Ren Sato",
            "F006,The Last Projection,Science Fiction,English,2025,112,9.7,Canada,Nadia Rahman"
        )
        val expectedFilms = listOf(
            Film("The Quiet Platform", Genre.DRAMA, "Serbian", 7.4, Director("Milica Petrovic")),
            Film("An Unfinished Waltz", Genre.COMEDY, "French", 8.0, Director("Chloé Martin")),
            Film("The River Postman", Genre.DOCUMENTARY, "Serbian", 6.5, Director("Milica Petrovic")),
            Film("Paper Moons", Genre.ANIMATION, "English", 9.8, Director("Nadia Rahman")),
            Film("Lanterns at Dawn", Genre.THRILLER, "Japanese", 9.6, Director("Ren Sato")),
            Film("The Last Projection", Genre.SCIENCE_FICTION, "English", 9.7, Director("Nadia Rahman"))
        )

        assertEquals(expectedFilms, parseFilms(lines))
    }

    @Test
    fun `test - directors have data class value semantics`() {
        val director = Director("Milica Petrovic")

        assertEquals(director, Director("Milica Petrovic"))
        assertEquals(director, director.copy())
        assertNotEquals(director, director.copy(name = "Nadia Rahman"))
    }

    @Test
    fun `test - films have data class value semantics`() {
        val film = Film("Paper Moons", Genre.ANIMATION, "English", 9.8, Director("Nadia Rahman"))

        assertEquals(film, Film("Paper Moons", Genre.ANIMATION, "English", 9.8, Director("Nadia Rahman")))
        assertEquals(film, film.copy())
        assertNotEquals(film, film.copy(rating = 8.0))
    }

    @Test
    fun `test - parse header only input`() {
        assertEquals(emptyList(), parseFilms(listOf(header)))
    }

    @Test
    fun `test - parse empty input`() {
        assertEquals(emptyList(), parseFilms(emptyList()))
    }
}
