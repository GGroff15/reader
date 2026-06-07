package datastream.reader.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Entity
@Table
public class Subscription extends AbstractPersistable<UUID> {

    private String name;
    private String destination;

    protected Subscription() {
    }

    public Subscription(String name, String destination) {
        this.name = name;
        this.destination = destination;
    }

    public String name() {
        return name;
    }

    public String destination() {
        return destination;
    }
}