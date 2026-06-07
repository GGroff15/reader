package datastream.reader.persistence;

import datastream.reader.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Subscriptions extends JpaRepository<Subscription, UUID> {
    Optional<Subscription> findFirstByName(String name);
}