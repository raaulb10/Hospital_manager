# vederi
use Policlinica;

drop view if exists verificaOrar;
create view verificaOrar as
	select * from program_angajati where yearweek(data_program, 1) = yearweek(now(), 1);

drop view if exists verificaProgramari;
create view verificaProgramari as
	select id_programare, data_programare, ora_programare, CNP_medic, CNP_pacient, nume_pacient, prenume_pacient, durata_consultatie 
    from programari where yearweek(data_programare, 1) = yearweek(now(), 1);