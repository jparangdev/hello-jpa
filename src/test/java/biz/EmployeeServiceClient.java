package biz;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

public class EmployeeServiceClient {

	public static void main(String[] args) {
		EntityManagerFactory emf
			= Persistence.createEntityManagerFactory("Chapter03");

		EntityManager em = emf.createEntityManager();
		em.setFlushMode(FlushModeType.COMMIT);

		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			for (int i = 0; i < 5; i++) {
				Employee employee = new Employee();
				employee.setName("직원" + i);
				em.persist(employee);
			}
			tx.commit();

			String jpql = "SELECT e FROM Employee e ORDER BY e.Id DESC";
			List<Employee> employeeList = em.createQuery(jpql, Employee.class).getResultList();

			for (Employee employee : employeeList) {
				System.out.println("--> " + employee.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}
