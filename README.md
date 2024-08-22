Wizards of Hogwarts App
Overview
The Wizards of Hogwarts app displays Harry Potter characters from HP API with features including inline search, offline support, and a detailed character view.

Features
Character List: Shows each character’s name, actor, species, and a house color indicator:

Gryffindor: #740001
Slytherin: #1a472a
Ravenclaw: #0c1a40
Hufflepuff: #eeb939
Search: Filter characters by name or actor.

Detail View: Includes character’s picture, formatted date of birth, and alive/deceased status.

Offline Mode: Cached data using Room database.

Bonus
Light/Dark Mode: Switch themes with user preference saved via data preferences.

Accessibility: UI adapts to text size changes.

Technologies
MVVM Clean Architecture: For separation of concerns.
Jetpack Compose: For modern, declarative UI.
Room Database: For offline data storage.
Data Preferences: For theme persistence.
Coil: For efficient image loading and caching.
Flow: For reactive and asynchronous data handling.
Dagger Hilt: For dependency injection and managing dependencies.
Version Catalog: Implemented for managing dependencies.
API Key Management: API keys are securely maintained in BaseConfig file for both develop and release variants.
Setup
Clone the Repository:

bash
Copy code
git clone https://github.com/anchu-jossy/wizards-of-hogwarts-app.git
Navigate and Run:

bash
Copy code
cd wizards-of-hogwarts-app
Open in Android Studio, sync Gradle files, and run the app.

Branch Information:

Please refer to the develop branch for the latest updates and code
