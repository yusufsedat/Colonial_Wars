/**
*
* @author Yusuf Sedat SAĞALTICI yusuf.sagaltici@ogr.sakarya.edu.tr B201210031
* @since 31.05.2023
* <p>
* Oyun kuralları ve savaşın yapıldığı yer
* </p>
*/



package pdpOdevC;

import java.util.ArrayList;
import java.util.List;


public class Oyun {
    private List<Koloni> koloniler;

    public Oyun() {
        this.koloniler = new ArrayList<>();
    }

    public void koloniEkle(Koloni koloni) {
        koloniler.add(koloni);
    }
    
   private int turSayisi=0;

   
   public int getTurSayisi() {
       return this.turSayisi;
   }
  

    public void baslat() {
    	    	
    	System.out.println("-------------------------------------------");
        System.out.println("Tur Sayisi: " + turSayisi);
        System.out.printf("%-10s %-12s %-10s\n", "Sembol", "Populasyon", "Yemek");

        for (Koloni koloni : koloniler) {
            if (koloni != null) {
                if(koloni.getPopulasyon() > 0) {
                    System.out.printf("%-10s %-12d %-10d\n", 
                                      koloni.getSembol(), 
                                      koloni.getPopulasyon(), 
                                      koloni.getYemekStogu());

                }
            }
        }
    	
        while (true) {
        		
        	int aktifKoloniSayisi = 0;
        	turSayisi++;
        	
        	 for (Koloni koloni : koloniler) {
                 koloni.setSavasYapildi(false);
             }
            for (int i = 0; i < koloniler.size(); i++) {
                Koloni mevcutKoloni = koloniler.get(i);

                mevcutKoloni.populasyonGuncelle();
                mevcutKoloni.yemekStoguGuncelle();
                
                              
                
                for (int j = 0; j < koloniler.size(); j++) {
                    if (i != j && !mevcutKoloni.getSavasYapildi()&& !koloniler.get(j).getSavasYapildi()) {
                    	
                        Koloni digerKoloni = koloniler.get(j);
                                             
                     
                        if (mevcutKoloni.savas(digerKoloni)) {
                        	mevcutKoloni.setSavasYapildi(true);
                        	digerKoloni.setSavasYapildi(true);
                            int fark = mevcutKoloni.getPopulasyon() - digerKoloni.getPopulasyon();
                            int yemekTransferi = digerKoloni.getYemekStogu() * fark / 1000;

                            mevcutKoloni.setPopulasyon(mevcutKoloni.getPopulasyon() + fark);
                            mevcutKoloni.setYemekStogu(mevcutKoloni.getYemekStogu() + yemekTransferi);

                            digerKoloni.setPopulasyon(digerKoloni.getPopulasyon() - fark);
                            digerKoloni.setYemekStogu(digerKoloni.getYemekStogu() - yemekTransferi);
                            
                            
                        }
                    }
                }
            }
            
            for (Koloni koloni : koloniler) {
                if (!koloni.getIsDead()) { // Eğer koloni ölü değilse, aktif koloni sayısını artır
                    aktifKoloniSayisi++;
                }
            }
            System.out.println("-------------------------------------------");
            System.out.println("Tur Sayisi: " + turSayisi);
            System.out.printf("%-10s %-12s %-10s %-18s %-18s\n", "Sembol", "Populasyon", "Yemek", "Kazanılan Savaş", "Kaybedilen Savaş");

            for (Koloni koloni : koloniler) {
            	
                if (koloni != null) {
                    if(koloni.getPopulasyon() > 0) {
                        System.out.printf("%-10s %-12d %-10d %-18d %-18d\n", 
                                          koloni.getSembol(), 
                                          koloni.getPopulasyon(), 
                                          koloni.getYemekStogu(),
                                          koloni.getKazanilanSavas(),
                                          koloni.getKaybedilenSavas());
                    } else {
                        System.out.printf("%-10s %-12s %-10s %-18s %-18s\n", 
                                          koloni.getSembol(), 
                                          "---", 
                                          "---", 
                                          "---", 
                                          "---");
                    }
                }
            }

            System.out.println("-------------------------------------------");
            
            if (aktifKoloniSayisi <= 1) {
                break;
            }
         
         
        }
           
    }
}
   

