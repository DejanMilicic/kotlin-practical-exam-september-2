import com.jetbrains.Director
import com.jetbrains.Film
import com.jetbrains.Genre
import com.jetbrains.common.FileReader
import com.jetbrains.newFilmCatalog
import com.jetbrains.parseFilms
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FilmFestivalTest {
    private val films = parseFilms(FileReader.readFileInResources("films.csv"))
    private val filmApi = newFilmCatalog(films)

    @Test
    fun `test - complete festival dataset has the expected records`() {
        assertEquals(175, films.size)
        assertEquals(
            Film("The Quiet Platform", Genre.DRAMA, "Serbian", 7.4, Director("Milica Petrovic")),
            films.first()
        )
        assertEquals(
            Film("After the Final Applause", Genre.DRAMA, "English", 6.4, Director("Oliver Reed")),
            films.last()
        )
    }

    @Test
    fun `test - top five highest rated festival films`() {
        assertEquals(
            listOf(
                "Paper Moons",
                "The Last Projection",
                "Lanterns at Dawn",
                "Echoes of Tomorrow",
                "A Map of Silence"
            ),
            filmApi.getTopNHighestRatedFilms(5).map { it.title }
        )
    }

    @Test
    fun `test - all festival directors without duplicates`() {
        assertEquals(
            listOf(
                "Milica Petrovic",
                "Sofia Ricci",
                "Gabriel Santos",
                "Mateo Silva",
                "Adrian Cole",
                "Nadia Rahman",
                "Victor Chen",
                "Louis Bernard",
                "Ren Sato",
                "Pavel Novak",
                "Diego Torres",
                "Jonas Weber",
                "Elena Kovac",
                "Sara Lind",
                "Minseo Park",
                "Amina Diallo",
                "Hana Mori",
                "Claire Moreau",
                "Ines Costa",
                "Anja Fischer",
                "Lucia Alvarez",
                "Tomas Varga",
                "Leila Haddad",
                "Chloé Martin",
                "Oliver Reed"
            ),
            filmApi.getAllDirectors().map { it.name }
        )
    }

    @Test
    fun `test - English drama films in the festival`() {
        assertEquals(
            listOf(
                "The Red Bicycle",
                "Lost in the Greenhouse",
                "A Street Called Home",
                "A Door in the Mountain",
                "Echoes of Tomorrow",
                "A Garden for Tomorrow",
                "The Small Hours",
                "The Road beyond the Mill",
                "A Sky Full of Windows",
                "The Last Visitor",
                "After the Final Applause"
            ),
            filmApi.getFilmsByLanguageAndGenre("English", Genre.DRAMA).map { it.title }
        )
    }

    @Test
    fun `test - festival films with the most common language`() {
        assertEquals(
            listOf(
                "Voices beyond the Wall",
                "The Wind Collector",
                "The Unlikely Magician",
                "The Train to the Old Cinema",
                "The Sound of an Empty Room",
                "The Small Hours",
                "The Sleepwalking City",
                "The Sleepless River",
                "The Silent Telescope",
                "The Silent Satellite",
                "The Secret of the Attic",
                "The Road beyond the Mill",
                "The Return of the Fireflies",
                "The Red Bicycle",
                "The Quiet Side of Mars",
                "The Portrait Collector",
                "The Passenger in Blue",
                "The Paper Astronaut",
                "The Orchestra of Small Things",
                "The Night of Paper Boats",
                "The Night of Falling Leaves",
                "The Night Train Orchestra",
                "The Missing Frame",
                "The Library after Dark",
                "The Last Warm Morning",
                "The Last Visitor",
                "The Last Summer Cinema",
                "The Last Ferry Home",
                "The Last Apple Tree",
                "The Lantern Keeper",
                "The House on the Border",
                "The Hidden Stairway",
                "The Glass Violin",
                "The Fourth Key",
                "The Day the Trams Stopped",
                "The Borrowed Voice",
                "The Blue Suitcase",
                "The Bicycle Repair Club",
                "The Astronaut's Kitchen",
                "Portrait of a Stranger",
                "Paper Moons",
                "Lost in the Greenhouse",
                "Letters from the Harbor",
                "Footsteps in the Snow",
                "Echoes of Tomorrow",
                "Dancing with Shadows",
                "An Ocean of Rooftops",
                "An Island of Windows",
                "An Afternoon in Orbit",
                "After the Final Applause",
                "A Village of Kites",
                "A Ticket to the Moon",
                "A Table for Three",
                "A Street Called Home",
                "A Story in the Sand",
                "A Song for the City",
                "A Sky Full of Windows",
                "A Pocket of Summer",
                "A Light above the Orchard",
                "A Handful of Stars",
                "A Garden for Tomorrow",
                "A Door in the Mountain",
                "A Cup of Yesterday",
                "A City Made of Paper",
                "A Bench for Two"
            ),
            filmApi.getFilmsWithMostCommonLanguage().map { it.title }
        )
    }

    @Test
    fun `test - festival director with the most films`() {
        assertEquals(Director("Nadia Rahman"), filmApi.findDirectorWithMostFilms())
    }
}
