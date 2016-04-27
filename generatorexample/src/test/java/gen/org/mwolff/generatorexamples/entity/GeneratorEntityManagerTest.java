package gen.org.mwolff.generatorexamples.entity;

import java.util.function.BiFunction;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GeneratorEntityManagerTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void testEntitySupplier() throws Exception {
        
        GeneratorEntityManager generatorEntityManager = new GeneratorEntityManager();
        BiFunction<EntityManager, EntityTransaction, String> code = (em, tx) -> {
            Adress adress = em.find(Adress.class, 1);
            
            if (adress == null) {                                                         
              adress = new Adress();                                                        
              adress.setId(1);                                                              
              adress.setCity("Bremen");                                                          

              tx.begin();                                                               
              em.merge(adress);                                                         
              tx.commit();                                                              
            }   
            return String.format("Adress{id=%s, city=%s}", adress.getId(), adress.getCity());
        };
        assertThat("Adress{id=1, city=Bremen}", is(generatorEntityManager.accept(code)));
        
    }

}
