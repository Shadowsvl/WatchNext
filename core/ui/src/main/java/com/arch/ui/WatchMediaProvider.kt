package com.arch.ui

import com.arch.model.data.MainSection
import com.arch.model.data.MediaType
import com.arch.model.data.WatchMedia

val movieA = WatchMedia(
    id = 634649L,
    originalTitle = "Spider-Man: No Way Home",
    title = "Spider-Man: No Way Home",
    overview = "Peter Parker es desenmascarado y por tanto no es capaz de separar su vida normal de los enormes riesgos que conlleva ser un súper héroe. Cuando pide ayuda a Doctor Strange, los riesgos pasan a ser aún más peligrosos, obligándole a descubrir lo que realmente significa ser Spider-Man.",
    releaseDate = "15/12/2021",
    score = "8.0",
    scoreValue = 0.8f,
    posterUrl = "",
    backdropUrl = "",
    mediaType = MediaType.Movie
)

val movieB = WatchMedia(
    id = 507086L,
    originalTitle = "Jurassic World Dominion",
    title = "Jurassic World: Dominion",
    overview = "Cuatro años después de la destrucción de Isla Nublar, los dinosaurios conviven – y cazan – con los seres humanos en todo el mundo. Este frágil equilibrio cambiará el futuro y decidirá, de una vez por todas, si los seres humanos seguirán en la cúspide de los depredadores en un planeta que comparten con los animales más temibles de la creación.",
    releaseDate = "01/06/2022",
    score = "7.1",
    scoreValue = 0.71f,
    posterUrl = "",
    backdropUrl = "",
    mediaType = MediaType.Movie
)

val tvA = WatchMedia(
    id = 84773L,
    originalTitle = "The Lord of the Rings: The Rings of Power",
    title = "El Señor de los Anillos: Los anillos de poder",
    overview = "Serie de televisión basada en El señor de los anillos, ambientada durante el período de 3.441 años, conocida como la Era de Númenor, o la Segunda Edad.",
    releaseDate = "01/09/2022",
    score = "7.1",
    scoreValue = 0.71f,
    posterUrl = "",
    backdropUrl = "",
    mediaType = MediaType.Tv
)

val tvB = WatchMedia(
    id = 766507L,
    originalTitle = "House of the Dragon",
    title = "La casa del dragón",
    overview = "Basada en el libro 'Fuego y Sangre' de George R.R. Martin. La serie se centra en la casa Targaryen, trescientos años antes de los eventos vistos en 'Juego de Tronos'.",
    releaseDate = "21/08/2022",
    score = "8.7",
    scoreValue = 0.8f,
    posterUrl = "",
    backdropUrl = "",
    mediaType = MediaType.Tv
)

val watchMediaList = listOf(
    movieA,
    movieB,
    tvA,
    tvB,
    movieA.copy( id = 1L ),
    movieB.copy( id = 2L ),
    tvA.copy( id = 3L ),
    tvB.copy( id = 4L )
)

val mainSection = MainSection(
    titleId = R.string.section_cinema_movies,
    watchMediaList = watchMediaList
)