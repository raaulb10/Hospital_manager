# crearea bazei de date

drop database if exists Policlinica; 
create database Policlinica ; 
use Policlinica;

create table unitati (
    id_unitate int(20) auto_increment primary key,
    denumire varchar(50),
    oras varchar(50)
);

create table cabinete(
id_cabinet int(20),
id_unitate int(20),
foreign key (id_unitate) references unitati (id_unitate),
primary key (id_cabinet, id_unitate)
);

create table servicii (
    id_serviciu varchar(20) primary key,
    nume varchar(50),
    descriere varchar(500),
    specialitate varchar(50),
    competenta_necesara varchar(50),
    pret float,
    durata int
);

create table servicii_oferite (
    id_unitate int,
    id_serviciu varchar(20),
    foreign key (id_unitate) references unitati (id_unitate),
    foreign key (id_serviciu) references servicii (id_serviciu)
);

create table program_unitati (
    id_unitate int(20),
    zi_calendaristica enum('Luni', 'Marti', 'Miercuri', 'Joi', 'Vineri', 'Sambata', 'Duminica'),
    ora_deschidere int,
    ora_inchidere int,
    foreign key (id_unitate) references unitati (id_unitate)
);

create table angajati (
	id	int auto_increment primary key,
    cnp varchar(20) unique,
    nume varchar(50),
    prenume varchar(50),
    salariu int,
    nr_minim_ore int,
    nr_contract varchar(20),
    data_angajarii date,
    functie enum('inspector resurse umane', 'expert financiar-contabil', 'receptioner', 'asistent medical', 'medic')
);

create table program_angajati (
    data_program date,
    id_unitate int,
    cnp varchar(20),
    ora_inceput int,
    ora_sfarsit int,
    zi_lucratoare boolean,
    zi_calendaristica enum('Luni', 'Marti', 'Miercuri', 'Joi', 'Vineri', 'Sambata', 'Duminica'),
    primary key (cnp, data_program),
    foreign key (id_unitate) references unitati (id_unitate),
    foreign key (cnp) references angajati (cnp)
);


create table asistenti (
    cnp varchar(20) primary key,
    grad enum('principal', 'secundar'),
    tip enum('generalist', 'laborator', 'radiologie'),
    foreign key (cnp) references angajati (cnp)
);

create table medici (
    cnp varchar(20),
    parafa varchar(50),
    titlu_stiintific enum('doctorand', 'doctor în stiinte medicale'),
    post_didactic enum('preparator', 'asistent', 'sef de lucrari', 'conferentiar', 'profesor'),
    procent_venit float,
    primary key (cnp , parafa),
    foreign key (cnp) references angajati (cnp)
);

create table specialitate_medici (
    id_specialitate int primary key,
    parafa varchar(50),
    specialitate varchar(20),
    grad enum('specialist', 'primar'),
    cnp varchar(20),
    foreign key (cnp , parafa) references medici (cnp , parafa)
);

create table competente (
    parafa varchar(50) primary key,
    cnp varchar(20),
    ecografie boolean,
    endoscopie_digestiva boolean,
    ecocardiografie boolean,
    cardiologie_interventionala boolean,
    bronhoscopie boolean,
    eeg boolean,
    emg boolean,
    dializa boolean,
    chirurgie_laparoscopica boolean,
    chirurgie_toracica boolean,
    chirurgie_spinala boolean,
    litotritie_extracorporeala boolean,
    explorare_computer_tomograf boolean,
    imagistica_prin_rezonanta_magnetica boolean,
    foreign key (cnp , parafa) references medici (cnp , parafa)
);

create table programari (
    id_programare varchar(20),
    data_programare date,
    ora_programare int,
    cnp_medic varchar(20),
    parafa varchar(50),
    cnp_pacient varchar(20),
    nume_pacient varchar(50),
    prenume_pacient varchar(50),
    nume_medic_recomandat varchar(50),
    prenume_medic_recomandat varchar(50),    
    durata_consultatie int,
    primary key (id_programare),
    foreign key (cnp_medic , parafa) references medici (cnp , parafa)
);

create table istoric_pacienti (
    id_programare varchar(20),
    nume_asistent varchar(50),
    prenume_asistent varchar(50),
    simptome varchar(50),
    diagnostic varchar(50),
    recomandari varchar(50),
    istoric varchar(500),
    cnp_medic varchar(20),
    parafa varchar(50),
    primary key (id_programare),
    foreign key (id_programare) references programari (id_programare),
    foreign key (cnp_medic, parafa) references medici (cnp, parafa)
);

create table investigatii (
    id_programare varchar(20),
    id_serviciu varchar(20),
    concluzie varchar(500),
    nume_asistent varchar(50),
    prenume_asistent varchar(50),
    foreign key (id_serviciu) references servicii (id_serviciu),
    foreign key (id_programare) references programari (id_programare)
);

create table utilizatori (
    email varchar(50) primary key,
    parola varchar(50),
    cnp varchar(20),
    oras varchar(50),
    telefon varchar(20),
    iban varchar(25),
	tip enum('administrator', 'super-administrator', 'angajat'),
    foreign key (cnp) references angajati(cnp)
);
