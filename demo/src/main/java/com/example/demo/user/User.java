package com.example.demo.user;

import com.example.demo.booking.Booking;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

  @Id
  @SequenceGenerator(
    name = "user_sequence",
    sequenceName = "user_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "user_sequence"
  )
  private Long id;

  private String name;

  @Column(unique = true)
  private String phoneNumber;

  @Column(unique = true)
  private String email;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false)
  private Date createdAt;

  private String password;

  @PrePersist
  protected void onCreate() {
    createdAt = new Date();
  }

  public Long getBalance() {
    return balance;
  }

  public void setBalance(Long balance) {
    this.balance = balance;
  }

  private Long balance;

  @OneToMany
  @JoinColumn(name = "userId", referencedColumnName = "id")
  private List<Booking> bookings;

  public User() {}

  public User(
    String name,
    String email,
    String phoneNumber,
    String password,
    Long balance
  ) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.balance = balance;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
