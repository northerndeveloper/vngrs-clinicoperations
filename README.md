# Clinic Operations Project

Clinic Operations Assignment was prepared for VNGRS company as an assignment.
It is a Spring Boot 2.4.2 application. No-SQL database(MongoDB) is used as database (spring-boot-starter-data-mongodb)
and Spring Rest Services are used for operations.

* You have to setup MongoDB at your machine in order to maintain a connection to MongoDB and execute
the application successfully.
  
Please execute following commands. 

brew tap mongodb/brew
brew install mongodb-community
brew services start mongodb-community

* You can start the application by executing main class ClinicOperationsApp or run button at your IDE to start the
application.
  
* Those are the following Rest Operations.

# GET PATIENTS 

LUNK :  http://localhost:8102/clinicoperations/appointments

Curl Command:
curl --location --request GET 'http://localhost:8102/clinicoperations/patients' \
--header 'Content-Type: application/json' \

# POST PATIENTS ( ADD PATIENTS )

Link :  http://localhost:8102/clinicoperations/patients

BODY (type JSON) : 

{
"name": "First Patient Name",
"surname": "First Patient Surname",
"citizenshipNumber": 10000001
}

Curl Command:

curl --location --request POST 'http://localhost:8102/clinicoperations/patients' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "First Patient Name",
"surname": "First Patient Surname",
"citizenshipNumber": 10000001
}'

# GET DOCTORS

Link : http://localhost:8102/clinicoperations/doctors

Curl Command :

curl --location --request GET 'http://localhost:8102/clinicoperations/doctors'

# POST DOCTORS ( ADD DOCTOR )

Link : http://localhost:8102/clinicoperations/doctors

BODY (type JSON) : 

{
"name": "First Doctor Name",
"surname": "First Doctor Surname",
"citizenshipNumber": 20000001,
"hourlyRate" : 150
}

Curl Command :

curl --location --request POST 'http://localhost:8102/clinicoperations/doctors' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "First Doctor Name",
"surname": "First Doctor Surname",
"citizenshipNumber": 20000001,
"hourlyRate" : 150
}'

# GET APPOINTMENTS

LINK :  http://localhost:8102/clinicoperations/appointments

Curl Command:

curl --location --request GET 'http://localhost:8102/clinicoperations/appointments'

# POST APPOINTMENT ( ADD APPOINTMENT )

LINK : http://localhost:8102/clinicoperations/appointments

BODY (type JSON) : 

{
    "doctorId" : "6027fef945a417bdda5379f3",
    "patientId" : "6027feee45a417bdda5379f2",
    "appointmentDate" : "2021-02-14T20:00+03:00",
    "lengthOfAppointment" : 10
}

Curl Command:

curl --location --request POST 'http://localhost:8102/clinicoperations/appointments' \
--header 'Content-Type: application/json' \
--data-raw '{

    "doctorId" : "6027fef945a417bdda5379f3",
    "patientId" : "6027feee45a417bdda5379f2",
    "appointmentDate" : "2021-02-14T20:00+03:00",
    "lengthOfAppointment" : 10
}
'

# CANCEL APPOINTMENT ( POST REQUEST)

Link : http://localhost:8102/clinicoperations/appointments/{appointment_ID}/cancel

Curl Command: 

curl --location --request POST 'http://localhost:8102/clinicoperations/appointments/60293ad2b135c01fb2d55f87/cancel'

# LIST TRANSACTIONS( GET REQUEST )

Link : http://localhost:8102/clinicoperations/transactions

Curl Command: 

curl --location --request GET 'http://localhost:8102/clinicoperations/transactions'





  


