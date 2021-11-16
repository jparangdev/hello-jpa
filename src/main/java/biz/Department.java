package biz;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "S_DEPT")
public class Department {
	@Id
	@GeneratedValue
	@Column(name = "DEPT_ID")
	private Long Id;

	@Column(length = 25, nullable = false)
	private String name;

	@OneToMany(mappedBy = "department")
	List<Employee> employeeList = new ArrayList<>();

}
