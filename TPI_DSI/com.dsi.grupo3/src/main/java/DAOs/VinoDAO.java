package DAOs;

import Entidades.Vino;
import Util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public class VinoDAO implements DAO<Vino, Long>{


    private final EntityManager em = EntityManagerUtil.getEntityManager();

    @Override
    public void create(Vino vino) {

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




