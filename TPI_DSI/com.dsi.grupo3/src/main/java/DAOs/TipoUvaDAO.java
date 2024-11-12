package DAOs;

import Entidades.TipoUva;
import Util.Conexion;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TipoUvaDAO implements DAO<TipoUva, Long>{

    private final EntityManager ENTITY_MANAGER = Conexion.getInstancia().getEntityManager();

    @Override
    public void create(TipoUva tipoUva) {
        try {

            ENTITY_MANAGER.getTransaction().begin();
            ENTITY_MANAGER.persist(tipoUva);
            ENTITY_MANAGER.getTransaction().commit();
        } catch (Exception e) {
            ENTITY_MANAGER.getTransaction().rollback();
            throw new RuntimeException("Error creating Tipo de Uva: " + e.getMessage(), e);
        }
    }

    @Override
    public TipoUva findById(Long id) {
        try {
            ENTITY_MANAGER.getTransaction().begin();
            TipoUva tipoUva = ENTITY_MANAGER.find(TipoUva.class, id);
            ENTITY_MANAGER.getTransaction().commit();

            return tipoUva;
        } catch (Exception e) {
            ENTITY_MANAGER.getTransaction().rollback();
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
