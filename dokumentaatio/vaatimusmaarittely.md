# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on klassinen 9x9 Sudoku. Lähtökohtaisesti sovellus on siis hyvin simppeli systeemi, jossa ei ole käyttäjärooleja, mutta ajan salliessa siihen voidaan lisätä erilaisia bonus ominaisuuksia.

## Käyttöliittymäluonnos

Käyttöliittymä tulee vastaamaan laajalti suoku.com:in mukaista näkymää, jossa näkyy Sudoku ruudukko ja nappeja numeroiden valitsemiseksi. Numeroita voi myös syöttää suoraan näppäimistöltä.

## Perusversion tarjoama toiminnallisuus

Sovellus aukeaa suoraan satunnaisesti generoituun Sudoku-ruudukkoon, josta on mahdollisuus aloitta ratkomaan Sudokua, valita uusi peli tai poistua sovelluksesta.

Sudokun ratkaisun jälkeen ruudulle tulee viesti, jossa ilmoitetaan Sudokun ratkenneen ja kysytään, haluaako käyttäjä pelata uudestaan.

### Tehty 
#### (24.11):
- Sovellus aukeaa kovakoodattuun Sudoku-ruudukkoon
- Sudokun ratkettua käyttäjälle ilmoitetaan siitä viestill
#### (2.12):
- Sovellus generoi nyt satunnaisesti uuden Sudoku-ruudukon ohjelmaa käynnistäessä, pelin jälkeen tai nappia painamalla
- Nappia painamalla voi aloittaa uuden pelin 

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavilla toiminnallisuuksilla

- Keskeneräisen pelin tallettaminen
- Eri vaikeustasoja
- Vinkkinappi, joka paljastaa ruutuja
- Käyttöliittymä näyttää, mitkä ruudut ovat väärin (jos ne ovat väärin)
- Mahdollisuus kirjoittaa numeromuistiinpanoja ruutuihin
- Useita käyttäjätunnuksia samalla koneella, ja mahdollisuus kirjautua ulos/sisään
- Sudokun ratkaisuun käytetyn ajan mittaaminen ja parhaimpien suoritusten mahdollinen tallettaminen listalle
- Suoritusten mahdollinen tallentaminen verkkoon ranking-listalle
