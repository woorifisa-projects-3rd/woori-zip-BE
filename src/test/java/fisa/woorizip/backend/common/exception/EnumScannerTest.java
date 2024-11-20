package fisa.woorizip.backend.common.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.Set;

class EnumScannerTest {

    @Test
    void Enum_클래스를_찾을_수_있다() {
        Set<String> enumClasses = EnumScanner.getEnumClasses();
        assertThat(enumClasses)
                .contains(
                        "Role",
                        "Category",
                        "HouseType",
                        "LoanGoodsType",
                        "HousingExpenses",
                        "EarningsType");
    }
}
