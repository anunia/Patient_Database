package me.elmaalem.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import me.elmaalem.project.service.CSVService;

@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/api")
public class CSVController {

    @Autowired
    CSVService fileService;

    //
    @GetMapping(value = "/patients/city/{cities}")
    public ResponseEntity<String> getAllPatientsByCity (@PathVariable String[] cities) {

        try {
            String patients = fileService.getAllPatientsByCity(cities);

            if (patients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<String>(patients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(value = "/patients/specialization/{spec}")
    public ResponseEntity<String> getAllPatientsBySpec (@PathVariable String[] spec) {
    
        try {
            String patients = fileService.getAllPatientsBySpec(spec);

            if (patients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<String>(patients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        
        //
        @GetMapping(value = "/patients/city/{cities}/specialization/{spec}")
        public ResponseEntity<String> getCountByCityAndSpec (@PathVariable String[] cities, @PathVariable String[] spec) {
    
            try {
                String patients = fileService.getCountByCityAndSpec(cities, spec);
    
                if (patients.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<String>(patients, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping(value = "/visits/specialization/{spec}")
        public ResponseEntity<String> getCountForSpec (@PathVariable String[] spec) {
    
            try {
                String patients = fileService.getCountForSpec(spec);
    
                if (patients.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
    
                return new ResponseEntity<String>(patients, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


    //
    @GetMapping("/patiens")
    public ResponseEntity<String> getAllPatients () {
        try {
            String patients = fileService.getAllPatients();

            if (patients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<String>(patients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
