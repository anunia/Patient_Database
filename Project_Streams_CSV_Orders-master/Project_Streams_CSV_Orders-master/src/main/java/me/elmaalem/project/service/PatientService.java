package me.elmaalem.project.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.elmaalem.project.model.Patient;
import me.elmaalem.project.repository.PatientsRepo;
import me.elmaalem.project.service.PatientService;
 

@Service
public class PatientService {
    @Autowired
    private PatientsRepo patientsRepo;
    
    public void setPatientsRepo(PatientsRepo patientsRepo) {
     this.patientsRepo = patientsRepo;
    }
     
    public List<Patient> retrievePatients() {
     List<Patient> patients = patientsRepo.findAll();
     return patients;
    }
     
    public Patient getPatient(Long patientId) {
     Optional<Patient> optEmp = patientsRepo.findById(patientId);
     return optEmp.get();
    }
     
    public void savePatient(Patient patient){
     patientsRepo.save(patient);
    }
     
    public void deletePatient(Long patientId){
     patientsRepo.deleteById(patientId);
    }
     
    public void updatePatient(Patient patient) {
     patientsRepo.save(patient);
    }
}
