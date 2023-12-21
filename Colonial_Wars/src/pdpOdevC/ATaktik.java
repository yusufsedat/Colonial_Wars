package pdpOdevC;

import java.util.Random;

public class ATaktik implements Taktik {
    @Override
    public int savas() {
        
        return new Random().nextInt(1001); // 0-1000 arasında bir sayı döndürür
    }
}
