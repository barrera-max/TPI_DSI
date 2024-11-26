package DAOs;

import Entidades.Bodega;
import Entidades.Enofilo;
import Util.Conexion;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EnofiloDAO implements DAO<Enofilo, Long> {

    private final static EntityManager em = Conexion.getInstancia().getEntityManager();

    @Override
    public void create(Enofilo enofilo) {

    }

    @Override
    public Enofilo findById(Long aLong) {
        return null;
    }

    @Override
    public void update(Enofilo enofilo) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<Enofilo> findAll() {
        try {
            em.getTransaction().begin();

            List<Enofilo> enofilos = em.createQuery("SELECT e FROM Enofilo e").getResultList();

            em.getTransaction().commit();
            return enofilos;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error finding Enofilos: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        EnofiloDAO dao = new EnofiloDAO();

        List<Enofilo> enofilos = dao.findAll();

        Enofilo enofilo = enofilos.get(0);

        String st = enofilo.getNombre() + ' ' + enofilo.getSeguido().get(0).getBodega().getNombre();

        System.out.println(st);
    }


}
