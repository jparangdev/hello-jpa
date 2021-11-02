package biz;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeeServiceClient {

	public static void main(String[] args) {
		EntityManagerFactory emf
			= Persistence.createEntityManagerFactory("Chapter02");

		EntityManager em = emf.createEntityManager();

		try  {
			Employee employee = new Employee();
			employee.setId(1);
			employee.setName("둘리");
			employee.setMailId("gurum");
			employee.setStartDate(new Date());
			employee.setTitle("과장");
			employee.setDeptName("총무부");
			employee.setSalary(2500.00);
			employee.setCommissionPct(12.50);

			em.persist(employee);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
}
