package Util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Conexion {

    private static final String PERSISTENCE_UNIT_NAME = "myPU";
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static Conexion instancia;

    // Constructor privado para evitar instancias externas
    private Conexion() {
        if (entityManager == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            entityManager = entityManagerFactory.createEntityManager();
        }
    }

    // Método estático y sincronizado para obtener la única instancia
    public static synchronized Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    // Método para obtener el EntityManager
    public EntityManager getEntityManager() {
        return entityManager;
    }


    public static void main(String[] args) {
        EntityManager manager = Conexion.getInstancia().getEntityManager();

        System.out.println(manager);
    }

}
