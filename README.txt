------------------------------------------

PROIECT LA BAZE DE DATE: POLICLINICA

Echipa de proiect:
Blaj Sergiu-Emanuel
Borbei Raul-Aurelian
Mihalache Rares
Preda Cristian
30225, CTI, Romana, Seria A (2021)
------------------------------------------

Arhiva contine urmatoarele:
- documentatia, in care este explicata alegerea noastra si modul de implementare
- codul sursa Java
- imaginile folosite in interfata grafica
- script-urile MySQL folosite: CreareBD (creaza tabelele bazei de date), PopulareBD (populeaza tabelele bazei de date), Functii, Proceduri si Vederi
- Diagrama UML a bazei de date ("Diagrama.pdf")
- dependentele proiectului, reprezentat de librariile JAR: jcalendar - pentru selectia datei (sub forma unui calendar) si mysql-connector (are ca rol conectarea aplicatiei la baza de date)
- aplicatia in format Java archive, Policlinica.jar
- acest fisier README.txt

Atentie! Inainte de rularea programului, cititi documentatia si faceti modificarile necesare in fisierul "settings.txt"!

Recomandam, pentru testarea aplicatiei:

Functia: inspector resurse umane
Email: bogdanbarta@yahoo.com
Parola: BogdanB

Functia: receptioner
Email: cristinaoprita@yahoo.com
Parola: CristinaO

Functia: asistent medical
Email: alinailici@yahoo.com
Parola: AlinaI

Functia: medic
Email: raduprejbeanu@yahoo.com
Parola: RaduP

Functia: medic
Email: catalincopaescu@yahoo.com
Parola: CatalinC

Functia: medic
Email: victortomulescu@yahoo.com
Parola: VictorT

Functia: expert financiar contabil
Email: danasafta@yahoo.com
Parola: DanaS

Functia: administrator
Email: andreeamanolescu@yahoo.com
Parola: AndreeaM

Functia: super-administrator
Email: emiliapaloma@yahoo.com
Parola: EmiliaP

Iar pentru teste (fie din workbench, fie cu ajutorul aplicatiei)

insert into utilizatori(email, parola, CNP,   oras, telefon, IBAN, tip)
values("emiliapaloma@yahoo.com", "EmiliaP", "2860827357444", "Cluj-Napoca", "0766125613", "RO49RZBR1558862542651344", "administrator");

select addProgramare('1', '2021-01-15', 14, '2850520452101', '1870504338210', '12341', 'pop1', 'vasi1', 'pre1', 'tri1') as r;
select addProgramare('2', '2021-01-15', 15, '2850520452101', '5000309292361', '12342', 'pop2', 'vasi2', 'pre2', 'tri2') as r;
select addProgramare('3', '2021-01-15', 16, '2850520452101', '1851214318952', '12343', 'pop3', 'vasi3', 'pre3', 'tri3') as r;

select addInvestigatie('2850520452101', '1', '5') as r;
select addInvestigatie('2850520452101', '2', '1') as r;
select addInvestigatie('2850520452101', '3', '11') as r;

select completareInvestigatie('6000607249201', '1', '5', 'ok1') as r;
select completareInvestigatie('6000607249201', '2', '1', 'ok3') as r;
select completareInvestigatie('6000607249201', '3', '11', 'ok4') as r;

select completeazaRaport('1', 'Alina', 'Ilici', 's1', 'd1', 'r1', '1870504338210') as r;
select completeazaRaport('2', 'Alina', 'Ilici', 's2', 'd2', 'r2', '5000309292361') as r;
select completeazaRaport('3', 'Alina', 'Ilici', 's3', 'd3', 'r3', '1851214318952') as r;



