package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author kozmikko
 */
public class KassapaateTest {
    Kassapaate kassa1;
    Maksukortti kortti1;
    
    @Before
    public void setUp() {
        kassa1 = new Kassapaate();
        kortti1 = new Maksukortti(1000);
    }
    
    @Test
    public void luotuKassaOnOlemassa() {
        assertTrue(kassa1 != null);
    }
    @Test
    public void alkuSaldoOikein() {
        assertTrue(kassa1.kassassaRahaa() == 100000);
    }
    @Test
    public void edullistenMyyntiSaldoAlussaNolla() {
        assertTrue(kassa1.edullisiaLounaitaMyyty() == 0);
    }
    @Test 
    public void maukkaidenMyyntiSaldoAlussaNolla() {
        assertTrue(kassa1.maukkaitaLounaitaMyyty() == 0);
    }
    @Test
    public void edullisenKateisostoToimiiTasarahalla() {
        int vaihtoraha = kassa1.syoEdullisesti(240);
        assertTrue(kassa1.edullisiaLounaitaMyyty() == 1);
        assertTrue(kassa1.kassassaRahaa()==100240);
        assertTrue(vaihtoraha == 0);
    }
    @Test
    public void edullisenKateisostoToimiiYlirahalla() {
        int vaihtoraha = kassa1.syoEdullisesti(500);
        assertTrue(kassa1.edullisiaLounaitaMyyty()==1);
        assertTrue(kassa1.kassassaRahaa()==100240);
        assertTrue(vaihtoraha == 260);
    }
    @Test
    public void edullisenKateisostoFeilaa() {
        int vaihtoraha = kassa1.syoEdullisesti(200);
        assertTrue(kassa1.edullisiaLounaitaMyyty()==0);
        assertTrue(kassa1.kassassaRahaa()==100000);
        assertTrue(vaihtoraha == 200);
    }
    @Test
    public void maukkaanKateisostoToimiiTasarahalla() {
        int vaihtoraha = kassa1.syoMaukkaasti(400);
        assertTrue(kassa1.maukkaitaLounaitaMyyty()==1);
        assertTrue(kassa1.kassassaRahaa()==100400);
        assertTrue(vaihtoraha==0);
    }
    @Test
    public void maukkaanKateisostoToimiiYlirahalla(){
        int vaihtoraha = kassa1.syoMaukkaasti(500);
        assertTrue(kassa1.maukkaitaLounaitaMyyty()==1);
        assertTrue(kassa1.kassassaRahaa()==100400);
        assertTrue(vaihtoraha == 100);
    }
    @Test
    public void maukkaanKateisostoFeilaa() {
        int vaihtoraha = kassa1.syoMaukkaasti(240);
        assertTrue(kassa1.maukkaitaLounaitaMyyty()==0);
        assertTrue(kassa1.kassassaRahaa()==100000);
        assertTrue(vaihtoraha == 240);
    }
    @Test
    public void edullisenOstoKortillaToimii() {
        int kassa = kassa1.kassassaRahaa();
        boolean osto = kassa1.syoEdullisesti(kortti1);
        assertTrue(osto);
        assertTrue(kassa1.edullisiaLounaitaMyyty()==1);
        assertEquals(kassa, kassa1.kassassaRahaa());
        
    }
    @Test
    public void edullisenOstoKortillaFeilaa() {
        kortti1.otaRahaa(900);
        boolean osto = kassa1.syoEdullisesti(kortti1);
        assertTrue(!osto);
        assertTrue(kassa1.edullisiaLounaitaMyyty()==0);
    }
    @Test
    public void maukkaanOstoKortillaToimii() {
        int kassa = kassa1.kassassaRahaa();
        boolean osto = kassa1.syoMaukkaasti(kortti1);
        assertTrue(osto);
        assertTrue(kassa1.maukkaitaLounaitaMyyty()==1);
        assertEquals(kassa, kassa1.kassassaRahaa());
    }
    @Test
    public void MaukkaanOstoKortillaFeilaa() {
        kortti1.otaRahaa(900);
        boolean osto = kassa1.syoMaukkaasti(kortti1);
        assertTrue(!osto);
        assertTrue(kassa1.maukkaitaLounaitaMyyty()==0);
    }
    @Test
    public void kortinLataaminenOnnistuu() {
        kassa1.lataaRahaaKortille(kortti1, 1000);
        assertTrue(kassa1.kassassaRahaa()==101000);
        assertEquals("saldo: 20.0", kortti1.toString());
    }
    @Test
    public void negatiivinenLatausEiOnnistu() {
        kassa1.lataaRahaaKortille(kortti1, -500);
        assertTrue(kassa1.kassassaRahaa()==100000);
        assertEquals("saldo: 10.0", kortti1.toString());
    }
    
}
