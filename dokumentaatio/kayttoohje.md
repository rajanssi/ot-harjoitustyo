# Käyttöohje
Lataa tiedosto [Sudoku-2.0.jar](https://github.com/rajanssi/ot-harjoitustyo/releases/tag/viikko7)

## Konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto config.properties, joka määrittelee käyttäjät ja todot tallettavien tiedostojen nimet. Tiedoston muoto on seuraava


```
settingsFile=settings.csv
gameFile=saveGame.csv
dbUrl=jdbc:sqlite:statistics.db
```

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
Sudoku-2.0.jar
```

## Pelin aloittaminen

Ensimmäistä kertaa peliä pelatessasi voit aloittaa uuden pelin klikkaamalla "Uusi peli" nappia valikosta. Valikosta voit myös siirtyä asetuksiin tai katsella tilastoja, joskin tilastoissa ei ole mitään erityisen mielenkiinoista, mikäli et ole pelannut vielä yhtään peliä.

### Sudokun pelaaminen

"Sudoku on logiikkapeli, jossa tehtävänä on täyttää neliönmuotoinen ruudukko merkeillä niin että jokaisella vaakarivillä 
ja pystyrivillä sekä jokaisessa osaneliössä käytetään samaa merkkiä tasan yhden kerran. Ruudukossa on aluksi valmiina jo muutama merkki." ([Wikipedia](https://fi.wikipedia.org/wiki/Sudoku))

Klikkaa jotain tyhjää ruutua johon haluat syöttää arvauksesi. Syötä sitten jokin numero kenttään. Oikein syötetty numero muuttuu vihreäksi, jos et ole vaihtanut asetusten vaihtoehtoa "näytä virheet". Numeron syöttämisen jälkeen ei tarvitse painaa mitään muuta, sillä sovellus tarkastaa aina automaattisesti, onko ruutu syötetty oikein ja onko kaikki ruudut ratkaistu. Syötettyäsi oikein viimeisenkin sudokuruudun peli havaitsee sen heti ja ilmoittaa siitä sinulle.

### Uuden pelin aloittaminen

Uuden pelin voi aloittaa "Uusi peli"/"New game" nappia painamalla pelinäkymässä. Myös ratkaisemalla Sudokun, peli kysyy, jos haluat pelata uudestaan.

### Pelin tallentaminen

Sulkiessasi sovelluksen tai siirtymällä takaisin valikkoon käynnissä oleva pelisi tallennetaan.

### Pelin jatkaminen

Jos jätät pelin kesken, se tallennetaan ja voit jatkaa sitä myöhemmin päävalikon "Jatka peliä"/"Continue game" nappulaa painamalla.

## Asetukset

Asetuksista voit vaihtaa käyttöliittymän kieltä, uusien pelien vaikeustasoa tai virheiden näyttöä. Painamalla alaoikealta nappia "Hyväksy" tai englanniksi "Apply" tallennat valitsemasi asetukset.

### Kieli

Oletusarvoisesti ohjelman kieli on suomi, mutta voit asettaa kieleksi myös englannin halutessasi.

### Vaikeustaso

Vaikeustaso määrittelee sen, kuinka monta ratkaistua ruutua käyttäjälle näytetään pelin alussa. Vaikea Sudoku on kokeneillekkin Sudokun pelaajille melko haastava.

### Virheiden näyttö

Jos virheiden näyttö on päällä, sudokua pelatessa virheellisesti syötetyt ruudut näytetään punaisella ja oikein syötetyt ruudut näytetään vihreällä. 

## Tilastot

Täältä näet tilastoja onnistuneesti suorittamistasi peleistä. Takaisin valikkoon pääset klikkaamalla alaoikealta nappia "Päävalikkoon" tai "Menu".

## Ohjelmasta poistuminen

Voit poistua ohjelmasta milloin tahansa painamalla ruksia yläoikealta. Jos sinulla on peli kesken, se tallennetaan tiedostoon ja voit jatkaa siitä mihin jäit. Ohjelmasta voi poistua myös päävalikon "Sulje"/"Quit" napin avulla.
