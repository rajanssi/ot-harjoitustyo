package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussa() {
        assertEquals(kortti.toString(), "saldo: 0.10");
    }
    
    @Test
    public void lataaRahaa(){
        kortti.lataaRahaa(10);
        assertEquals(kortti.saldo(), 20);
    }
    
    @Test
    public void otaRahaa(){
        assertEquals(kortti.otaRahaa(9), true);
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi(){
        assertEquals(kortti.otaRahaa(11), false);
    }
}
