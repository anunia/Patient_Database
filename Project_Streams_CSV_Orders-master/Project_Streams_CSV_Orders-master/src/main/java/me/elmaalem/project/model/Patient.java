package me.elmaalem.project.model;

import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="EMPLOYEE")
public class Patient {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="patientId")
        private long patientId;

        @Column(name="firstName")
        private String firstName;
        
        @Column(name="lastName")
        private String lastName;
                
        @Column(name="city")
        private String city;
                
        @Column(name="createdAt")
        private String createdAt;
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");

	public Patient(long patientId, String firstName, String lastName, String city, String createdAt) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.createdAt = createdAt;
	}

	public String getLastName() {
		return lastName;
        }
        
        public Long getpatientId() {
                return patientId;
        }
        
        public void setpatientId(Long id) {
                this.patientId = id;
        }
        
        public String getFirstName() {
                return firstName;
        }
        
        public String getLastName(String name) {
                return  lastName;
        }
        
        public String getCity() {
        return city;
        }
        
        public String getCreatedAt() {
        return createdAt;
        }
        

}
