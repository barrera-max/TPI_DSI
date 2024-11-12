package DAOs;

import Entidades.Vino;
import Util.Conexion;
import jakarta.persistence.EntityManager;

import java.util.List;

public class VinoDAO implements DAO<Vino, Long>{


    private final EntityManager em = Conexion.getInstancia().getEntityManager();

    @Override
    public void create(Vino vino) {
        try {
            em.getTransaction().begin();
            em.persist(vino);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) { // Verifica si la transacción está activa
                em.getTransaction().rollback();
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
    public void delete(Long aLong) {

    }

    @Override
    public List<Vino> findAll() {
        try {
            em.getTransaction().begin();

            List<Vino> vinos = em.createQuery("SELECT v FROM Vino v").getResultList();

            em.getTransaction().commit();
            return vinos;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error finding Vinos: " + e.getMessage(), e);
        }

    }

    public static void main(String[] args) {

        VinoDAO dao = new VinoDAO();


        List<Vino> vinos = dao.findAll();

        System.out.println(vinos.toString());

    }


}




