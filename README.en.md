# Watch Next

Sample project in development using [Jetpack Compose](https://developer.android.com/jetpack/compose). With the aim of showing a modular proyect with the current UI creation capabilities, and its use in conjunction with the rest of the libraries for navigation, dependency injection, consumption of REST services and local data persistence. Following the current practices recommended by the [Architecture Guide](https://developer.android.com/topic/architecture).

Get to know the latest in movies and series, trends and others.
Check the endless list for movies and series.
Add content to your list so you don't miss a thing.

## Features

The application consists of five screens:
1. Main view divided by sections of recent content for movies and series, as well as the most recent ones added to your list.
2. Detail with the information and complete summary, which has a section with more content.
3. List that shows movies or series, which loads more content as you scroll to the bottom.
4. List of saved movies and series.
5. Search for content by title.

Each screen shows a preview with the information of the selected content, which allows you to easily add it to your list, as well as navigate to the detail screen.

#### Main dependencies
* **Compose** - UI
* **Material 3** - Design System
* **Hilt** - Dependency Injection
* **Room** - Local data persistence (SQLite)
* **Retrofit** - HTTP client
* **Coil** - Image loading

## Requirements

The project uses [The Movie DB API](https://www.themoviedb.org/documentation/api). You can sign up for an API key.

You need to create a file **secret.properties** in the main directory and add the key:

`API_KEY=<YourAPIKey>`

## Screenshots

<img src="https://drive.google.com/uc?id=1tPILbYTIlfES2sFQPtFZPjGYmf052Bcj" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1cpo3Lur3Xu-hJ0QtMUIgqVeAAG0PqmJJ" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1ifKij8XCbHU4GrS9jSXdOI46lQyxeT-D" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1G1QKY2j32-AmapV8zPoBhtQjxXLu_QOv" alt="Screenshot">
<img src="https://drive.google.com/uc?id=13d-F7zRVlSvXgcegyec4-BQPas-jKul4" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1fZEf4tv_E5Ofp2RJN3Z8wt9Mc8WF7vj9" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1T8mIt09-tI4j1d-FGS7V8vuvYc6oYRSd" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1lgvFXIQ0h54pMPq5MqgIr1fDf6xq2aD7" alt="Screenshot">
<img src="https://drive.google.com/uc?id=19oFc4hvE0LIwjqH3vtQq9bdjWe6DYOZc" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1iA1poL6UddeDuRnYrsIGTKc0rMR7n-Oo" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1i48zFmTg_7CEdkvmdgrOALUynj5gyA9A" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1LV9PFuuUsyRnoAW047YzH3LSWr_gO49a" alt="Screenshot">