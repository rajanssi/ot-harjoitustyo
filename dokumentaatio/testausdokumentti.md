# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Automatisoitujen testien päähuomiona on sudoku.domain pakkauksen SudokuService luokka. Sen kautta pääsee käsiksi lähes kaikkiin sovelluslogiikan muihin luokkiin, joten sitä testaamalla kaikkien muiden luokkien toiminnallisuus testautuu samalla. Lisäksi käyttöliittymä ja sovelluslogiikka kommunikoivat pääasiassa SudokuService luokan kautta, joten on mielekästä testata suurin osa sovelluslogiikan toiminnallisuuksista sen kautta. GameServiceTest on siis pääasiassa integraatiotestausta. Esim. SettingsTest on kuitenkin puhtaasti yksikkötestausta.

### DAO-luokat

Dao luokissa on myös paljon integraatiotestausta, mutta täällä on enemmän ihan puhtaita yksikkötestejä, kuin domain luokissa.

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 87% ja haarautumakattavuus 59%

<img src="https://raw.githubusercontent.com/rajanssi/ot-harjoitustyo/master/dokumentaatio/kuvat/b-1.png" width="800">

Testaamatta jätettiin mm. moni Message luokan metodeista, koska niissä toiminnallisuus on lähes identtistä, joten virheet oletettavasti havaittaisiin kääntämisen aikana.

Ilman sudoku.messages pakkausta sovelluksen testauksen rivikattavuus on 95% ja haarautumakattavuus 92%.

<img src="https://raw.githubusercontent.com/rajanssi/ot-harjoitustyo/master/dokumentaatio/kuvat/b-2.png" width="800">

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/rajanssi/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) kuvaamalla tavalla Linux-ympäristössä siten, että sovelluksen käynnistyshakemistossa on ollut käyttöohjeen kuvauksen mukainen _config.properties_-tiedosto.

Sovellusta on testattu sekä tilanteissa, joissa käyttäjät ja työt tallettavat tiedostot ovat olleet olemassa ja joissa niitä ei ole ollut jolloin ohjelma on luonut ne itse.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/rajanssi/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on sudokuruudut yritetty täyttää myös virheellisillä arvoilla kuten kirjaimilla.
