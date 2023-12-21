package pdpOdevC;

import java.util.Random;

public class BUretim implements Uretim {
    @Override
    public int uret() {
        
        return new Random().nextInt(5) + 6; // 6-10 arasında bir sayı döndürür
    }
}
