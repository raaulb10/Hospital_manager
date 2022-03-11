# proceduri
use Policlinica;

drop procedure if exists consultaInvestigatii;
delimiter //
create procedure consultaInvestigatii(id_programare varchar(20))
begin
	select servicii.nume, investigatii.concluzie, investigatii.nume_asistent, investigatii.prenume_asistent 
    from investigatii left join servicii on investigatii.id_serviciu = servicii.id_serviciu
    where investigatii.id_programare = id_programare;
end //
delimiter ;

drop procedure if exists consultaProgramari;
delimiter //
create procedure consultaProgramari(CNP varchar(20))
begin
	select id_programare, data_programare, ora_programare, CNP_pacient, nume_pacient, prenume_pacient, durata_consultatie 
    from programari where programari.CNP_medic = CNP
    and dayofyear(data_programare) = dayofyear(current_date());
end //
delimiter ;

drop procedure if exists consultaIstoric;
delimiter //
create procedure consultaIstoric(CNP varchar(20))
begin
	select * from istoric_pacienti where CNP = 
    (select CNP_pacient from programari where programari.id_programare = istoric_pacienti.id_programare);
end //
delimiter ;