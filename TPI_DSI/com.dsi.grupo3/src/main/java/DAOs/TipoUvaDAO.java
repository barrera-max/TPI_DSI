package DAOs;

import Entidades.Maridaje;
import Entidades.TipoUva;
import Util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TipoUvaDAO implements DAO<TipoUva, Long>{

    private final EntityManager entityManager = EntityManagerUtil.getEntityManager();

    @Override
    public void create(TipoUva tipoUva) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(tipoUva);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error creating Tipo de Uva: " + e.getMessage(), e);
        }
    }

    @Override
    public TipoUva findById(Long id) {
        try {
            entityManager.getTransaction().begin();
            TipoUva tipoUva = entityManager.find(TipoUva.class, id);
            entityManager.getTransaction().commit();

            return tipoUva;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error No Tipo de Uva ID: " + e.getMessage());
        }
    }

    @Override
    public void update(TipoUva tipoUva) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<TipoUva> findAll() {
        return List.of();
    }
}
