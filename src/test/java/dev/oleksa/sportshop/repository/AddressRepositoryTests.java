package dev.oleksa.sportshop.repository;

import dev.oleksa.sportshop.model.user.address.Address;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AddressRepositoryTests {

    @Autowired
    private AddressRepository addressRepository;

    /**
     *
     */
    @Test
    public void AddressRepository_SavedAll_ReturnSavedAddress() {

        // Arrange
//        Address address = Address.builder()
//                .user()
//                .build();

        // Act


        // Assert


    }
}
