# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee pääpiirteiltään kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:

<img src = https://raw.githubusercontent.com/rajanssi/ot-harjoitustyo/master/dokumentaatio/kuvat/a-5.png width="400">

Pakkaus todoapp.ui sisältää JavaFX:llä toteutetun käyttöliittymän todoapp.domain sovelluslogiikan ja todoapp.dao tietojen pysyväistallennuksesta vastaavan koodin. Pakkause messages sisältää käyttöliittymän viesteissä käytetyt tekstijonot, jotka voidaan asettaa suomeksi tai englanniksi.

## Käyttöliittymä

Käyttöliittymä sisältää neljä erilaista näkymää

* Valikkonäkymä
* Pelinäkymä
* Asetukset näkymä
* Tilastonäkymä

Jokainen näistä on toteutettu itse tehdyn IScene rajapinnan toteuttavan luokkana. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen stageen, joka annetaan sovelluksen käynnistyksen yhteydessä kaikille näkymille ohjelman pääluokassa. 

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta. Metodeja kutsutaan pääosin sovelluslogiikkaa hallitsevan luokan *service* olion kautta. SudokuService luokassa luodun sovelluslogiikan totettava olio antaa myös jonkin verran viitteitä käyttöliittymälle itsensä sisällä luoduille olioille. 

Toiminnallisuus on pyritty pitämään suhteellisen yksinkertaisena ja helppokäyttöisenä. Sovellus käynnistyy päävalikkonäkymään, josta voi siirtyä nappeja painamalla toisiin näkymiin. Tilastonäkymä näyttää tilastoja voitetuista peleistä. Asetukset näkymästä käyttäjä voi muuttaa käyttöliittymän kieltä, vaihtaa pelin vaikeustasoa ja päättää näytetäänkö virheellisesti asetettuja Sudoku ruutuja.

Pelinäkymään pääsee päävalikon kahdesta ylimmästä napista. Käyttäjä voi aloittaa uuden pelin tai jatkaa aiemmin kesken jäänyttä peliä. Jos peliä ei ole tallennettu, niin vanhan pelin jatkamiseen tarkoitettu nappula on pois päältä, jolloin käyttäjä voi aloittaa vain uuden pelin. 

Pelinäkymässä käyttäjä syöttää numeroita Sudokuruudukkoon näppäimistöltä. Sudokun alussa näytettyjen ruutujen määrä riippuu siitä, minkä vaikeustason käyttäjä on valinnut asetuksista. Jos virheiden näyttäminen on päällä, niin väärin asetettu numero muuttuu punaiseksi heti, kun se on syötetty Sudoku ruutuun. Oikein asetettu numero näkyy vihreänä. Ylälaidassa näkyy peliin käytetty aika. Näkymästä voi poistua takaisin päävalikkoon, tai sulkea ohjelman ruksista, jolloin peli tallentuu automaattisesti ja sitä voi myöhemmin jatkaa siitä mihin käyttäjä jäi. Pelinäkymästä voi myös aloitaa uuden pelin. Kun käyttäjä on ratkaisuut Sudokun, hänelle esitetään viesti, jossa hän voi aloittaa uuden pelin, siirtyä takaisin päävalikkoon tai poistua ohjelmasta. Pelin voitettuaan peli tallennetaan tietokantaan ja tilastot näkymä päivitetään. 

## Sovelluslogiikka

Sovelluksen kommunikointi käyttöliittymän ja pysyväistallenuksen kanssa toteutetaan SudokuService luokan ainoassa oliossa, joka toimii julkisivuna ohjelman muulle sovelluslogiikalle. Ohjelman käynnistyessä sen sisällä luodaan yksi instanssi kaikista muista sovelluslogiikan toteuttavista luokista. SudokuService luokan omia käyttöliittymälle tarjomia toimintoja ovat mm.

* void newGame()
* void applySettings(Language language, Difficulty difficulty, boolean showMistakes)
* boolean saveGame(int time)

SudokuService pääsee käsiksi pelien, asetusten ja tilastojen tietojen tallennuksesta vastaavan pakkauksessa sudoku.dao sijaitsevien luokkien FileGameDao, FileSettingsDao ja DbStatisticsDao kautta. Luokkien toteutuksen injektoidaan sovelluslogiikalle konstruktorikutsun yhteydessä.

<img src=https://raw.githubusercontent.com/rajanssi/ot-harjoitustyo/master/dokumentaatio/kuvat/a-7.png width="500">

Lisäksi SudokuService tarjoaa refrensejjä sisällään luotuihin sovelluslogiikan luokista Settings, Game ja Statistics luotuihin olioihin. Nämä oliot on määritelty final määritteellä ja niiden luokista on olemassa vain yksi instanssi koko ohjelman käynnissäolon aikana. 

Luokassa Game hallitaan pelin kannalta oleellista toiminnallisuutta, kuten pelin ruutujen tarkistus ja peliin käytetyn ajan ylläpito. Game luokassa luodaan pelin alun yhteydessä uusi Puzzle luokan olio. Puzzle luokassa luodaan uusi peli [ulkoisen kirjaston](https://github.com/sfuhrm/sudoku) tarjoamien algoritmien avulla. Käyttöliittymälle Game luokka tarjoamat metodit ovat pääosin get ja set tyyppisiä. Muita metodeita ovat mm.

* boolean checkCell(int x, int y, int value)
* boolean checkComplete()

Settings luokassa voidaan asettaa virheiden näyttö ja pelin vaikeusaste metodeilla setDifficulty(Difficulty difficulty) ja setShowMistakes(boolean showMistakes) ja ne saadaan noudettua vastaavilla gettereillä. Tallennetut asetukset ladataan ohjelman käynnistyksen yhteydessä, tai asetetaan perusarvoille SudokuService luokan kautta, jos asetuksia ei ole vielä tallennettu. 

Statistics luokka tarjoaa SudokuService luokan tietokannasta hakemien tilastojen perusteella käyttöliittymälle tietoa pelatuista peleistä. Luokalla on vain yksi setteri, joka tallettaa kaikki tietokannan tiedot luokkaan tietojen latauksen yhteydessä SudokuService luokasta. 

Messages luokka, joka on omassa pakkauksessaan, istuu vähän sovelluslogiikan ja käyttöliittymän välillä. Sieltä voi noutaa käyttöliittymässä näytettäviä viestejä englanniksi tai suomeksi. Luokasta ei luoda olioita, vaan kaikki sen metodit ovat staattisia. Ohjelman kielen voi muuttaa käyttöliittymästä metodilla setLanguage(Language language). 

## Tietojen pysyväistallennus

Pakkauksen sudoku.dao luokat FileGameDao, FileSettingsDao ja DbStatisticsDao huolehtivat tietojen tallettamisesta tiedostoihin.

### Tiedostot

Sovellus tallettaa pelien ja asetusten tiedot erillisiin tiedostoihin ja tilastot tietokantaan.

Sovelluksen juureen sijoitettu konfiguraatiotiedosto [config.properties](https://github.com/rajanssi/ot-harjoitustyo/blob/master/Sudoku/config.properties) määrittelee tiedostojen ja tietokannan nimet. 

Sovellus tallentaa pelit seuraavassa muodossa:

<pre>
417286593;823957461;596134728;235679184;671845932;984312657;749523816;368491275;152768349;
000000100000110100000000010100010000011100010100010000001011011100011010010000000
000110100000110100000000010100010000011100010100010000001011011100011010010000000
14
EASY
</pre>

Ensin siis tallennetaan 81-soluisen 2x2 sudokuruudukon arvot rivi kerrallaan, ja jokainen rivi on 
eroteltu puolipilkulla. Sitten tallennetaan näytettävät ruudut, mukaan lukien ne, jotka käyttäjä on 
jo ratkaissut. Seuraavalle riville tallennetaan pelin alkuperäiset näytettävät ruudut. Sen jälkeen
tallennetaan pelin kesto sekunneissa ja viimeiselle riville tallennetaan pelin vaikeustaso.

Asetukset tallennetaan formaatissa

<pre>
ENGLISH;EASY;FALSE
</pre>

Kentät on eroteltu puolipistein. Ensimmäinen kenttä kertoo käyttöliittymälle tallennetun kielen, toinen kenttä kertoo peleille tallennetun vaikeustason ja kolmas kenttä kertoo, näytetäänkö käyttäjälle virheet Sudoku ruuduissa.

Tietokantaan tallennetaan pelkästään onnistuneesti ratkaistujen pelien kesto. Tietokanta tarkastetaan aina ohjelman käynnistyksen yhteydessä käskyllä 

<pre>
CREATE TABLE IF NOT EXISTS Games (duration INT)
</pre>

Tietokantaan tallennetaan pelejä käskyllä 

<pre>
INSERT INTO Games(duration) VALUES (?)
</pre>

Kysymysmerkin paikalle asetetaan tietokantakomennon toteuttavan metodin insertGame(int time) ottama parametri time. 

Tietokannasta haetaan tietoa metodilla int[] getAll() komennolla 

<pre>
SELECT COUNT(*), AVG(Duration), MIN(Duration), MAX(Duration) FROM Games
</pre>

Tietokanta paluttaa int[] taulukon SudokuService luokan oliolle, joka sitten siirtää arvot Statistics oliolle, josta ne voidaan näyttää käyttöliittymässä.

## Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaaviona.


#### Uuden pelin aloittaminen

Kun päävalikkonäkymässä klikataan btnNewGame nappia etenee sovelluksen kontrolli seuraavasti:

<img src=https://raw.githubusercontent.com/rajanssi/ot-harjoitustyo/master/dokumentaatio/kuvat/a-8.png width="750">

Painikkeen painamiseen reagoiva tapahtumankäsittelijä kutsuu sovelluslogiikan service metodia newGame(). Metodi hakee settings oliolta asetuksista vaikeustason metodilla getDifficulty(), ja antaa vaikeustason puzzle oliolle game luokan kautta metodilla getPuzzle().setPuzzle(). Game luokassa oleva puzzle olio alustaa uuden pelin spesifioidulla vaikeustasolla. Sitten service antaa vielä game oliolle käskyn initialisoida näytetyt sudoku ruudut metodilla initMasks() ja asettaa pelin ajaksi 0 metodilla setGameTime(0). Sitten käyttöliittymä vaihtaa näkymää antamalla metodille changeScene(IScene scene) parametrin gameScene, ja metodi kutsuu gameScene olion metodia setScene, joka asettaa stagelle näkymäksi pelinäkymän. 

#### Asetusten asettaminen

Kun asetusnäkymässä klikataan buttonSave nappia etenee sovelluksen kontrolli seuraavasti:

<img src=https://raw.githubusercontent.com/rajanssi/ot-harjoitustyo/master/dokumentaatio/kuvat/a-9.png width="750">

Painikkeen painamiseen reagoiva tapahtumankäsittelijä kutsuu sovelluslogiikan service metodia applySettings(language, difficulty, showMistakes). Metodi applySettings kutsuu sitten Message.setLanguage(language) metodia joka asettaa kielen. Sitten se kutsuu settings olion metodeita setDifficulty(difficulty) ja setShowMistakes(showMistakes). Metodi applySettings() kutsuu lopuksi vielä omasta luokastaan metodia saveSettings() tallentaakseen asetukset tiedostoon. Metodi saveSettings() kutstuu settingsDao olion metodia saveFile(language, difficulty, showMistakes), joka tallentaa tiedostot tiedostoon ja onnistuessaan palauttaa arvon true. Sitten käyttöliittymä vaihtaa näkymää antamalla metodille changeScene(IScene scene) parametrin settingsScene, ja metodi kutsuu settingsScene olion metodia setScene, joka lataa asetusnäkymän uudelleen.

#### Sudokuruudun tarkastus

Kun käyttäjä asettaa sudokuruutuun näppäimistöllä numeron 9 etenee sovelluksen kontrolli seuraavasti: 

<img src=https://raw.githubusercontent.com/rajanssi/ot-harjoitustyo/master/dokumentaatio/kuvat/a-10.png width="750">

Ruutuun syöttämiseen reagoiva kuuntelija kutsuu sovelluslogiikan service metodia getSettings().isShowMistakes(). Metodi palauttaa tässä tapauskessa arvon false. Tällöin siirrytään tarkastamaan ruutua, ja kutstutaan game olion metodia checkCell(9) käyttäjän syöttämällä arvolla, joka aiemmin validoitu oikeaksi. Metodi tarkastaa arvon oikeaksi, joten se asettaa ruudun näytettäväksi kutsumalla oman luokkansa metodia setMaskFalse(). Sitten metodi palauttaa arvon true pelinäkymään. Tämän jälkeen pelinäkymä tarkastaa, onko peli suoritettu, joten se kutsuu taas game oliolta metodia checkComplete(). Metodi tarkastaa kaikki peitetyt ruudut, ja jos niitä on vielä jäljellä, se palauttaa takaisin arvon false. Tällöin ruutu näytetään käyttäjälle, mutta peli jatkuu.

#### Muut toiminnallisuudet

Sama periaate toistuu sovelluksen kaikissa toiminnallisuuksissa, käyttöliittymän tapahtumakäsittelijä kutsuu sopivaa sovelluslogiikan metodia, sovelluslogiikka päivittää pelin, asetusten tai tilastojen tilaa. Kontrollin palatessa käyttöliittymään, päivitetään tarvittaessa sovelluksen tiedot sekä aktiivinen näkyvä.


## Ohjelman rakenteeseen jääneet heikkoudet

#### DAO-luokat

Dao toteutukset eivät varsinaisesti vastaa ihan oikeaoppista DAO tyylistä toteutusta ja jos datan talletustapaa päätetään vaihtaa, pitää niitä refaktoroida mahdollisesti melko perinpohjaisesti. 

#### SudokuService luokka

Ohjelman käynnistyessä luodun SudokuService luokan olion vastuulle tulee hallita sovelluslogiikkaa melko laajalti ja sitä voisi ehkä kutsua jonkinlaiseksi [jumala objektiksi](https://en.wikipedia.org/wiki/God_object). 
