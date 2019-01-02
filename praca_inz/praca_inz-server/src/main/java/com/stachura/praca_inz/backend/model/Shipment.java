package com.stachura.praca_inz.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stachura.praca_inz.backend.model.enums.Status;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.model.security.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
@EnableAutoConfiguration
@Table(name = "SHIPMENT")
@Getter
@Setter
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "SHIPMENT_NUMBER", nullable = false)
    private String deliveryNumber;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Warehouse senderWarehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Warehouse recieverWarehouse;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SHIPMENT_DEVICES", joinColumns = @JoinColumn(name = "SHIPMENT_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "DEVICE_ID", referencedColumnName = "ID"))
    @OrderBy
    @JsonIgnore
    private Collection<Device> devices;

    @Basic
    @Column(name = "SHIPMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date utilTimestamp;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;
}