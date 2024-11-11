package DAOs;

import Entidades.Maridaje;
import Util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MaridajeDAO implements DAO<Maridaje, Long> {

    private final EntityManager entityManager = EntityManagerUtil.getEntityManager();

    @Override
    public void create(Maridaje maridaje) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(maridaje);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error creating Maridaje: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Maridaje maridaje) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(maridaje);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Error updating Maridaje: " + e.getMessage());
        }

    }

    @Override
    public List<Maridaje> findAll() {
        try {
            entityManager.getTransaction().begin();

            List maridajes = entityManager.createQuery("SELECT m FROM Maridaje m").getResultList();

            entityManager.getTransaction().commit();
            return maridajes;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error finding Maridajes: " + e.getMessage(), e);
        }

    }

    @Override
    public Maridaje findById(Long id) {
        try {
            entityManager.getTransaction().begin();
            Maridaje maridaje = entityManager.find(Maridaje.class, id);
            entityManager.getTransaction().commit();

            return maridaje;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error No Maridaje ID: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            entityManager.getTransaction().begin();

            Maridaje maridaje = entityManager.find(Maridaje.class, id);

            if (maridaje != null) {
                entityManager.remove(maridaje);
            } else {
                throw new RuntimeException("Error! No Maridaje ID: " + id);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error deleting Maridaje: " + e.getMessage(), e);
        }
    }


    public static void main(String[] args) {

        MaridajeDAO dao = new MaridajeDAO();


        //Maridaje maridaje = dao.findById(3L);

        List<Maridaje> maridajes = dao.findAll();
        if (maridajes != null) {
            System.out.println(maridajes.toString());
        }else{
            System.out.println("No hay maridaje con ese ID");
        }
    }


}
