# MIDI Ear Trainer

Simple app that aims to improve user's ear for music. Under development.

## Features
- game where two random notes are played separately, and the user provides his/her guess for the interval between the notes
- profile window that shows analyzed data from played games in a chosen time period
- graph window that displays graphs based on analyzed game data (in the making)
- special game that focuses on the intervals the user has most trouble with (to-do)
- user account creation / login at app startup (to-do)

## Tools used

- JavaFX 19
- Maven 3
- Local MariaDB database

## Deployment

1. Import the project into your IDE.
2. Run the SQL script in '/src/main/resources/' to create a local database. 
3. Create a `config.properties` file in '/src/main/resources/' and configure the database connection parameters there.
4. Run Main class from '/src/main/java/launcher'.
