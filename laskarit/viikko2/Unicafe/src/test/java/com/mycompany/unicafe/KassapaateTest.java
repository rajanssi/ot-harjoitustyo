package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void rahamaara() {
        assertEquals(kassa.kassassaRahaa(), 100000);
    }

    @Test
    public void lounaitaMyyty() {
        int myyty = kassa.maukkaitaLounaitaMyyty() + kassa.edullisiaLounaitaMyyty();
        assertEquals(myyty, 0);
    }

    @Test
    public void edullinenKateisostoKasvattaaSaldoa() {
        assertEquals(kassa.syoEdullisesti(500), 260);
        assertEquals(kassa.kassassaRahaa(), 100240);
    }

    @Test
    public void maukasKateisostoKasvattaaSaldoa() {
        assertEquals(kassa.syoMaukkaasti(500), 100);
        assertEquals(kassa.kassassaRahaa(), 100400);
    }

    @Test
    public void myytyjenLounaidenMaaraKasvaa() {
        kassa.syoEdullisesti(300);
        kassa.syoMaukkaasti(500);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 1);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 1);
    }

    @Test
    public void riittamatonEdullinenOstosPalautetaan() {
        assertEquals(kassa.syoEdullisesti(100), 100);
        assertEquals(kassa.kassassaRahaa(), 100000);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 0);
    }

    @Test
    public void riittamatonMaukasOstosPalautetaan() {
        assertEquals(kassa.syoMaukkaasti(100), 100);
        assertEquals(kassa.kassassaRahaa(), 100000);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 0);
    }

    @Test
    public void edullisetKorttiOstotToimii() {
        assertEquals(kassa.syoEdullisesti(kortti), true);
    }
    
    @Test
    public void maukkaatKorttiOstotToimii() {
        assertEquals(kassa.syoMaukkaasti(kortti), true);
    }
    
    @Test
    public void edullisetKorttiOstotKasvattaaMyytyjenLounaidenMaaraa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 1);
    }
    
    @Test
    public void maukkaatKorttiOstotKasvattaaMyytyjenLounaidenMaaraa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 1);
    }
    
    @Test
    public void lounaitaEiMyydaJosKortillaEiOleTarpeeksi(){
        kortti.otaRahaa(900);
        assertEquals(kassa.syoEdullisesti(kortti), false);
        assertEquals(kassa.syoMaukkaasti(kortti), false);
    }
    
    
    @Test
    public void lounaidenMaaraEiMuutuJosKortillaEiOleTarpeeksi(){
        kortti.otaRahaa(900);
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 0);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void kortinRahamaaraEiMuutuJosEiOleTarpeeksi(){
        kortti.otaRahaa(900);
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(kortti.saldo(), 100);
    }
    
    @Test 
    public void lataaRahaaKortilleKassasta(){
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(kassa.kassassaRahaa(), 101000);
        assertEquals(kortti.saldo(), 2000);
    }
    
    @Test 
    public void lataaNegatiivistaRahaaKortilleKassasta(){
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(kassa.kassassaRahaa(), 100000);
        assertEquals(kortti.saldo(), 1000);
    }
}
