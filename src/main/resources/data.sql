insert into hospital (id, name, description) values 
(1, 'Metro Hospital', 'Metro City main hospital'),
(2, 'Cathy Memorial Hospital', 'Off maxwell rd'),
(3, 'Capital area Hospital', 'near downtown capital area');

insert into provider (id, first_name, last_name) values 
(1, 'Martin', 'Andrew'),
(2, 'Steven', 'Strange'),
(3, 'Jon', 'Who');

insert into patient (id, first_name, last_name) values 
(1, 'Aaron', 'Andrew'),
(2, 'Steven', 'Grey'),
(3, 'Jon', 'Snow');

insert into hospital_provider (id, hospital_id, provider_id)  values
(1, 1, 1),
(2, 2, 2),
(3, 3, 3);

insert into patient_provider (patient_id, hospital_provider_id)  values
(1, 1),
(2, 2),
(3, 3);