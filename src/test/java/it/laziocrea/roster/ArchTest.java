package it.laziocrea.roster;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("it.laziocrea.roster");

        noClasses()
            .that()
            .resideInAnyPackage("it.laziocrea.roster.service..")
            .or()
            .resideInAnyPackage("it.laziocrea.roster.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..it.laziocrea.roster.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
