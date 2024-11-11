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


    public static EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        EntityManager em = factory.createEntityManager();
        return em;
    }


    //eliminar los metodos y dejar unicamente la conexion a la bd

    public List<Maridaje> getMaridajes() {
        try{
            return (List<Maridaje> )ENTITY_MANAGER.createNamedQuery("Maridaje.findAll").getResultList();
    }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<TipoUva> getTiposUva() {
        try{
            return (List<TipoUva>) ENTITY_MANAGER.createNamedQuery("TipoUva.findAll").getResultList();
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Varietal> getVarietales() {
        try{
            return (List<Varietal>) ENTITY_MANAGER.createNamedQuery("Varietal.findAll").getResultList();
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Bodega> getBodegas() {
        try{
            return (List<Bodega>) ENTITY_MANAGER.createNamedQuery("Bodega.findAll").getResultList();
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Vino> getVinos() {
        try{
            return (List<Vino>) ENTITY_MANAGER.createNamedQuery("Vino.findAll").getResultList();
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }



    public static void main(String[] args) {
        EntityManager manager = EntityManagerUtil.getEntityManager();

    }

}
