package com.github.jinahya.mysql.employees.persistence;

import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class DeptEmpIdTest extends BaseIdTest<DeptEmpId> {

    DeptEmpIdTest() {
        super(DeptEmpId.class);
    }

    @Override
    SingleTypeEqualsVerifierApi<DeptEmpId> equals__(final SingleTypeEqualsVerifierApi<DeptEmpId> verifierApi) {
        return super.equals__(verifierApi)
                .suppress(Warning.NONFINAL_FIELDS);
    }
}
