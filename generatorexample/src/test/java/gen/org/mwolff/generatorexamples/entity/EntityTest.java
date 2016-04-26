package gen.org.mwolff.generatorexamples.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EntityTest {
    
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void test() throws Exception {
        thrown.expect(PersistenceException.class);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");  
        EntityManager em = emf.createEntityManager();                               
        EntityTransaction tx = em.getTransaction();                                 

        Adress adress = em.find(Adress.class, 1);
        
        if (adress == null) {                                                         
          adress = new Adress();                                                        
          adress.setId(1);                                                              
          adress.setCity("Bremen");                                                          

          tx.begin();                                                               
          em.merge(adress);                                                         
          tx.commit();                                                              
        }                                                                           

        System.out.format("Adress{id=%s, city=%s}\n", adress.getId(), adress.getCity());            
        
        em.close();                                                                 
        emf.close(); 
    }

}
