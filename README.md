<h1>Sudokusovellus</h1>
Kyseessä on klassinen Sudoku-peli. Ajan salliessa sovellukseen voi lisätä itse asian lisäksi kaikkea muuta hyödyllistä tilpehööriä.
<h2>Dokumentaatio</h2>

[Vaatimusmäärittely](https://github.com/rajanssi/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/rajanssi/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/rajanssi/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

<h2>Releaset</h2>

[Viikko 5](https://github.com/rajanssi/ot-harjoitustyo/releases/tag/viikko5)

<h2>Komentorivitoiminnot</h2>

<h3>Testaus</h3>
Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

<h3>Checkstyle</h3>

Tiedostoon [checkstyle.xml](https://github.com/rajanssi/ot-harjoitustyo/blob/master/Sudoku/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
