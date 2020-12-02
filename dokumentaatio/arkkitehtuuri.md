# Arkkitehtuurikuvaus

Ohjelman tämänhetkinen arkkitehtuuri:

<img src="https://raw.githubusercontent.com/rajanssi/ot-harjoitustyo/master/dokumentaatio/kuvat/a-1.png" width="160">

Ohjelma käynnistyy pakkauksesta _sudoku_. Paukkaus _sudoku.userinterface_ sisältää JavaFX:llä toteutetun käyttöliittymän ja _sudoku.domain_ sovelluslogiikan.

## Käyttöliittymä

Tässä vaiheessa käyttäjä voi vain syöttää numeroita sudokuruutuihin. Ratkaistuaan sudokun, ruudulle tulee viesti, jossa ilmoitetaan pelin päättymisestä. 
Käyttäjä voi painaa OK nappia, jonka jälkeen alkaa uusi peli.

Käyttöliittymä luodaan _UserInterface_ luokassa. _UserInterface_ luokassa luodaan myös _SudokuCell_ luokan oliot, johon asetetaan kuuntelija käyttäjän syötteitä varten.
Kun käyttäjä on onnistunut ratkaisemaan kaikki ruudut, kuuntelija tuo esille viestilaatikon, jonka hyväksymisen jälkeen käyttöliittymä luo uuden näkymän ja sijoittaa
sen sovelluksen _stageen_.

## Sovelluslogiikka

Toiminnallisista kokonaisuuksista vastaa luokan _GameLogic_ olio. Luokka tarjoaa käyttölittymän toiminnoille mm. seuraavat metodit:
-initGame()
-checkComplete()
-checkCell()
-checkMask()

_GameLogic_ tarkastaa _Puzzle_ luokassa luodun sudokun ruudut käyttöliittymän niitä pyytäessä. _UserInterface_ luokassa luodut _SudokuCell_ oliot kysyvät kuuntelijalla tietoja.

Ohjelman osien suhdetta kuvaava alustava luokkakaavio:

<img src="https://raw.githubusercontent.com/rajanssi/ot-harjoitustyo/master/dokumentaatio/kuvat/a-2.png" width="400">

### Päätoiminnallisuudet

#### Uuden pelin luominen

Kun perusnäkymässä klikataan newGameButton nappia etenee sovelluksen kontrolli seuraavasti:

<img src="https://raw.githubusercontent.com/rajanssi/ot-harjoitustyo/master/dokumentaatio/kuvat/a-3.png" width="700">

Painikkeen painamiseen reagoiva tapahtumankäsittelijä kutsuu sovelluslogiikan GameLogic metodia initGame. Metodissa initGame() luodaan uusi sudoku peli asettamalla kutsumalla Puzzle luokan staattista metodia setPuzzle() ja sitten gameInit() asettaa piilotetut sudokuruudut. Tämän jälkeen asetetaan stagelle uusi Scene olio UserInterface luokan getScene() metodin avulla.
