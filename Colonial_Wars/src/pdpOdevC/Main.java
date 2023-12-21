/**
*
* @author Yusuf Sedat SAĞALTICI yusuf.sagaltici@ogr.sakarya.edu.tr B201210031
* @since 31.05.2023
* <p>
* Main 
* </p>
*/


package pdpOdevC;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        // Taktik ve üretim tiplerinin listelerini oluştur
        List<Taktik> taktikler = Arrays.asList(new ATaktik(), new BTaktik());
        List<Uretim> uretimler = Arrays.asList(new AUretim(), new BUretim());

        Random random = new Random();

        // Oyun nesnesinin oluşturulması
        Oyun oyun = new Oyun();
      
        
        Scanner scanner = new Scanner(System.in); // Kullanıcıdan popülasyonları al
        
        System.out.print("Lütfen popülasyonları boşlukla ayırarak giriniz: ");
        String girdi = scanner.nextLine();
        String[] popülasyonlar = girdi.split(" "); 
        
        // Kullanılacak sembollerin listesi
        List<Character> semboller = new ArrayList<>();
        for (char i = 'A'; i <= 'Z'; i++) {
            semboller.add(i);
        }
        // Semboller listesini karıştırırız böylece daha sonra bu listeden rastgele bir sembol seçmiş oluruz.
        Collections.shuffle(semboller);

        // Her popülasyon için bir koloni oluştur ve oyun nesnesine ekle
        for (String popülasyon : popülasyonlar) {
            int baslangicPopulasyonu = Integer.parseInt(popülasyon);
            char sembol = semboller.remove(semboller.size() - 1);  // Listeden rastgele bir sembol seç ve listeden çıkar
            Taktik rastgeleTaktik = taktikler.get(random.nextInt(taktikler.size()));
            Uretim rastgeleUretim = uretimler.get(random.nextInt(uretimler.size()));

            Koloni koloni = new Koloni(baslangicPopulasyonu, sembol, rastgeleTaktik, rastgeleUretim);
            oyun.koloniEkle(koloni);
        }

        // Oyunun başlatılması
        oyun.baslat();
        scanner.close();
        

    }
}
