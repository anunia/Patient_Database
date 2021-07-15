# Patient_Database

## Details
API returns informations about visit at the medical specialists. 

## Endpoint

### Request
`GET /patients/city/{cities}/specialization/{spec}`
where `{cities}` represents city for which we want to get data and `{spec}` is specialization that we want to search.
When `All` is passed to `{cities}` then response conteins results for all cities by analogy when `All` is passed to `{spec}`, then response conteins results for all specializations.


### Response
String containg information in the form of json.

## Example 
### Request
`GET /api/patients/city/Łódź/specialization/Pediatrics`

### Response
```
{"Patients":[{"CITY":"Łódź","LASTNAME":"Adamski","countVisits":99,"FIRSTNAME":"Paweł"}]}
```

## Example 
### Request
`GET /api/patients/city/All/specialization/Pediatrics`

### Response
```
{"Patients":[{"CITY":"Łódź","LASTNAME":"Adamski","countVisits":99,"FIRSTNAME":"Paweł"},{"CITY":"Radomsko","LASTNAME":"Krawczyk","countVisits":106,"FIRSTNAME":"Cyprian"},{"CITY":"Kutno","LASTNAME":"Gajewski","countVisits":117,"FIRSTNAME":"Janusz"}]}
```

## Endpoint

### Request
`GET /api/patients/city/{cities}`
where `{cities}` represents city for which we want to get data.

### Response
String containg information in the form of json.

## Example 
### Request
`GET api/patients/city/Łódź`

### Response
```
{"Patients":[{"CITY":"Łódź","LASTNAME":"Włodarczyk","countVisits":114,"FIRSTNAME":"Amir"},{"CITY":"Łódź","LASTNAME":"Adamski","countVisits":99,"FIRSTNAME":"Paweł"},{"CITY":"Łódź","LASTNAME":"Pietrzak","countVisits":125,"FIRSTNAME":"Albert"}]}
```

## Example 
### Request
`GET api/patients/city/All`

### Response
```
{"Patients":[{"CITY":"Łódź","LASTNAME":"Włodarczyk","countVisits":114,"FIRSTNAME":"Amir"},{"CITY":"Łódź","LASTNAME":"Adamski","countVisits":99,"FIRSTNAME":"Paweł"},{"CITY":"Radomsko","LASTNAME":"Krawczyk","countVisits":106,"FIRSTNAME":"Cyprian"},{"CITY":"Łódź","LASTNAME":"Pietrzak","countVisits":125,"FIRSTNAME":"Albert"},{"CITY":"Kutno","LASTNAME":"Kaczmarczyk","countVisits":109,"FIRSTNAME":"Robert"},{"CITY":"Radomsko","LASTNAME":"Mróz","countVisits":110,"FIRSTNAME":"Ludwik"},{"CITY":"Kutno","LASTNAME":"Gajewski","countVisits":117,"FIRSTNAME":"Janusz"},{"CITY":"Kutno","LASTNAME":"Zalewski","countVisits":116,"FIRSTNAME":"Lucjan"},{"CITY":"Radomsko","LASTNAME":"Szczepański","countVisits":104,"FIRSTNAME":"Korneliusz"}]}
```
