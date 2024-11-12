package Util;

import Entidades.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EntityManagerUtil {

    private static final String PERSISTENCE_UNIT_NAME = "myPU";

    private static final EntityManager ENTITY_MANAGER = getEntityManager();


    //aplicar singleton en esta clase

    public static EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        EntityManager em = factory.createEntityManager();
        return em;
    }




    public static void main(String[] args) {
        EntityManager manager = EntityManagerUtil.getEntityManager();

    }

}
