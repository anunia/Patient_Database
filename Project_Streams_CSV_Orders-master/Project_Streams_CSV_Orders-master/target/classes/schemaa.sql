drop table patients IF EXISTS;
create table patients(
patientId integer primary key,
firstName varchar(20),
lastName varchar(20),
city  varchar(20),
createdAt  varchar(20)
)
as select patientId, firstName, lastName, city, createdAt from CSVREAD( 'C:/Users/Aneta/Desktop/Załącznik_zadanie_JB/csv/patients.csv', null, 'charset=UTF-8' );

--select * from patients;

drop table practitioners IF EXISTS;
create table practitioners(
practitionerId integer primary key,
specialization varchar(30)
)
as select practitionerId , specialization from CSVREAD( 'C:/Users/Aneta/Desktop/Załącznik_zadanie_JB/csv/practitioners.csv', null, 'charset=UTF-8' );

--select * from practitioners;

drop table visits IF EXISTS;
create table visits(
visitId integer primary key,
practitionerId integer,
patientId integer,
foreign key (practitionerId ) references practitioners(practitionerId ),
foreign key (patientId  ) references patients(patientId )
)
as select visitId, practitionerId, patientId from CSVREAD( 'C:/Users/Aneta/Desktop/Załącznik_zadanie_JB/csv/visits.csv', null, 'charset=UTF-8' );

--select * from visits;


drop table patient2practitioner IF EXISTS;
create table patient2practitioner(
patientId integer,
practitionerId integer,
foreign key (patientId  ) references patients(patientId),
foreign key (practitionerId ) references practitioners(practitionerId)
)
as select patientId,practitionerId from CSVREAD( 'C:/Users/Aneta/Desktop/Załącznik_zadanie_JB/csv/patient2practitioner.csv', null, 'charset=UTF-8' );

--select * from patient2practitioner;

--select patientId,practitionerId from CSVREAD( 'C:/Users/Aneta/Desktop/Załącznik_zadanie_JB/csv/patient2practitioner.csv', null, 'charset=UTF-8' );