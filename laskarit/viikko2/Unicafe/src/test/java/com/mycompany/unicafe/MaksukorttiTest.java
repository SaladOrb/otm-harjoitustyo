package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void alkuSaldoOikein() {

        assertEquals("saldo: 10.0", kortti.toString());
    }

    @Test
    public void saldoKasvaaOikein() {

        kortti.lataaRahaa(700);
        assertEquals("saldo: 17.0", kortti.toString());
    }

//    @Test
//    public void maksimiSaldoEiYlity() {
//        kortti.lataaRahaa(15000);
//        assertEquals("saldo: 150.0", kortti.toString());
//    }

    @Test
    public void saldoToimiiTosinTietystiSeToimii() {
        assertEquals("10.0", ""+(double)kortti.saldo() / 100);
    }

    @Test
    public void riittavanSummanVahennysToimii() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 5.0", kortti.toString());
    }

    @Test
    public void liiallisenSummanVahennysToimii() {
        kortti.otaRahaa(1500);
        assertEquals("saldo: 10.0", kortti.toString());
    }

    @Test
    public void onnistunutMaksuPalauttaaTrue() {
        Boolean palautus = kortti.otaRahaa(1000);
        assertEquals("saldo: 0.0", kortti.toString());
    }

    @Test
    public void epaonnistunutMaksuPalauttaaFalse() {
        Boolean palautus = kortti.otaRahaa(1300);
        assertEquals("saldo: 10.0", kortti.toString());
    }
}
