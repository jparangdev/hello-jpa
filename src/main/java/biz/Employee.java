package biz;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "S_EMP")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(length = 25, nullable = false)
	private String name;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "DEPT_ID")
	private Department department;

	public void setDepartment(Department department) {
		this.department = department;
		department.getEmployeeList().add(this);
	}
	
}
