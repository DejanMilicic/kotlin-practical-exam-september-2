## Film Festival Catalog
Build a catalog of films submitted to a fictional film festival. Model film data, parse a CSV file, and implement a small query API over that data.

**Duration: 2 hours. Total: 60 points.**

### Setup
- Use the Gradle wrapper that is committed to this repository.
- The project is pinned to Gradle `9.5.1` and Kotlin `2.4.0`.
- The build targets Java `21`.
- If students work inside IntelliJ IDEA, the bundled JetBrains Runtime is enough to import the project and run Gradle tasks. No separate Gradle install is required.
- For terminal use on macOS and Linux, run `sh ./run-gradle.cmd ...`.
- For terminal use on Windows, run `.\run-gradle.cmd ...`.
- The single `run-gradle.cmd` bootstrap reuses JDK `21` from `JAVA_HOME`, `PATH`, `~/.jdks`, `%USERPROFILE%\\.jdks`, or common system locations, and downloads the correct Amazon Corretto `21` archive for the current OS if needed.
- To create a ZIP for submission, run `sh ./zip-project.cmd` on macOS/Linux or `.\zip-project.cmd` on Windows. The script asks for first name, last name, and index number in the usual form such as `123/2026`, then creates a ZIP named `First_Last_123-2026.zip` with tracked plus non-ignored project files only.
- Verify the environment with one of:

```shell
sh ./run-gradle.cmd compileKotlin
```

```powershell
.\run-gradle.cmd compileKotlin
```

- The starter project intentionally contains placeholders and incomplete types, so `run-gradle ... test` is expected to fail until the assignment is implemented.

### Contents
- `FilmApi.kt`: the supplied query interface.
- `FilmRunner.kt`: the parser, catalog factory, and entry point.
- `models.kt`
- `common/FileReader.kt`: supplied resource reader; do not modify it.
- `films.csv`: the festival dataset.
- `FilmParserTest.kt`, `FilmCatalogTest.kt`, and `FilmFestivalTest.kt`: prepared checks.

The following types are part of the assignment:
- `Director`: represents a director, identified by name.
- `Film`: represents a film with exactly one director and one genre.
- `Genre`: enum class for film genres.
- `FilmApi`: interface defining the required queries; keep its signatures unchanged.

All Kotlin source files you create should use the `com.jetbrains` package. Keep the provided tests unchanged.

### Tasks
#### Task 1: Create Entity Classes
###### Total points: 10
In `models.kt`, create the following entities:
- `Director`: data class with the primary constructor property `name: String`.
- `Film`: data class with the primary constructor properties, in this order:
  - `title: String`
  - `genre: Genre`
  - `language: String`
  - `rating: Double`
  - `director: Director`
- `Genre`: enum class containing `DRAMA`, `COMEDY`, `DOCUMENTARY`, `ANIMATION`, `THRILLER`, and `SCIENCE_FICTION`.

#### Task 2: Parse the input CSV file `films.csv`
###### Total points: 10
Implement the top-level `parseFilms(filmCSVLines: List<String>): List<Film>` function in `FilmRunner.kt`. The supplied reader passes the lines of [films.csv](src/main/resources/films.csv), including the header, to this function.

The CSV contains 175 fictional films and has these columns in this order:

```text
ID,TITLE,GENRE,LANGUAGE,RELEASE_YEAR,DURATION_MINUTES,RATING,COUNTRY,DIRECTOR
F001,The Quiet Platform,Drama,Serbian,2021,102,7.4,Serbia,Milica Petrovic
```

- Skip the first line, which contains column names.
- Columns are separated by a comma (`,`). Input is well-formed: no blank rows, missing values, surrounding whitespace, quoted fields, or embedded commas.
- Use `TITLE`, `GENRE`, `LANGUAGE`, `RATING`, and `DIRECTOR`; ignore the remaining columns.
- Convert genre labels such as `Drama` to `DRAMA` and `Science Fiction` to `SCIENCE_FICTION`.
- Convert the rating to `Double` and construct a `Director` from the director name.
- Preserve row order and text values, including accented characters. Do not remove films that share a director.
- Empty input or a header-only input returns an empty list. No malformed-input handling is required.

Verify Tasks 1 and 2 with:

```shell
sh ./run-gradle.cmd test --tests "FilmParserTest"
```

The test sources require the completed models from Task 1 before they can compile.

#### Task 3: Create `FilmCatalog` and implement the `FilmApi` interface
###### Total points: 40

##### Note
Prefer Kotlin collection APIs such as `map`, `filter`, `distinct`, `sortedByDescending`, `take`, `groupingBy`, and `maxByOrNull`.
Imperative solutions using loops are acceptable, but each such task receives a `2` point deduction.

All queries must use the list passed to the constructor, including when it differs from the supplied CSV. Do not modify this list. String comparisons are case-sensitive. Sorting strings uses Kotlin's natural string order.

The commands below use macOS/Linux syntax. On Windows, replace `sh ./run-gradle.cmd` with `.\run-gradle.cmd`. You can also run the tests using IntelliJ IDEA's gutter controls.

#### Task 3.1
###### Points: 4
Create a `FilmCatalog` class in `FilmCatalog.kt`, in the same package as `FilmRunner.kt` and `models.kt`.
`FilmCatalog` should have a primary constructor with a `films: List<Film>` property and should implement `FilmApi`.

Implement `newFilmCatalog(films: List<Film>): FilmApi` in `FilmRunner.kt` so it returns an instance of `FilmCatalog` initialized with the supplied list.

#### Task 3.2: Implement `FilmCatalog.getTopNHighestRatedFilms`
###### Points: 4
Return up to `n` films, ordered by rating from highest to lowest. Films with equal ratings retain their order from the input list.

You may assume `n >= 0`. For `n == 0`, return an empty list. If `n` exceeds the number of films, return all films in the required order. An empty catalog returns an empty list.

Verify Task 3.2 with:
```shell
sh ./run-gradle.cmd test --tests "FilmCatalogTest.test - top N*"
```

#### Task 3.3: Implement `FilmCatalog.getAllDirectors`
###### Points: 8
Return all directors without duplicates, in the order in which their names first appear in the input list. Directors with the same name are equal even if represented by separately constructed objects. An empty catalog returns an empty list.

Verify Task 3.3 with:
```shell
sh ./run-gradle.cmd test --tests "FilmCatalogTest.test - all directors*"
```

#### Task 3.4: Implement `FilmCatalog.getFilmsByLanguageAndGenre`
###### Points: 8
Return all films matching both the provided language and genre, preserving input order. Return an empty list if no films match.

Verify Task 3.4 with:
```shell
sh ./run-gradle.cmd test --tests "FilmCatalogTest.test - language and genre*"
```

#### Task 3.5: Implement `FilmCatalog.getFilmsWithMostCommonLanguage`
###### Points: 8
Find the language used by the greatest number of films. Return all films in that language, sorted by title in descending order.

If multiple languages have the same greatest count, choose the language that first appears in the input list. Films with equal titles retain their input order. An empty catalog returns an empty list.

Verify Task 3.5 with:
```shell
sh ./run-gradle.cmd test --tests "FilmCatalogTest.test - most common language*"
```

#### Task 3.6: Implement `FilmCatalog.findDirectorWithMostFilms`
###### Points: 8
Return the `Director` credited with the greatest number of films. Count films by director name, not by object identity.

If multiple directors have the same greatest count, choose the director whose name first appears in the input list. For an empty catalog, throw `NoSuchElementException`; the return type remains non-nullable.

Verify Task 3.6 with:
```shell
sh ./run-gradle.cmd test --tests "FilmCatalogTest.test - director with most films*"
```

### Final Verification
Run the five queries against the complete festival CSV with:

```shell
sh ./run-gradle.cmd test --tests "FilmFestivalTest"
```

Run all prepared checks before submitting:

```shell
sh ./run-gradle.cmd test
```

The smaller tests check the models, parsing, individual queries, ordering, ties, and empty inputs. The festival tests check the supplied dataset. Passing tests does not replace the requirements to use data classes, implement `FilmApi` in `FilmCatalog`, and prefer Kotlin collection operations.
