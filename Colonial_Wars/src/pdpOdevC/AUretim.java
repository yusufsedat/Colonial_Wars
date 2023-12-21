package pdpOdevC;

import java.util.Random;

public class AUretim implements Uretim {
    @Override
    public int uret() {
        
        return new Random().nextInt(10) + 1; // 1-10 arasında bir sayı döndürür
    }
}
