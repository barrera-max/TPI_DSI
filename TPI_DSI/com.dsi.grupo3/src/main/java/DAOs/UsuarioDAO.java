package DAOs;

import Entidades.TipoUva;
import Entidades.Usuario;
import Entidades.Vino;
import Util.Conexion;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UsuarioDAO implements DAO<Usuario,Long>{


    private final EntityManager em = Conexion.getInstancia().getEntityManager();


    @Override
    public void create(Usuario usuario) {
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) { // Verifica si la transacción está activa
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error creating Usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public Usuario findById(Long id) {
        try {
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, id);
            em.getTransaction().commit();

            return usuario;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error No usuario ID: " + e.getMessage());
        }    }

    @Override
    public void update(Usuario usuario) {

    }

    @Override
    public void delete(Long id) {
        try {
            em.getTransaction().begin();

            Usuario usuario = em.find(Usuario.class, id);

            if (usuario != null) {
                em.remove(usuario);
            } else {
                throw new RuntimeException("Error! No Usuario ID: " + id);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error deleting Usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Usuario> findAll() {
        try {
            em.getTransaction().begin();

            List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u").getResultList();

            em.getTransaction().commit();
            return usuarios;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error finding Usuarios: " + e.getMessage(), e);
        }
    }
}
