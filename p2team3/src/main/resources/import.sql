#Specialties

#1specialty
insert into specialty values(1,'Καρδιολόγος');

#2specialty
insert into specialty values(2,'Δερματολόγος');

#3specialty
insert into specialty values(3,'Διαβητολόγος');

#4specialty
insert into specialty values(4,'Αναισθησιολόγος');

#5specialty
insert into specialty values(5,'Νευρολόγος');


#Doctors

#1Doc
insert into doctor values(1,'Κωνσταντίνος','Παπαδόπουλος','$2a$10$P7G7HgWgtBcZFgfzUtINB.xus.y64lZAWhSv3DuYyOV.9283bW.5K','kpapas',1);

#2Doc
insert into doctor values(2,'Δημήτρης','Αντωνίου','$2a$10$P7G7HgWgtBcZFgfzUtINB.xus.y64lZAWhSv3DuYyOV.9283bW.5K','dimant',2);

#3Doc
insert into doctor values(3,'Γρηγόρης','Μαρκόπουλος','$2a$10$P7G7HgWgtBcZFgfzUtINB.xus.y64lZAWhSv3DuYyOV.9283bW.5K','grigmark',1);

#4Doc
insert into doctor values(4,'Σταμάτιος','Πέτρου','$2a$10$P7G7HgWgtBcZFgfzUtINB.xus.y64lZAWhSv3DuYyOV.9283bW.5K','stampe',4);

#5Doc
insert into doctor values(5,'Χαράλαμπος','Σπυρόπουλος','$2a$10$P7G7HgWgtBcZFgfzUtINB.xus.y64lZAWhSv3DuYyOV.9283bW.5K','xarspi',5);

#Patients

#1Pat
insert into patient values(1, '13039125652', 'tomKuriakou@gmail.com', 'Θωμάς', 'Κυριάκου', '6936251465', '$2a$10$P7G7HgWgtBcZFgfzUtINB.xus.y64lZAWhSv3DuYyOV.9283bW.5K', 'tomkuriakou');

#2Pat
insert into patient values(2, '25089673692', 'johnGian@gmail.com', 'Ιωάννης', 'Γιαννόπουλος', '6981425196', '$2a$10$P7G7HgWgtBcZFgfzUtINB.xus.y64lZAWhSv3DuYyOV.9283bW.5K', 'johnjohn');

#3Pat
insert into patient values(3, '11079274125', 'athanren@gmail.com', 'Αθανάσιος', 'Ρεντίδης', '6987546556','$2a$10$P7G7HgWgtBcZFgfzUtINB.xus.y64lZAWhSv3DuYyOV.9283bW.5K', 'athanren');

#4Pat
insert into patient values(4, '12075686578', 'pavkamp@gmail.com', 'Παύλος', 'Καμπίδης', '6958745854','$2a$10$P7G7HgWgtBcZFgfzUtINB.xus.y64lZAWhSv3DuYyOV.9283bW.5K', 'pavkamp');

#Appointments

#1Apmt
insert into appointment values(1, '2019-01-23 16:07', 'Στεφανιαία νόσος της καρδίας', 'Ισχαιμική πάθηση', 1, 1)

#2Apmt
insert into appointment values(2, '2018-12-22 11:07', 'Αγγειακή Άννοια', 'Εξέταση', 5, 2)

#3Apmt
insert into appointment values(3, '2018-12-12 19:20', 'Νόσος των αρτηριών', 'Εγκεφαλικό επεισόδιο', 1, 2)

#4Apmt
insert into appointment values(4, '2018-11-27 08:07', 'Αγγειακή Άννοια', 'Συνταγογράφηση φαρμάκων', 5, 2)

#5Apmt
insert into appointment values(5, '2018-12-21 08:14', 'Περιφερική αρτηριακή νόσος', 'Πόδια, χέρια.', 1, 3)

#6Apmt
insert into appointment values(6, '2018-12-19 19:25', 'Ρευματική νόσος της καρδίας', 'Ρευματική καρδίτιδα', 3, 4)

#7Apmt
insert into appointment values(7, '2019-01-10 13:00', 'Αφαίρεση ελιάς', 'Εξέταση', 4, 2)