package DAOs;

import Entidades.Bodega;
import Entidades.Siguiendo;
import Entidades.TipoUva;
import Util.Conexion;
import jakarta.persistence.EntityManager;

import java.util.List;

public class SiguiendoDAO implements DAO<Siguiendo, Long> {

    private static final EntityManager ENTITY_MANAGER = Conexion.getInstancia().getEntityManager();

    @Override
    public void create(Siguiendo siguiendo) {

    }

    @Override
    public Siguiendo findById(Long id) {
        try {
            ENTITY_MANAGER.getTransaction().begin();
            Siguiendo siguiendo = ENTITY_MANAGER.find(Siguiendo.class, id);
            ENTITY_MANAGER.getTransaction().commit();

            return siguiendo;
        } catch (Exception e) {
            ENTITY_MANAGER.getTransaction().rollback();
            throw new RuntimeException("Error No Siguiendo ID: " + e.getMessage());
        }
    }

    @Override
    public void update(Siguiendo siguiendo) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<Siguiendo> findAll() {
        try {
            ENTITY_MANAGER.getTransaction().begin();

            List<Siguiendo> siguiendo = ENTITY_MANAGER.createQuery("SELECT si FROM Siguiendo si").getResultList();

            ENTITY_MANAGER.getTransaction().commit();
            return siguiendo;
        } catch (Exception e) {
            ENTITY_MANAGER.getTransaction().rollback();
            throw new RuntimeException("Error finding Siguiendo: " + e.getMessage(), e);
        }
    }
}
