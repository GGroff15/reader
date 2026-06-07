package datastream.reader.persistence;

import datastream.reader.PostgresTestcontainersConfiguration;
import datastream.reader.TestContainers;
import datastream.reader.model.Subscription;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(PostgresTestcontainersConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SubscriptionsIT implements TestContainers {

    @Autowired
    private Subscriptions subscriptions;

    @Test
    @DisplayName("Should find subscription by name")
    void shouldFindSubscriptionByName() {
        Optional<Subscription> approval = subscriptions.findFirstByName("approval");
        assertTrue(approval.isPresent());
    }
}