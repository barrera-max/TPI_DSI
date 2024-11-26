package DAOs;

import Entidades.Vino;
import Util.Conexion;
import jakarta.persistence.EntityManager;

import java.util.List;

public class VinoDAO implements DAO<Vino, Long>{


    private final EntityManager entityManager = Conexion.getInstancia().getEntityManager();

    @Override
    public void create(Vino vino) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(vino);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) { // Verifica si la transacción está activa
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error creating Vino: " + e.getMessage(), e);
        }
    }

    @Override
    public Vino findById(Long aLong) {
        return null;
    }

    @Override
    public void update(Vino vino) {

    }

    @Override
    public void delete(Long id) {
        try {
            entityManager.getTransaction().begin();

            Vino vino = entityManager.find(Vino.class, id);

            if (vino != null) {
                entityManager.remove(vino);
            } else {
                throw new RuntimeException("Error! No Vino ID: " + id);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error deleting Vino: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Vino> findAll() {
        try {
            entityManager.getTransaction().begin();

            List<Vino> vinos = entityManager.createQuery("SELECT v FROM Vino v").getResultList();

            entityManager.getTransaction().commit();
            return vinos;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error finding Vinos: " + e.getMessage(), e);
        }

    }

    public static void main(String[] args) {

        VinoDAO dao = new VinoDAO();


        //dao.delete(2L);

        List<Vino> vinos = dao.findAll();

        System.out.println(vinos.toString());

    }


}




