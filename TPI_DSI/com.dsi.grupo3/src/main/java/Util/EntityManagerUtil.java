package Util;

import Entidades.Maridaje;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EntityManagerUtil {


    public static EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_PU");

        EntityManager em = factory.createEntityManager();
        return em;
    }


    public static void main(String[] args) {
        EntityManager manager = EntityManagerUtil.getEntityManager();
        System.out.println("EntityManager class ==>" + manager.getClass().getCanonicalName());

        System.out.println("OBTENIENDO MARIDAJES");

        Maridaje maridajes = manager.find(Maridaje.class, 1L);

        System.out.println(maridajes);
    }




}
