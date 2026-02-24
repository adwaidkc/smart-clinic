# Smart Clinic Management System - MySQL Schema Design

## Database: smartclinic

### Doctor Table
- id (BIGINT, Primary Key, Auto Increment)
- name (VARCHAR)
- speciality (VARCHAR)
- email (VARCHAR, Unique)
- password (VARCHAR)

### Patient Table
- id (BIGINT, Primary Key, Auto Increment)
- name (VARCHAR)
- email (VARCHAR, Unique)
- password (VARCHAR)
- phone (VARCHAR)

### Appointment Table
- id (BIGINT, Primary Key, Auto Increment)
- appointment_time (DATETIME)
- doctor_id (BIGINT, Foreign Key references Doctor(id))
- patient_id (BIGINT, Foreign Key references Patient(id))

### Prescription Table
- id (BIGINT, Primary Key, Auto Increment)
- medicine (VARCHAR)
- dosage (VARCHAR)
- doctor_id (BIGINT, Foreign Key references Doctor(id))
- patient_id (BIGINT, Foreign Key references Patient(id))

## Relationships
- One Doctor has many Appointments
- One Patient has many Appointments
- One Doctor has many Prescriptions
- One Patient has many Prescriptions