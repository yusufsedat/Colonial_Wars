package pdpOdevC;

import java.util.Random;

public class BTaktik implements Taktik {
    @Override
    public int savas() {
       
      return new Random().nextInt(501)+500; // 0-750 arasında bir sayı döndürür
      
    }
}