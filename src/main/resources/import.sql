
INSERT INTO health_facility (id, address, name) VALUES (nextval('hibernate_sequence'), 'Via dell''Amba Aradam 9, 00184 Roma', 'Ospedale San Giovanni Addolorata');
INSERT INTO health_facility (id, address, name) VALUES (nextval('hibernate_sequence'), 'Largo A. Gemelli 8, 00168 Roma', 'Policlinico Universitario A. Gemelli');
INSERT INTO health_facility (id, address, name) VALUES (nextval('hibernate_sequence'), 'Viale del Policlinico 155, 00161 Roma', 'Policlinico Umberto I');
INSERT INTO health_facility (id, address, name) VALUES (nextval('hibernate_sequence'), 'Via dei Monti Tiburtini 385, 00157 Roma', 'Ospedale Sandro Pertini');
INSERT INTO health_facility (id, address, name) VALUES (nextval('hibernate_sequence'), 'Piazza di Sant''Onofrio 4, 00165 Roma', 'Ospedale Pediatrico Bambino Gesù');

INSERT INTO specialization (id, name) VALUES (1, 'Cardiologia');
INSERT INTO specialization (id, name) VALUES (2, 'Dermatologia');
INSERT INTO specialization (id, name) VALUES (3, 'Ortopedia');
INSERT INTO specialization (id, name) VALUES (4, 'Neurologia');
INSERT INTO specialization (id, name) VALUES (5, 'Ginecologia');
INSERT INTO specialization (id, name) VALUES (6, 'Oculistica');
INSERT INTO specialization (id, name) VALUES (7, 'Psichiatria');

CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

-- Cardiologia
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'), 'Elettrocardiogramma (ECG)', 1);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'), 'Holter cardiaco (24h)', 1);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'), 'Test da sforzo', 1);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'), 'Visita cardiologica', 1);

-- Dermatologia
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Visita dermatologica', 2);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Mappatura nei', 2);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Crioterapia', 2);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Biopsia cutanea', 2);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Dermatoscopia', 2);

-- Ortopedia
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Visita ortopedica', 3);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Infiltrazione articolare', 3);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence')'Controllo post-operatorio', 3);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Radiografia', 3);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Fisioterapia ortopedica', 3);

-- Neurologia
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Visita neurologica', 4);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Elettroencefalogramma (EEG)', 4);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Potenziali evocati', 4);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'EMG (elettromiografia)', 4);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Test del liquido cerebrospinale', 4);

-- Ginecologia
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Visita ginecologica', 5);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Pap Test', 5);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Ecografia transvaginale', 5);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Colposcopia', 5);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Screening HPV', 5);

-- Oculistica
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Visita oculistica', 6);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Tonometri a contatto', 6);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Campo visivo', 6);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Topografia corneale', 6);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Esame funduscopico', 6);

-- Psichiatria
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Visita psichiatrica', 7);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Colloquio psicologico', 7);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Test neuropsicologici', 7);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Terapia di gruppo', 7);
INSERT INTO visit_type (id, name, specialization_id) VALUES (nextval('hibernate_sequence'),'Terapia cognitivo-comportamentale', 7);
