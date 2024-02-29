package com.github.jinahya.mysql.employees.persistence;

import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class DepartmentTest extends _BaseEntityTest<Department, String> {

    DepartmentTest() {
        super(Department.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    SingleTypeEqualsVerifierApi<Department> equals__(final SingleTypeEqualsVerifierApi<Department> verifierApi) {
        return super.equals__(verifierApi)
                .suppress(Warning.SURROGATE_KEY);
    }
}
