package biz;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OneToOneOneWayClient {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter05");
		try {
			dataInsert(emf);
			dataSelect(emf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}
	}

	private static void dataSelect(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EmployeeCard employeeCard = em.find(EmployeeCard.class, 1L);
		System.out.println("검색된 사원증 번호 :" + employeeCard.getCardId());
		System.out.println("권한 :" + employeeCard.getRole());
		System.out.println("소유자 :" + employeeCard.getEmployee().getName());
	}

	private static void dataInsert(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Employee employee = new Employee();
		employee.setName("둘리");
		em.persist(employee);

		EmployeeCard employeeCard = new EmployeeCard();
		employeeCard.setExpireDate(LocalDateTime.now());
		employeeCard.setEmployee(employee);
		employeeCard.setRole("Master");

		employeeCard.setEmployee(employee);
		em.persist(employeeCard);

		em.getTransaction().commit();
		em.close();
	}
}
