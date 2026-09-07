import com.jetbrains.Director
import com.jetbrains.Film
import com.jetbrains.Genre
import com.jetbrains.newFilmCatalog
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FilmCatalogTest {
    private val films = listOf(
        Film("Zulu", Genre.DRAMA, "Serbian", 7.5, Director("Zora Ilic")),
        Film("Beta", Genre.COMEDY, "English", 9.0, Director("Amir Khan")),
        Film("Echo", Genre.DRAMA, "English", 8.0, Director("Mina Lee")),
        Film("Alpha", Genre.DRAMA, "English", 9.0, Director("Amir Khan")),
        Film("Delta", Genre.DRAMA, "French", 6.5, Director("Zora Ilic")),
        Film("Foxtrot", Genre.DRAMA, "English", 7.0, Director("Amir Khan"))
    )
    private val filmApi = newFilmCatalog(films)

    @Test
    fun `test - top N highest rated films in descending rating order`() {
        assertEquals(
            listOf(films[1], films[3], films[2]),
            filmApi.getTopNHighestRatedFilms(3)
        )
    }

    @Test
    fun `test - top N retains input order when a rating tie crosses the cutoff`() {
        assertEquals(listOf(films[1]), filmApi.getTopNHighestRatedFilms(1))
    }

    @Test
    fun `test - top N with zero requested films`() {
        assertEquals(emptyList(), filmApi.getTopNHighestRatedFilms(0))
    }

    @Test
    fun `test - top N larger than the catalog returns all films sorted`() {
        assertEquals(
            listOf(films[1], films[3], films[2], films[0], films[5], films[4]),
            filmApi.getTopNHighestRatedFilms(100)
        )
    }

    @Test
    fun `test - top N from an empty catalog`() {
        assertEquals(emptyList(), newFilmCatalog(emptyList()).getTopNHighestRatedFilms(5))
    }

    @Test
    fun `test - all directors are unique and retain first appearance order`() {
        assertEquals(
            listOf(Director("Zora Ilic"), Director("Amir Khan"), Director("Mina Lee")),
            filmApi.getAllDirectors()
        )
    }

    @Test
    fun `test - all directors from an empty catalog`() {
        assertEquals(emptyList(), newFilmCatalog(emptyList()).getAllDirectors())
    }

    @Test
    fun `test - language and genre must both match and retain input order`() {
        assertEquals(
            listOf(films[2], films[3], films[5]),
            filmApi.getFilmsByLanguageAndGenre("English", Genre.DRAMA)
        )
    }

    @Test
    fun `test - language and genre with no matching combination`() {
        assertEquals(emptyList(), filmApi.getFilmsByLanguageAndGenre("French", Genre.COMEDY))
        assertEquals(emptyList(), filmApi.getFilmsByLanguageAndGenre("German", Genre.DRAMA))
    }

    @Test
    fun `test - language and genre matching is case sensitive`() {
        assertEquals(emptyList(), filmApi.getFilmsByLanguageAndGenre("english", Genre.DRAMA))
    }

    @Test
    fun `test - language and genre from an empty catalog`() {
        assertEquals(
            emptyList(),
            newFilmCatalog(emptyList()).getFilmsByLanguageAndGenre("English", Genre.DRAMA)
        )
    }

    @Test
    fun `test - most common language returns all its films by descending title`() {
        assertEquals(
            listOf(films[5], films[2], films[1], films[3]),
            filmApi.getFilmsWithMostCommonLanguage()
        )
    }

    @Test
    fun `test - most common language ties follow first appearance`() {
        val frenchFilm = films[0].copy(language = "French")
        val tiedFilms = listOf(films[4], films[1], films[2], frenchFilm)

        assertEquals(
            listOf(frenchFilm, films[4]),
            newFilmCatalog(tiedFilms).getFilmsWithMostCommonLanguage()
        )
        assertEquals(
            listOf(films[2], films[1]),
            newFilmCatalog(tiedFilms.drop(1) + tiedFilms.first()).getFilmsWithMostCommonLanguage()
        )
    }

    @Test
    fun `test - most common language preserves input order for equal titles`() {
        val firstFilm = films[1].copy(title = "Same")
        val secondFilm = films[2].copy(title = "Same")
        val lastFilm = films[3].copy(title = "Zebra")

        assertEquals(
            listOf(lastFilm, firstFilm, secondFilm),
            newFilmCatalog(listOf(firstFilm, secondFilm, lastFilm)).getFilmsWithMostCommonLanguage()
        )
    }

    @Test
    fun `test - most common language from an empty catalog`() {
        assertEquals(emptyList(), newFilmCatalog(emptyList()).getFilmsWithMostCommonLanguage())
    }

    @Test
    fun `test - director with most films counts separately constructed equal directors`() {
        assertEquals(Director("Amir Khan"), filmApi.findDirectorWithMostFilms())
    }

    @Test
    fun `test - director with most films ties follow first appearance`() {
        val tiedFilms = listOf(films[0], films[1], films[3], films[4])

        assertEquals(Director("Zora Ilic"), newFilmCatalog(tiedFilms).findDirectorWithMostFilms())
        assertEquals(
            Director("Amir Khan"),
            newFilmCatalog(tiedFilms.drop(1) + tiedFilms.first()).findDirectorWithMostFilms()
        )
    }

    @Test
    fun `test - director with most films from an empty catalog throws`() {
        assertFailsWith<NoSuchElementException> {
            newFilmCatalog(emptyList()).findDirectorWithMostFilms()
        }
    }

    @Test
    fun `test - queries work with a single film`() {
        val singleFilm = listOf(films[4])
        val singleFilmApi = newFilmCatalog(singleFilm)

        assertEquals(singleFilm, singleFilmApi.getTopNHighestRatedFilms(1))
        assertEquals(listOf(Director("Zora Ilic")), singleFilmApi.getAllDirectors())
        assertEquals(singleFilm, singleFilmApi.getFilmsByLanguageAndGenre("French", Genre.DRAMA))
        assertEquals(singleFilm, singleFilmApi.getFilmsWithMostCommonLanguage())
        assertEquals(Director("Zora Ilic"), singleFilmApi.findDirectorWithMostFilms())
    }

    @Test
    fun `test - queries do not modify the supplied list`() {
        val suppliedFilms = films.toMutableList()
        val suppliedFilmApi = newFilmCatalog(suppliedFilms)

        suppliedFilmApi.getTopNHighestRatedFilms(3)
        suppliedFilmApi.getAllDirectors()
        suppliedFilmApi.getFilmsByLanguageAndGenre("English", Genre.DRAMA)
        suppliedFilmApi.getFilmsWithMostCommonLanguage()
        suppliedFilmApi.findDirectorWithMostFilms()

        assertEquals(films, suppliedFilms)
    }
}
