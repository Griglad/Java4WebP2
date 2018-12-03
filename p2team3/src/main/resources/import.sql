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
insert into appointment values(1, '2019-01-23 16:07', 'Στεφανιαία νόσος της καρδίας', 'Ισχαιμική πάθηση', 1, 2);

#2Apmt
insert into appointment values(2, '2018-12-22 11:07', 'Αγγειακή Άννοια', 'Εξέταση', 5, 2);

#3Apmt
insert into appointment values(3, '2018-12-12 19:20', 'Νόσος των αρτηριών', 'Εγκεφαλικό επεισόδιο', 1, 2);

#4Apmt
insert into appointment values(4, '2018-11-27 08:07', 'Αγγειακή Άννοια', 'Συνταγογράφηση φαρμάκων', 5, 2);

#5Apmt
insert into appointment values(5, '2018-12-21 08:14', 'Περιφερική αρτηριακή νόσος', 'Εξέταση', 1, 3);

#6Apmt
insert into appointment values(6, '2018-12-19 19:25', 'Ρευματική νόσος της καρδίας', 'Ρευματική καρδίτιδα', 3, 4);

#7Apmt
insert into appointment values(7, '2019-01-10 13:00', 'Μυοκαρδίτιδα', 'Εξέταση', 1, 2);

#8Apmt
insert into appointment values(8, '2018-12-12 19:20', 'Δερματίτιδα', 'Επείγον', 2, 2);

#9Apmt
insert into appointment values(9, '2019-12-15 09:20', 'Πόνος στο στήθος','Επείγον', 2, 1);

#10Apmt
insert into appointment values (10, '2019-01-14 07:30','Ταχυκαρδία','Αγχος',2,1);

#11Apmt
insert into appointment values(11,'2018-12-20 17:50','Νευρα άνευ αιτίας','Νέυρα',2,3);

#12Αptm
insert into appointment values(12, '2019-01-15 19:20', 'Αρτηριοσκλήρυνση', 'Ενοχλήσεις', 5, 2);

#13Aptm
insert into appointment values(13, '2020-01-15 16:40', 'Καρδιολογικος έλεγχος','check up',2,1);

#14Aptm
insert into appointment values(14, '2018-12-30 19:20', 'Ταχυκαρδία', 'Καρδιογράφημα', 2, 4);

#15Aptm
insert into appointment values(15, '2020-01-05 09:30', 'Περιφεριακή αρτηριακή νόσος','Προληπτικός ελεγχος',2,1);

#16Aptm
insert into appointment values(16,'2019-03-05 16:30','Μυοκαρδίτιδα','Ελεγχος',2,2);

#17Aptm
insert into appointment values(17,'2019-03-05 07:30','Ρευματική νόσος της καρδιάς','Ελεγχος',2,2);

#18Aptm
insert into appointment values(18,'2019-04-15 08:25','Εξανθήματα','Ελεγχος',2,2);

#19Aptm
insert into appointment values(19,'2020-03-05 07:30','Αφαίρεση ελιάς','Επανέλεγχος',4,2);

#20Aptm
insert into appointment values(20, '2018-12-12 19:20', 'Μυοκαρδίτιδα', 'Πόνος στη καρδιά', 1, 2);

#21Aptm
insert into appointment values(21, '2018-12-18 19:20', 'Νόσος των αρτηριών', 'Εγκεφαλικό επεισόδιο', 1, 2);

#21Aptm
insert into appointment values(22, '2018-12-22 19:20', 'Νόσος των αρτηριών', 'Εγκεφαλικό επεισόδιο', 1, 3);

#22Aptm
insert into appointment values(23, '2018-12-20 19:20', 'Καρδιοπάθεια', 'Επείγον', 1, 4);

#22Apthm
insert into appointment values(24, '2018-12-11 19:20', 'Ταχυκαρδία', 'Ζάλη', 1, 3);

#23Aptm
insert into appointment values(25, '2018-12-12 19:20', 'Νόσος των αρτηριών', 'Εγκεφαλικό επεισόδιο', 1, 2);

#24Aptm
insert into appointment values(26, '2020-12-13 13:20', 'Εντονος πόνος', 'Αμεση επίσκεψη', 1, 3);

#24Aptm
insert into appointment values(27, '2018-12-30 19:20', 'Καρδιοπάθεια', 'Επανέλεγχος', 1, 4);

#25Aptm
insert into appointment values (28, '2019-07-30 13:40', 'Νευρίτιδα','Ελεγχος',5,2)




























