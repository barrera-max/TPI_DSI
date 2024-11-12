package DAOs;

import Entidades.Bodega;
import Util.Conexion;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BodegaDAO implements DAO<Bodega, Long>{

    private final EntityManager em = Conexion.getInstancia().getEntityManager();

    @Override
    public void create(Bodega bodega) {

    }

    @Override
    public Bodega findById(Long aLong) {
        return null;
    }

    @Override
    public void update(Bodega bodega) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<Bodega> findAll() {
        try {
            em.getTransaction().begin();

            List<Bodega> bodegas = em.createQuery("SELECT b FROM Bodega b").getResultList();

            em.getTransaction().commit();
            return bodegas;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error finding Bodegas: " + e.getMessage(), e);
        }
    }
}
