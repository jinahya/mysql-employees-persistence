package com.github.jinahya.mysql.employees.persistence;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

//@NamedQuery(
//        name ="Salary.selectLatestByEmpNo",
//        query = "SELECT e FROM Salary AS e WHERE e.empNo = (SELECT )"
//)
@IdClass(SalaryId.class)
@Entity
@Table(name = Salary.TABLE_NAME)
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@SuppressWarnings({
        "java:S1700" // ... salary;
})
public class Salary extends _BaseEntity<SalaryId> {

    @Serial
    private static final long serialVersionUID = 604718367871825963L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "salaries";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_EMP_NO = Employee.COLUMN_NAME_EMP_NO;

    // ---------------------------------------------------------------------------------------------------------- salary
    public static final String COLUMN_NAME_SALARY = "salary";

    // ------------------------------------------------------------------------------------------------------- from_date
    public static final String COLUMN_NAME_FROM_DATE = "from_date";

    // --------------------------------------------------------------------------------------------------------- to_date
    public static final String COLUMN_NAME_TO_DATE = "to_date";

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Salary that)) {
            return false;
        }
        return Objects.equals(empNo, that.empNo) &&
                Objects.equals(fromDate, that.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, fromDate);
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation
    //@AssertTrue
    private boolean isFromDateLessThanOrEqualToToDate() {
        if (fromDate == null || toDate == null) {
            return true;
        }
        return !fromDate.isAfter(toDate);
    }

    // -------------------------------------------------------------------------------------------------- empNo/employee
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(final Employee employee) {
        this.employee = employee;
        setEmpNo(
                Optional.ofNullable(this.employee)
                        .map(Employee::getEmpNo)
                        .orElse(null)
        );
    }

    // ---------------------------------------------------------------------------------------------------------- salary

    // -------------------------------------------------------------------------------------------------------- fromDate

    // ---------------------------------------------------------------------------------------------------------- toDate

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_EMP_NO, nullable = false, insertable = true, updatable = false)
    private Integer empNo;

    @Valid
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_EMP_NO, nullable = false, insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Employee employee;

    // -----------------------------------------------------------------------------------------------------------------
    //@Positive
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALARY, nullable = false, insertable = true, updatable = true)
    private Integer salary;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FROM_DATE, nullable = false, insertable = true, updatable = true)
    private LocalDate fromDate;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TO_DATE, nullable = false, insertable = true, updatable = true)
    private LocalDate toDate;
}
