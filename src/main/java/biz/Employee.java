package biz;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "S_EMP", uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME", "MAILID"})})
// @NoArgsConstructor
// @AllArgsConstructor
@ToString(exclude = {"searchCondition","searchKeyword"})
@Access(AccessType.FIELD)
public class Employee {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 7, nullable = false)
	private long id;

	@Column(length = 25, nullable = false)
	private String name;

	@Column(length = 8, unique = true)
	private String mailId;

	@Column(name = "START_DATE", insertable = true)
	// @Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(length = 25)
	private String title;

	@Column(name = "DEPT_NAME", length = 30)
	private String deptName;

	@Column(precision = 11, scale = 2)
	private double salary;

	@Column(name = "COMMISSION_PCT", precision = 4, scale = 2,
		columnDefinition = "double CHECK (commission_pct IN (10, 12.5, 15, 17.5, 20))")
	private double commissionPct;

	@Transient
	private String searchCondition;

	@Transient
	private String searchKeyword;

}
