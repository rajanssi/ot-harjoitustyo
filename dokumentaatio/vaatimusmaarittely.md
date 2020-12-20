# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on pohjimmiltaan klassisen 9x9 Sudoku-peli. Sovellus on kaksikielinen ja sen voi saada toimimaan suomeksi tai englanniksi. Sovellus tarjoaa eri vaikeustasoja, joita käyttäjä voi vapaasti vaihtaa asetuksista. Sovelluksesta näkyy myös tilastoja omista Sudokusuorituksista.

## Käyttöliittymä ja perustoiminallisuus

Sovellus koostuu neljästä näkymästä. Käyttöliittymä käynnistyy valikkoon, josta käyttäjä voi aloittaa uuden pelin, siirtyä asetuksiin tai katsella tilastoja. Valikkonäkymästä voi myös jatkaa tallennettua peliä, jos sellainen on olemassa. 

<img src="https://raw.githubusercontent.com/rajanssi/ot-harjoitustyo/master/dokumentaatio/kuvat/c-1.png" width="500">

Asetuksissa käyttäjä voi vaihtaa käyttöliittymän kieltä, pelin vaikeustasoa tai virheelliseten sudokuruutujen näyttämistä. Asetukset pysyväistallennetaan ja seuraavalla kerralla sovellusta käynnistäessä ne ovat samat, kuin mitkä edellisellä kerralla määriteltiin.

<img src="https://raw.githubusercontent.com/rajanssi/ot-harjoitustyo/master/dokumentaatio/kuvat/c-2.png" width="500">

Pelinäkymässä käyttäjä syöttää näppäimistöllä numeroita sudokuruudukkoon. Poistuessaan sovelluksesta tai palatessaan päävalikkoon käynnissä oleva peli tallennetaan automaattisesti ja sitä voi jatkaa myöhemmin siitä mihin jäätiin. Pelinäkymästä voi myös suoraan aloittaa uuden pelin.

<img src="https://raw.githubusercontent.com/rajanssi/ot-harjoitustyo/master/dokumentaatio/kuvat/c-3.png" width="500">

Ratkaistuaan sudokun, käyttäjälle ilmoitetaan siitä ja kysytään, haluaako hän pelata uudelleen. 

<img src="https://raw.githubusercontent.com/rajanssi/ot-harjoitustyo/master/dokumentaatio/kuvat/c-4.png" width="200">

Tilastonäkymässä voi katsella onnistuneesti ratkaistujen pelien tilastoja.

<img src="https://raw.githubusercontent.com/rajanssi/ot-harjoitustyo/master/dokumentaatio/kuvat/c-5.png" width="500">

## Jatkokehitysideoita

- Hienompi käyttöliittymä, mahdollisuus toimia ilman näppäimistöä
- Vinkkinappi, joka paljastaa ruutuja
- Mahdollisuus kirjoittaa numeromuistiinpanoja ruutuihin
- Useita käyttäjätunnuksia samalla koneella, ja mahdollisuus kirjautua ulos/sisään
- Suoritusten mahdollinen tallentaminen verkkoon ranking-listalle
