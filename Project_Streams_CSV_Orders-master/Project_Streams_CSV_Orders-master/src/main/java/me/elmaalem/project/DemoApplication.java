package me.elmaalem.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import me.elmaalem.project.repository.DBConfiguration;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		Connection conn = null;
		Statement stmt = null;
		String query;

		try {
			conn = DBConfiguration.getConnection();
			stmt = conn.createStatement();
			
			stmt.execute("drop table visits, patient2practitioner IF EXISTS");
			stmt.execute("drop table patients, practitioners IF EXISTS");

			query = "create table practitioners("
				+"practitionerId integer primary key,"
				+"specialization varchar(30))"
				+"as select practitionerId , specialization from "
				+"CSVREAD( 'C:/Users/Aneta/Desktop/Załącznik_zadanie_JB/csv/practitioners.csv', null, 'charset=UTF-8' )";
			stmt.execute(query);

			query ="create table patients("
				+"patientId integer primary key, "
				+"firstName varchar(20),"
				+"lastName varchar(20),"
				+"city  varchar(20), "
				+"createdAt  varchar(20) "
				+")"
				+"as select patientId, firstName, lastName, city, createdAt from "
				+"CSVREAD( 'C:/Users/Aneta/Desktop/Załącznik_zadanie_JB/csv/patients.csv', null, 'charset=UTF-8' )";
			stmt.execute(query);

			query = "create table visits("
				+"visitId integer primary key,"
				+"practitionerId integer,"
				+"patientId integer,"
				+"foreign key (practitionerId ) references practitioners(practitionerId ),"
				+"foreign key (patientId  ) references patients(patientId )"
				+")"
				+"as select visitId, practitionerId, patientId from "
				+"CSVREAD( 'C:/Users/Aneta/Desktop/Załącznik_zadanie_JB/csv/visits.csv', null, 'charset=UTF-8' )";
			stmt.execute(query);

			query = "create table patient2practitioner("
				+"patientId integer,"
				+"practitionerId integer,"
				+"foreign key (patientId  ) references patients(patientId),"
				+"foreign key (practitionerId ) references practitioners(practitionerId)"
				+")"
				+"as select patientId,practitionerId from "
				+"CSVREAD( 'C:/Users/Aneta/Desktop/Załącznik_zadanie_JB/csv/patient2practitioner.csv', null, 'charset=UTF-8' )";
			stmt.execute(query);

			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
