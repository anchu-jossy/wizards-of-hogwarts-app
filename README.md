# Wizards of Hogwarts App

## Overview

The Wizards of Hogwarts app displays characters from the Harry Potter universe using the HP API. Key features include inline search, offline support, and detailed character views.

## Features

- **Character List**: Shows:
  - Character’s name
  - Actor’s name
  - Species
  - House color indicator:
    - Gryffindor: `#740001`
    - Slytherin: `#1a472a`
    - Ravenclaw: `#0c1a40`
    - Hufflepuff: `#eeb939`

- **Search**: Inline search functionality to filter characters by name or actor.

- **Detail View**: Displays:
  - Character’s picture
  - Formatted date of birth (`dd MMM yyyy`)
  - Alive or deceased status

- **Offline Mode**: Utilizes Room database for caching data.

## Bonus Features

- **Light/Dark Mode**: Switch between themes with preferences saved using data preferences.

- **Accessibility**: UI adapts to text size changes for improved readability.

## Technologies

- **MVVM+MVI Clean Architecture**: Ensures separation of concerns and maintainability.
- **Jetpack Compose**: Provides a modern, declarative UI toolkit.
- **Room Database**: Manages offline data storage.
- **Data Preferences**: Saves user-selected themes.
- **Coil**: Handles efficient image loading and caching.
- **Flow**: Manages reactive and asynchronous data streams.
- **Dagger Hilt**: Facilitates dependency injection and management.
- **Version Catalog**: Centralizes dependency management.
- **API Key Management**: Secures API keys in the `BaseConfig` file for both `develop` and `release` variants.
- **Coroutines**: Handles asynchronous operations and concurrency.
- **Flow Library**: Supports proper data operations and state management.


## Setup

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/anchu-jossy/wizards-of-hogwarts-app.git
    ```

2. **Navigate to the Project Directory:**

    ```bash
    cd wizards-of-hogwarts-app
    ```

3. **Open in Android Studio:**
   - Sync Gradle files.
   - Build and run the app.

4. **Branch Information:**
   - For the latest updates, refer to the `develop` branch.

## Contact

For any questions or further information, please feel free to reach out.
