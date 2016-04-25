import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

public class ToTestTest {

    @Test
    public void test() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");  
        EntityManager em = emf.createEntityManager();                               
        EntityTransaction tx = em.getTransaction();                                 

        ToTest test = em.find(ToTest.class, 1);                                         
        if (test == null) {                                                         
          test = new ToTest();                                                        
          test.id = 1;                                                              
          test.data = "a";                                                          

          tx.begin();                                                               
          em.persist(test);                                                         
          tx.commit();                                                              
        }                                                                           

        System.out.format("Test{id=%s, data=%s}\n", test.id, test.data);            

        em.close();                                                                 
        emf.close(); 
    }

}
