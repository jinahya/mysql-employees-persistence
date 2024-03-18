package com.github.jinahya.mysql.employees.persistence.spring;

import com.github.jinahya.mysql.employees.persistence.Employee;
import com.github.jinahya.mysql.employees.persistence.Employee_;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class EmployeeRepository_FindAllBornInYear2_IT
        extends EmployeeRepository_FindAllBornInYear$_IT {

    private List<Integer> getBirthYearList() {
        return selectDistinctBirthYearList(entityManager(), 8);
    }

    @MethodSource({"getBirthYearList"})
    @ParameterizedTest
    void __(final int year) {
        // ------------------------------------------------------------------------------------------------------- given
        final var size = ThreadLocalRandom.current().nextInt(8) + 1;
        final var sort = Sort.by(Sort.Order.asc(Employee_.empNo.getName()));
        for (var pageable = PageRequest.of(0, size, sort); ; pageable = pageable.next()) {
            // ---------------------------------------------------------------------------------------------------- when
            final var all = repositoryInstance().findAllBornInYear2(year, pageable);
            log.debug("all.content.size: {}", all.getContent().size());
            all.forEach(e -> {
                log.debug("e: {}", e);
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(all).hasSizeLessThanOrEqualTo(size);
            assertThat(all.getContent()).isSortedAccordingTo(Comparator.comparing(Employee::getEmpNo));
            if (!all.hasNext() || pageable.getPageNumber() > 2) {
                break;
            }
        }
    }
}