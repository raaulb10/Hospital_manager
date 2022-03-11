insert into unitati(denumire, oras)
values("Regina Maria", "Cluj-Napoca");
insert into unitati(denumire, oras)
values("Medica", "Cluj-Napoca");
insert into unitati(denumire, oras)
values("Vitaplus", "Cluj-Napoca");
insert into unitati(denumire, oras)
values("Medicover", "Cluj-Napoca");

insert into cabinete(id_cabinet,id_unitate)
values(1,1);
insert into cabinete(id_cabinet,id_unitate)
values(2,1);
insert into cabinete(id_cabinet,id_unitate)
values(3,1);

insert into cabinete(id_cabinet,id_unitate)
values(1,2);
insert into cabinete(id_cabinet,id_unitate)
values(2,2);
insert into cabinete(id_cabinet,id_unitate)
values(3,2);
insert into cabinete(id_cabinet,id_unitate)
values(4,2);

insert into cabinete(id_cabinet,id_unitate)
values(1,3);
insert into cabinete(id_cabinet,id_unitate)
values(2,3);
insert into cabinete(id_cabinet,id_unitate)
values(3,3);
insert into cabinete(id_cabinet,id_unitate)
values(4,3);
insert into cabinete(id_cabinet,id_unitate)
values(5,3);

insert into cabinete(id_cabinet,id_unitate)
values(1,4);
insert into cabinete(id_cabinet,id_unitate)
values(2,4);

insert into servicii(id_serviciu, nume, descriere, specialitate, competenta_necesara, pret, durata)
values("1", "Ecografie", "Vizualizare structuri anatomice cu ajutorul ultrasunetelor", "Imagistica", "ecografie", 150, 25);
insert into servicii(id_serviciu, nume, descriere, specialitate, competenta_necesara, pret, durata)
values("2", "Endoscopie", "Prevenirea si tratarea afectiunilor sistemului digestiv", "Interne", "endoscopie_digestiva", 500, 45);
insert into servicii(id_serviciu, nume, descriere, specialitate, competenta_necesara, pret, durata)
values("3", "Ecocardiografie", "Urmarirea si diagnosticarea bolilor de inima", "Interne", "ecocardiografie", 200, 20);
insert into servicii(id_serviciu, nume, descriere, specialitate, competenta_necesara, pret, durata)
values("4", "Coronarografie", "Identificarea obstacolelor aflate in calea curgerii normale a sangelui", "Interne", "cardiologie_interventionala", 300, 30);
insert into servicii(id_serviciu, nume, descriere, specialitate, competenta_necesara, pret, durata)
values("5", "Brohnoscopie", "Diagnosticarea afectiunilor cailor respiratorii", "Interne", "bronhoscopie", 500, 45);
insert into servicii(id_serviciu, nume, descriere, specialitate, competenta_necesara, pret, durata)
values("6", "Electroencefalograma", "Inregistrarea activitatii cerebrale", "Interne", "EEG", 550, 25);
insert into servicii(id_serviciu, nume, descriere, specialitate, competenta_necesara, pret, durata)
values("7", "Electromiografie", "Evaluarea nervilor periferici", "Interne", "EMG", 550, 33);
insert into servicii(id_serviciu, nume, descriere, specialitate, competenta_necesara, pret, durata)
values("8", "Dializa", "Substituie functiile de baza ale rinichiului", "Interne", "dializa", 90, 20);
insert into servicii(id_serviciu, nume, descriere, specialitate, competenta_necesara, pret, durata)
values("9", "Laparoscopie", "Operatie la nivelul organelor abdominale", "Chirurgie", "chirurgie_laparoscopica", 1000, 40);
insert into servicii(id_serviciu, nume, descriere, specialitate, competenta_necesara, pret, durata)
values("10", "Pneumonectomie", "Tratarea afectiunilor pulmonare canceroase", "Chirurgie", "chirurgie_toracica", 1100, 30);
insert into servicii(id_serviciu, nume, descriere, specialitate, competenta_necesara, pret, durata)
values("11", "Consultatie Coloana", "Tratarea herniei de disc", "Chirurgie", "chirurgie_spinala", 1300, 25);
insert into servicii(id_serviciu, nume, descriere, specialitate, competenta_necesara, pret, durata)
values("12", "Litortitie", "Tratarea pietrelor la rinichi", "Interne", "litotritie_extracorporeala", 500, 20);
insert into servicii(id_serviciu, nume, descriere, specialitate, competenta_necesara, pret, durata)
values("13", "Tomografie", "O metoda de diagnostic cu raze X", "Imagistica", "explorare_computer_tomograf", 150, 25);
insert into servicii(id_serviciu, nume, descriere, specialitate, competenta_necesara, pret, durata)
values("14", "RMN", "Diagnosticarea bolilor de la nivelul creierului", "Imagistica", "imagistica_prin_rezonanta_magnetica", 180, 25);

insert into servicii_oferite(id_unitate, id_serviciu)
values(1, "1");
insert into servicii_oferite(id_unitate, id_serviciu)
values(1, "2");
insert into servicii_oferite(id_unitate, id_serviciu)
values(1, "3");
insert into servicii_oferite(id_unitate, id_serviciu)
values(2, "4");
insert into servicii_oferite(id_unitate, id_serviciu)
values(2, "5");
insert into servicii_oferite(id_unitate, id_serviciu)
values(2, "6");
insert into servicii_oferite(id_unitate, id_serviciu)
values(2, "7");
insert into servicii_oferite(id_unitate, id_serviciu)
values(3, "8");
insert into servicii_oferite(id_unitate, id_serviciu)
values(3, "9");
insert into servicii_oferite(id_unitate, id_serviciu)
values(3, "10");
insert into servicii_oferite(id_unitate, id_serviciu)
values(4, "11");
insert into servicii_oferite(id_unitate, id_serviciu)
values(4, "12");
insert into servicii_oferite(id_unitate, id_serviciu)
values(4, "13");
insert into servicii_oferite(id_unitate, id_serviciu)
values(4, "14");

insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(1, 'Luni', 8, 23);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(1, 'Marti', 8, 22);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(1, 'Miercuri', 8, 22);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(1, 'Joi', 9, 20);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(1, 'Vineri', 9, 20);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(1, 'Sambata', 0, 0);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(1, 'Duminica', 0, 0);

insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(2, 'Luni', 12, 22);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(2, 'Marti', 7, 19);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(2, 'Miercuri', 7, 19);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(2, 'Joi', 7, 19);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(2, 'Vineri', 7, 17);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(2, 'Sambata', 0, 0);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(2, 'Duminica', 0, 0);

insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(3, 'Luni', 10, 21);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(3, 'Marti', 10, 21);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(3, 'Miercuri', 10, 21);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(3, 'Joi', 9, 21);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(3, 'Vineri', 8, 20);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(3, 'Sambata', 0, 0);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(3, 'Duminica', 0, 0);

insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(4, 'Luni', 10, 22);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(4, 'Marti', 6, 22);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(4, 'Miercuri', 6, 23);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(4, 'Joi', 6, 23);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(4, 'Vineri', 6, 20);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(4, 'Sambata', 0, 0);
insert into program_unitati(id_unitate, zi_calendaristica, ora_deschidere, ora_inchidere)
values(4, 'Duminica', 0, 0);

insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("2850520452101", "Oprita", "Cristina", 3500, 26, "receptioner", "21160", current_date());
insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("2870215043388", "Stanescu", "Maria", 3500, 22, "receptioner", "21161", "2010-11-22");
insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("1940521267472", "Dobrean", "Tiberiu", 3500, 18, "receptioner", "21162", "2020-12-14");
insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("5000613193641", "Nastase", "Alexandru", 3500, 31, "receptioner", "21163", "2015-09-01");
insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("5000309292361", "Copaescu", "Catalin", 7500, 18, "medic", "10263", "2010-11-20");
insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("1870504338210", "Prejbeanu", "Radu", 5000, 14, "medic", "31854", "2012-07-14");
insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("1990718184495", "Simedrea", "Voicu", 8000, 16, "medic", "20204", "2016-12-08");
insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("1891016158532", "Moga", "Doru", 8500, 18, "medic", "29257", "2017-01-22");
insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("1851214318952", "Tomulescu", "Victor", 9000, 20, "medic", "87652", "2020-01-14");
insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("1990827323022", "Barta", "Bogdan", 4500, 13, "inspector resurse umane", "81815", "2018-08-22");
insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("2901109129743", "Safta", "Dana", 5500, 17, "expert financiar-contabil", "27487", "2018-08-29");
insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("6000607249201", "Ilici", "Alina", 1500, 24, "asistent medical","54813", "2010-05-14");
insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("2910624267383", "Paunescu", "Amalia", 1500, 24, "asistent medical","84730", "2016-02-03");
insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("2850621225645", "Ionesu", "Simina", 1500, 24, "asistent medical","33880", "2016-04-14");
insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("2920204234645", "Berecleanu", "Elena", 1500, 24, "asistent medical","17299", "2020-03-25");
insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("2860827357444", "Paloma", "Emilia", 1500, 24, "asistent medical","85254", "2020-04-04");
insert into angajati(CNP, nume, prenume, salariu, nr_minim_ore, functie, nr_contract, data_angajarii)
values ("2910809324121", "Manolescu", "Andreea", 1500, 24, "asistent medical","51580", "2020-10-27");

insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 1,1, "5000309292361", 8, 14, true, "Luni");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 4,2, "5000309292361", 10, 22, true, "Marti");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 1,2, "5000309292361", 9, 15, true, "Miercuri");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 2,3, "5000309292361", 7, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 4,1, "5000309292361", 8, 23, true, "Vineri");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-16', 1,1, "5000309292361", null, null, false, "Sambata");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-17', 1,3, "5000309292361", null, null, false, "Duminica");

insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 3,1, "1870504338210", 10, 23, true, "Luni");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 4,1, "1870504338210", 7, 10, true, "Marti");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 3,1, "1870504338210", 10, 18, true, "Miercuri");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 1,2, "1870504338210", 9, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 2,1, "1870504338210", 7, 23, true, "Vineri");

insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 1,2, "1990718184495", 14, 23, true, "Luni");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 4,3, "1990718184495", 7, 14, true, "Marti");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 4,1, "1990718184495", 6, 15, true, "Miercuri");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 3,5, "1990718184495", 9, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 1,4, "1990718184495", 9, 23, true, "Vineri");

insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 4,2, "1891016158532", 10, 22, true, "Luni");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 3,4, "1891016158532", 10, 14, true, "Marti");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 2,2, "1891016158532", 7, 17, true, "Miercuri");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 4,3, "1891016158532", 14, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 2,2, "1891016158532", 7, 23, true, "Vineri");

insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 2,3, "1851214318952", 12, 22, true, "Luni");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 3,1, "1851214318952", 11, 21, true, "Marti");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 4,5, "1851214318952", 12, 22, true, "Miercuri");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 4,2, "1851214318952", 6, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate,id_cabinet, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 1,5, "1851214318952", 10, 23, true, "Vineri");

insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 1, "2850520452101", 8, 23, true, "Luni");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 1, "2850520452101", 8, 22, true, "Marti");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 1, "2850520452101", 8, 22, true, "Miercuri");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 1, "2850520452101", 9, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 1, "2850520452101", 9, 23, true, "Vineri");

insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 2, "2870215043388", 12, 22, true, "Luni");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 2, "2870215043388", 7, 19, true, "Marti");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 2, "2870215043388", 7, 19, true, "Miercuri");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 2, "2870215043388", 7, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 2, "2870215043388", 7, 23, true, "Vineri");

insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 3, "1940521267472", 10, 21, true, "Luni");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 3, "1940521267472", 10, 21, true, "Marti");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 3, "1940521267472", 10, 21, true, "Miercuri");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 3, "1940521267472", 9, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 3, "1940521267472", 9, 23, true, "Vineri");

insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 4, "5000613193641", 10, 22, true, "Luni");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 4, "5000613193641", 6, 22, true, "Marti");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 4, "5000613193641", 6, 24, true, "Miercuri");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 4, "5000613193641", 6, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 4, "5000613193641", 6, 23, true, "Vineri");

insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 1, "1990827323022", 10, 17, true, "Luni");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 2, "1990827323022", 9, 15, true, "Marti");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 3, "1990827323022", 10, 17, true, "Miercuri");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 4, "1990827323022", 10, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 1, "1990827323022", 9, 23, true, "Vineri");

insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 4, "2901109129743", 8, 18, true, "Luni");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 1, "2901109129743", 8, 18, true, "Marti");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 2, "2901109129743", 8, 17, true, "Miercuri");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 3, "2901109129743", 8, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 4, "2901109129743", 9, 23, true, "Vineri");

insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 1, "6000607249201", 8, 14, true, "Luni");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 4, "6000607249201", 10, 22, true, "Marti");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 1, "6000607249201", 9, 15, true, "Miercuri");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 2, "6000607249201", 7, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 4, "6000607249201", 8, 23, true, "Vineri");

insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 3, "2910624267383", 10, 18, true, "Luni");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 4, "2910624267383", 7, 10, true, "Marti");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 3, "2910624267383", 10, 18, true, "Miercuri");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 1, "2910624267383", 9, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 2, "2910624267383", 7, 23, true, "Vineri");

insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 1, "2850621225645", 14, 23, true, "Luni");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 4, "2850621225645", 7, 14, true, "Marti");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 4, "2850621225645", 6, 15, true, "Miercuri");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 3, "2850621225645", 9, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 1, "2850621225645", 9, 23, true, "Vineri");

insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 4, "2920204234645", 10, 22, true, "Luni");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 3, "2920204234645", 10, 14, true, "Marti");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 2, "2920204234645", 7, 17, true, "Miercuri");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 4, "2920204234645", 14, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 2, "2920204234645", 7, 23, true, "Vineri");

insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 2, "2860827357444", 12, 22, true, "Luni");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 3, "2860827357444", 11, 21, true, "Marti");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 4, "2860827357444", 12, 22, true, "Miercuri");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 4, "2860827357444", 6, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 1, "2860827357444", 10, 23, true, "Vineri");

insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-11', 1, "2910809324121", 8, 14, true, "Luni");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-12', 4, "2910809324121", 10, 22, true, "Marti");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-13', 1, "2910809324121", 9, 15, true, "Miercuri");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-14', 2, "2910809324121", 7, 23, true, "Joi");
insert into program_angajati(data_program, id_unitate, CNP, ora_inceput, ora_sfarsit, zi_lucratoare, zi_calendaristica)
values('2021-01-15', 4, "2910809324121", 8, 23, true, "Vineri");

insert into asistenti(CNP, grad, tip)
values("6000607249201", "principal", "generalist");
insert into asistenti(CNP, grad, tip)
values("2910624267383", "principal", "laborator");
insert into asistenti(CNP, grad, tip)
values("2850621225645", "principal", "radiologie");
insert into asistenti(CNP, grad, tip)
values("2920204234645", "secundar", "generalist");
insert into asistenti(CNP, grad, tip)
values("2860827357444", "secundar", "laborator");
insert into asistenti(CNP, grad, tip)
values("2910809324121", "secundar", "radiologie");

insert into medici(CNP, parafa, titlu_stiintific, post_didactic, procent_venit)
values("5000309292361", "CCdp0145", "doctorand", "profesor", 4.5);
insert into medici(CNP, parafa, titlu_stiintific, post_didactic, procent_venit)
values("1870504338210", "PRdc0205", "doctorand", "conferentiar", 5);
insert into medici(CNP, parafa, titlu_stiintific, post_didactic, procent_venit)
values("1990718184495", "SVds0355", "doctor în stiinte medicale", "sef de lucrari", 5.5);
insert into medici(CNP, parafa, titlu_stiintific, post_didactic, procent_venit)
values("1891016158532", "MDdp0404", "doctor în stiinte medicale", "preparator", 4);
insert into medici(CNP, parafa, titlu_stiintific, post_didactic, procent_venit)
values("1851214318952", "TVds0548", "doctorand", "sef de lucrari", 4.8);

insert into specialitate_medici(id_specialitate, parafa, specialitate, grad, cnp)
values(1, "CCdp0145", "neurolog", "primar", "5000309292361");
insert into specialitate_medici(id_specialitate, parafa, specialitate, grad, cnp)
values(2, "PRdc0205", "pneumolog", "specialist", "1870504338210");
insert into specialitate_medici(id_specialitate, parafa, specialitate, grad, cnp)
values(3, "SVds0355", "gastroenterolog", "specialist", "1990718184495");
insert into specialitate_medici(id_specialitate, parafa, specialitate, grad, cnp)
values(4, "MDdp0404", "internist", "primar", "1891016158532");
insert into specialitate_medici(id_specialitate, parafa, specialitate, grad, cnp)
values(5, "TVds0548", "chirurg", "primar", "1851214318952");

insert into competente(parafa, CNP, ecografie, endoscopie_digestiva, ecocardiografie, cardiologie_interventionala,
bronhoscopie, EEG, EMG, dializa, chirurgie_laparoscopica, chirurgie_toracica, chirurgie_spinala,
litotritie_extracorporeala, explorare_computer_tomograf, imagistica_prin_rezonanta_magnetica)
values("CCdp0145", "5000309292361", true, false, true, false, false, true, false, true, true, false, false, true, false, false);
insert into competente(parafa, CNP, ecografie, endoscopie_digestiva, ecocardiografie, cardiologie_interventionala,
bronhoscopie, EEG, EMG, dializa, chirurgie_laparoscopica, chirurgie_toracica, chirurgie_spinala,
litotritie_extracorporeala, explorare_computer_tomograf, imagistica_prin_rezonanta_magnetica)
values("PRdc0205", "1870504338210", true, true, true, false, false, true, true, false, true, false, false, true, false, false);
insert into competente(parafa, CNP, ecografie, endoscopie_digestiva, ecocardiografie, cardiologie_interventionala,
bronhoscopie, EEG, EMG, dializa, chirurgie_laparoscopica, chirurgie_toracica, chirurgie_spinala,
litotritie_extracorporeala, explorare_computer_tomograf, imagistica_prin_rezonanta_magnetica)
values("SVds0355", "1990718184495", true, false, true, false, false, true, false, false, true, false, false, true, true, true);
insert into competente(parafa, CNP, ecografie, endoscopie_digestiva, ecocardiografie, cardiologie_interventionala,
bronhoscopie, EEG, EMG, dializa, chirurgie_laparoscopica, chirurgie_toracica, chirurgie_spinala,
litotritie_extracorporeala, explorare_computer_tomograf, imagistica_prin_rezonanta_magnetica)
values("MDdp0404", "1891016158532", true, true, true, true, true, true, false, false, true, true, true, true, false, false);
insert into competente(parafa, CNP, ecografie, endoscopie_digestiva, ecocardiografie, cardiologie_interventionala,
bronhoscopie, EEG, EMG, dializa, chirurgie_laparoscopica, chirurgie_toracica, chirurgie_spinala,
litotritie_extracorporeala, explorare_computer_tomograf, imagistica_prin_rezonanta_magnetica)
values("TVds0548", "1851214318952", true, true, true, false, false, true, false, false, true, false, false, true, false, false);

insert into utilizatori(email, parola, CNP,  oras, telefon, IBAN, tip)
values("cristinaoprita@yahoo.com", "CristinaO", "2850520452101", "Bucuresti", "0745213788", "RO80RZBR4474495833775872", "angajat");
insert into utilizatori(email, parola, CNP, oras, telefon, IBAN, tip)
values("catalincopaescu@yahoo.com", "CatalinC", "5000309292361", "Cluj-Napoca", "0744512637", "RO97RZBR6321121994719521",  "angajat");
insert into utilizatori(email, parola, CNP, oras, telefon, IBAN,tip)
values("raduprejbeanu@yahoo.com", "RaduP", "1870504338210", "Cluj-Napoca", "0725847121", "RO90RZBR1738623544949214", "angajat");
insert into utilizatori(email, parola, CNP, oras, telefon, IBAN, tip)
values("voicusimedrea@yahoo.com", "VoicuS", "1990718184495", "Cluj-Napoca", "0721554983", "RO46PORL8149514196754622", "angajat");
insert into utilizatori(email, parola, CNP, oras, telefon, IBAN, tip)
values("dorumoga@yahoo.com", "DoruM", "1891016158532", "Cluj-Napoca", "0767218595", "RO39PORL2213682566266311",  "angajat");
insert into utilizatori(email, parola, CNP,  oras, telefon, IBAN, tip)
values("victortomulescu@yahoo.com", "VictorT", "1851214318952", "Cluj-Napoca", "0787554213", "RO79PORL7847813574816622", "angajat");
insert into utilizatori(email, parola, CNP,  oras, telefon, IBAN, tip)
values("bogdanbarta@yahoo.com", "BogdanB", "1990827323022", "Cluj-Napoca", "0765219772", "RO94RZBR6571347164359894", "angajat");
insert into utilizatori(email, parola, CNP,  oras, telefon, IBAN, tip)
values("danasafta@yahoo.com", "DanaS", "2901109129743", "Cluj-Napoca", "0755216388", "RO37RZBR5462247924716149", "angajat");
insert into utilizatori(email, parola, CNP,  oras, telefon, IBAN, tip)
values("alinailici@yahoo.com", "AlinaI", "6000607249201", "Cluj-Napoca", "0767125124", "RO64PORL9326811553261778", "angajat");
insert into utilizatori(email, parola, CNP,  oras, telefon, IBAN, tip)
values("amaliapaunescu@yahoo.com", "AmaliaP", "2910624267383", "Cluj-Napoca", "0763258711", "RO51RZBR8644258263291224", "angajat");
insert into utilizatori(email, parola, CNP,  oras, telefon, IBAN, tip)
values("siminaionescu@yahoo.com", "SiminaI", "2850621225645", "Cluj-Napoca", "0799213877", "RO21PORL2761395825922552", "angajat");
insert into utilizatori(email, parola, CNP,   oras, telefon, IBAN, tip)
values("elenaberecleanu@yahoo.com", "ElenaB", "2920204234645", "Cluj-Napoca", "0712522458", "RO31RZBR6635125791618918", "angajat");
insert into utilizatori(email, parola, CNP,   oras, telefon, IBAN, tip)
values("andreeamanolescu@yahoo.com", "AndreeaM", "2910809324121", "Cluj-Napoca", "0768415831", "RO25RZBR3428731558743688", "super-administrator");
insert into utilizatori(email, parola, CNP,   oras, telefon, IBAN, tip)
values("emiliapaloma@yahoo.com", "EmiliaP", "2860827357444", "Cluj-Napoca", "0766125613", "RO49RZBR1558862542651344", "administrator");
