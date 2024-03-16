package com.github.jinahya.mysql.employees.persistence;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.util.List;
import java.util.Objects;

@NamedQuery(
        name = "Department.selectOneByDeptName",
        query = """
                SELECT e
                FROM Department AS e
                WHERE e.deptName = :deptName"""
)
@NamedQuery(
        name = "Department.selectAll",
        query = """
                SELECT e
                FROM Department AS e
                ORDER BY e.deptNo ASC"""
)
@Entity
@Table(name = Department.TABLE_NAME)
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Department extends _BaseEntity<String> {

    @Serial
    private static final long serialVersionUID = 3430343752363795141L;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "departments";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name the table column to which the {@link Department_#deptNo deptNo} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_DEPT_NO = "dept_no";

    public static final int COLUMN_LENGTH_DEPT_NO = 4;

    public static final String ATTRIBUTE_NAME_DEPT_NO = "deptNo";

    public static final int SIZE_MIN_DEPT_NO = COLUMN_LENGTH_DEPT_NO;

    public static final int SIZE_MAX_DEPT_NO = COLUMN_LENGTH_DEPT_NO;

    // ------------------------------------------------------------------------------------------------------ first_name
    public static final String COLUMN_NAME_DEPT_NAME = "dept_name";

    public static final int COLUMN_LENGTH_DEPT_NAME = 40;

    public static final int SIZE_MIN_DEPT_NAME = 0;

    public static final int SIZE_MAX_DEPT_NAME = COLUMN_LENGTH_DEPT_NAME;

    // ------------------------------------------------------------------------------------------ STATIC-FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Department that)) {
            return false;
        }
        return Objects.equals(deptNo, that.deptNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptNo);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Size(min = SIZE_MIN_DEPT_NO, max = SIZE_MAX_DEPT_NO)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_DEPT_NO, length = COLUMN_LENGTH_DEPT_NO, nullable = false, insertable = true,
            updatable = false)
    private String deptNo;

    @Size(min = SIZE_MIN_DEPT_NAME, max = SIZE_MAX_DEPT_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DEPT_NAME, length = COLUMN_LENGTH_DEPT_NAME, nullable = false)
    private String deptName;

    // -----------------------------------------------------------------------------------------------------------------

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = DeptEmp.TABLE_NAME,
               joinColumns = {
                       @JoinColumn(name = DeptEmp.COLUMN_NAME_DEPT_NO, referencedColumnName = COLUMN_NAME_DEPT_NO)
               },
               inverseJoinColumns = {
                       @JoinColumn(name = DeptEmp.COLUMN_NAME_EMP_NO,
                                   referencedColumnName = Employee.COLUMN_NAME_EMP_NO)
               }
    )
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<@Valid @NotNull Employee> employees;

    //    @OrderBy(DeptManager.ATTRIBUTE_NAME_FROM_DATE + " ASC")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = DeptManager.TABLE_NAME,
               joinColumns = {
                       @JoinColumn(name = DeptManager.COLUMN_NAME_DEPT_NO, referencedColumnName = COLUMN_NAME_DEPT_NO)
               },
               inverseJoinColumns = {
                       @JoinColumn(name = DeptManager.COLUMN_NAME_EMP_NO,
                                   referencedColumnName = Employee.COLUMN_NAME_EMP_NO)
               }
    )
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<@Valid @NotNull Employee> managers;
}
