package Util;

import Entidades.Maridaje;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class EntityManagerUtil {


    public static EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myPU");

        EntityManager em = factory.createEntityManager();
        return em;
    }


    public static void main(String[] args) {
        EntityManager manager = EntityManagerUtil.getEntityManager();

        System.out.println("OBTENIENDO MARIDAJES");

        Maridaje maridajes = manager.find(Maridaje.class, 1L);

        System.out.println(maridajes);
    }




}
