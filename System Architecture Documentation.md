# System Architecture Document

## Introduktion
#### Syfte
	Detta dokument beskriver arkitektur och ändamål med applikationen RecipeCalc
#### Avgränsningar
	Applikationen körs på Android, så avgränsningar som normalt associeras med en Android-app tillämpas av nödvändighet.
	Applikationen är avgränsad till recept och information som kommer ifrån ett enskiljt öppet API.
	
## Arkitektur

### API-anrop
	Edamam.com används som backend för att ta reda på den infromation som applikationen kräver.
### Java design principer
	Koden skall använda och följa de designprinciper som har lärts ut på de kurser i Objektorienterad programmering på Chalmers.
### MVVM
	Arkitekturen för logik, visning och gränssnitt följer arkitekturen osm är vanligast använd i Android just nu. Model-View-ViewModel separerar logik för backend, kod för att bygga upp varje del i gränssnittet och kod för gränssnittets kontroll över modellen.
### Databas
	Applikationen implementerar en SQLite-databas.
