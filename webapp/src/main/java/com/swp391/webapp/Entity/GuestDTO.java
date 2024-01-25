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
@Table(name = "guest")
public class GuestDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guest_id", nullable = false)
    private Integer guest_ID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_ID", referencedColumnName = "account_id")
    private AccountDTO accountDTO;

    @Column(name = "name", nullable = false)
    private Integer name;

    @Column(name = "phone", nullable = false)
    private Integer phone;

    @Column(name = "gender", nullable = false)
    private Integer gender;

//    @Column(name = "picture", nullable = true)
//    private  guest_ID;



}
