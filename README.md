# Patient_Database

## Details
API returns informations about visit at the medical specialists. 

## Most important classes

#### Controller
https://github.com/anunia/Patient_Database/blob/main/Project_Streams_CSV_Orders-master/Project_Streams_CSV_Orders-master/src/main/java/me/elmaalem/project/controller/CSVController.java

#### Service
https://github.com/anunia/Patient_Database/blob/main/Project_Streams_CSV_Orders-master/Project_Streams_CSV_Orders-master/src/main/java/me/elmaalem/project/service/CSVService.java

#### Main and database creation
https://github.com/anunia/Patient_Database/blob/main/Project_Streams_CSV_Orders-master/Project_Streams_CSV_Orders-master/src/main/java/me/elmaalem/project/DemoApplication.java

## Endpoint

### Request
`GET /api/patients/city/{cities}/specialization/{spec}`
where `{cities}` represents city for which we want to get data and `{spec}` is specialization that we want to search.
When `All` is passed to `{cities}` then response conteins results for all cities by analogy when `All` is passed to `{spec}`, then response conteins results for all specializations.


### Response
String containg information in the form of json.

### Example 
#### Request
`GET /api/patients/city/Łódź/specialization/Pediatrics`

#### Response
```
{"Patients":[{"CITY":"Łódź","LASTNAME":"Adamski","countVisits":99,"FIRSTNAME":"Paweł"}]}
```

### Example 
#### Request
`GET /api/patients/city/All/specialization/Pediatrics`

#### Response
```
{"Patients":[{"CITY":"Łódź","LASTNAME":"Adamski","countVisits":99,"FIRSTNAME":"Paweł"},{"CITY":"Radomsko","LASTNAME":"Krawczyk","countVisits":106,"FIRSTNAME":"Cyprian"},{"CITY":"Kutno","LASTNAME":"Gajewski","countVisits":117,"FIRSTNAME":"Janusz"}]}
```
## Endpoint

### Request
`GET /api/visits/specialization/{spec}`
Response contains information number of all visits at specified specialist or all specializations.
where `{spec}` represents specialization for which we want to get data.

### Example 
#### Request
`GET /api/visits/specialization/Pediatrics`

#### Response
```
{"Patients":[{"numberOfVists":80,"SPECIALIZATION":"Pediatrics"}]}
```

## Endpoint

### Request
`GET /api/patients/city/{cities}`
where `{cities}` represents city for which we want to get data.

### Response
String formated like json containg information about firstName, lastName and nuber of visits for petients from specified city.

### Example 
#### Request
`GET api/patients/city/Łódź`

#### Response
```
{"Patients":[{"CITY":"Łódź","LASTNAME":"Włodarczyk","countVisits":114,"FIRSTNAME":"Amir"},{"CITY":"Łódź","LASTNAME":"Adamski","countVisits":99,"FIRSTNAME":"Paweł"},{"CITY":"Łódź","LASTNAME":"Pietrzak","countVisits":125,"FIRSTNAME":"Albert"}]}
```

## Endpoint

### Request
`GET /api/patients/specialization/{spec}`
where `{cities}` represents city for which we want to get data.

### Response
String formated like json containg information about firstName, lastName and nuber of visits for petients attending to the doctor with the specialization specified in `{spec}`.

### Example 
#### Request
`GET /api/patients/specialization/Dermatology`

#### Response
```
{"Patients":[{"CITY":"Łódź","LASTNAME":"Włodarczyk","countVisits":114,"FIRSTNAME":"Amir"},{"CITY":"Łódź","LASTNAME":"Adamski","countVisits":99,"FIRSTNAME":"Paweł"},{"CITY":"Łódź","LASTNAME":"Pietrzak","countVisits":125,"FIRSTNAME":"Albert"}]}
```

