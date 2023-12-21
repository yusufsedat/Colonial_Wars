/**
*
* @author Yusuf Sedat SAĞALTICI yusuf.sagaltici@ogr.sakarya.edu.tr B201210031
* @since 31.05.2023
* <p>
* Savas kurallarının ve kolonilerin oluşturulduğu yer
* </p>
*/



package pdpOdevC;

import java.util.Random;

public class Koloni {
    private int populasyon;
    private int yemekStogu;
    private char sembol;
    private Taktik taktik;
    private Uretim uretim;
    private int kazanilanSavas;
    private int kaybedilenSavas;
    private boolean savasYapildi;
    private boolean isDead = false;
    

    public Koloni(int populasyon, char sembol, Taktik taktik, Uretim uretim) {
        this.populasyon = populasyon;
        this.sembol = sembol;
        this.taktik = taktik;
        this.uretim = uretim;
        this.yemekStogu = populasyon * populasyon;
        this.kazanilanSavas = 0;
        this.kaybedilenSavas = 0;
        this.savasYapildi = false;
    }	

    public void populasyonGuncelle() {
        this.populasyon = (int)(this.populasyon * 1.2);
        
        if (this.getPopulasyon() <= 0) {
            this.setIsDead(true);
        }
        
    }

    public void yemekStoguGuncelle() {
        this.yemekStogu -= this.populasyon * 2;
        if (this.yemekStogu < 0) {
            this.yemekStogu = 0;
            isDead=true;
        }
        this.yemekStogu += this.uretim.uret();
    }
    

    
    public boolean savas(Koloni digerKoloni) {
        int buKoloninTaktikSonucu = this.taktik.savas();
        int digerKoloninTaktikSonucu = digerKoloni.taktik.savas();

        int fark = Math.abs(buKoloninTaktikSonucu - digerKoloninTaktikSonucu);
        int farkOrani = fark / 10;  // 0-1000 arası değer için, 10'a bölünürse %1-%100 arası bir değer elde edilir.
        
        if (this.getPopulasyon() <= 0) {
            this.setIsDead(true);
        }
        
        if (digerKoloni.getPopulasyon() <= 0) {
            digerKoloni.setIsDead(true);
        }

        if (buKoloninTaktikSonucu > digerKoloninTaktikSonucu || (buKoloninTaktikSonucu == digerKoloninTaktikSonucu && this.populasyon > digerKoloni.populasyon)) {
            // Bu koloni savaşı kazandı
            digerKoloni.populasyon -= digerKoloni.populasyon * farkOrani / 100;
            this.yemekStogu += digerKoloni.yemekStogu * farkOrani / 100;
            digerKoloni.yemekStogu -= digerKoloni.yemekStogu * farkOrani / 100;
            this.savasKazandi();
            digerKoloni.savasKaybetti();
            return true;
        } else if (buKoloninTaktikSonucu < digerKoloninTaktikSonucu || (buKoloninTaktikSonucu == digerKoloninTaktikSonucu && this.populasyon < digerKoloni.populasyon)) {
            // Diğer koloni savaşı kazandı
            this.populasyon -= this.populasyon * farkOrani / 100;
            digerKoloni.yemekStogu += this.yemekStogu * farkOrani / 100;
            this.yemekStogu -= this.yemekStogu * farkOrani / 100;
            this.savasKaybetti();
            digerKoloni.savasKazandi();
            return false;
        } else {
            // Eğer taktik sonuçları ve populasyon sayısı aynıysa, rastgele bir koloni kazanır
            if (new Random().nextBoolean()) {
                digerKoloni.populasyon -= digerKoloni.populasyon * farkOrani / 100;
                this.yemekStogu += digerKoloni.yemekStogu * farkOrani / 100;
                digerKoloni.yemekStogu -= digerKoloni.yemekStogu * farkOrani / 100;
                this.savasKazandi();
                digerKoloni.savasKaybetti();
                return true;
            } else {
                this.populasyon -= this.populasyon * farkOrani / 100;
                digerKoloni.yemekStogu += this.yemekStogu * farkOrani / 100;
                this.yemekStogu -= this.yemekStogu * farkOrani / 100;
                this.savasKaybetti();
                digerKoloni.savasKazandi();
                return false;
            }
        } 
       
        
        
        

    }
    

    // Savaş sonucunda kazanılan veya kaybedilen savaşların sayısını güncellemek için metotlar

    public void savasKazandi() {
        this.kazanilanSavas++;
    }

    public void savasKaybetti() {
        this.kaybedilenSavas++;
        if (this.populasyon <= 0) {
            setIsDead(true);
        }
    }


    public boolean hayattaMi() {
        return this.populasyon > 0 && this.yemekStogu > 0;
    }

    @Override
    public String toString() {
	   return "Koloni " + sembol + " Populasyon: " + populasyon + " Yemek Stogu: " + yemekStogu;
  }

    // getter ve setter metotları

    public int getPopulasyon() {
        return populasyon;
    }

    public void setPopulasyon(int populasyon) {
        this.populasyon = populasyon;
    }

    public int getYemekStogu() {
        return yemekStogu;
    }

    public void setYemekStogu(int yemekStogu) {
        this.yemekStogu = yemekStogu;
    }

    public char getSembol() {
        return sembol;
    }

    public void setSembol(char sembol) {
        this.sembol = sembol;
    }
    
    public int getKazanilanSavas() {
        return kazanilanSavas;
    }

    public int getKaybedilenSavas() {
        return kaybedilenSavas;
    }
    public boolean getSavasYapildi() {
        return this.savasYapildi;
    }

    public void setSavasYapildi(boolean savasYapildi) {
        this.savasYapildi = savasYapildi;
    }
    public boolean getIsDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }
    
}

