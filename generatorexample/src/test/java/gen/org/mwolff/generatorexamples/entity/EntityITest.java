/**
    GeneratorExample.
    Reference Implementation to use the Simple Generator Framework.

    @author Manfred Wolff <m.wolff@neusta.de>
    Download: https://github.com/simplecommand/SimpleCommandFramework-Examples


    Copyright (C) 2016 neusta software development

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
    USA
 */
package gen.org.mwolff.generatorexamples.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EntityITest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testITest() throws Exception {
     
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        code(em, tx);

        em.close();
        emf.close();
    }

    public void code(EntityManager em, EntityTransaction tx) throws Exception{

        Adress adress = null;
        try {
            adress = em.find(Adress.class, 1);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (adress == null) {
            adress = new Adress();
            adress.setId(1);
            adress.setCity("Bremen");

            tx.begin();
            em.merge(adress);
            tx.commit();
        }

        System.out.format("Adress{id=%s, city=%s}\n", adress.getId(), adress.getCity());
    }
}