package Util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Conexion {

    private static final String PERSISTENCE_UNIT_NAME = "myPU";
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static Conexion instancia;

    private Conexion() {
        if (entityManager == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            entityManager = entityManagerFactory.createEntityManager();
        }
    }

    public static synchronized Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }


    public static void main(String[] args) {
        EntityManager manager = Conexion.getInstancia().getEntityManager();

        System.out.println(manager);
    }

}
