package com.swp391.webapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "host")
public class HostDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "host_id", nullable = false)
    private Integer host_ID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_ID", referencedColumnName = "account_id")
    private AccountDTO accountDTO;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

//    @Column(name = "picture", nullable = true)
//    private  guest_ID;



}
