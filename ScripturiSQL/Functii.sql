# functii
# pentru fiecare functie se verifica daca angajatul care apeleaza acea functie este in timpul programului
# apoi, in functie de procedura, se verifica validarea anumitor date

use Policlinica;

drop function if exists setConcediu;
delimiter //
create function setConcediu(inspectorCNP varchar(20), angCNP varchar(20), firstDay date, lastDay date)
	returns integer
    reads sql data
    deterministic
begin
	set @ok := 0;
	set @ins_ora_inceput := null, @ins_ora_sfarsit := null, @ins_zi_lucratoare := null;
    select ora_inceput, ora_sfarsit, zi_lucratoare into @ins_ora_inceput, @ins_ora_sfarsit, @ins_zi_lucratoare
    from program_angajati where program_angajati.cnp = inspectorCNP and data_program = current_date();
    if hour(now()) >= @ins_ora_inceput and hour(now()) <= @ins_ora_sfarsit and @ins_zi_lucratoare = 1 then
		set @angCNP := null;
		select CNP into @angCNP from angajati where CNP = angCNP;
		if @angCNP is not null then
			set @crtDay := firstDay;
			while @crtDay <= lastDay do
				set @dayExists := null;
				select data_program into @dayExists from program_angajati where CNP = @angCNP and data_program = @crtDay;
				if @dayExists is not null then
					update program_angajati set zi_lucratoare = false where CNP = @angCNP and data_program = @dayExists;
				else
					set @crtDayId := weekday(@crtDay);
					set @numeZi := null;
					case
						when @crtDayId = 0 then set @numeZi := "Luni";
						when @crtDayId = 1 then set @numeZi := "Marti";
						when @crtDayId = 2 then set @numeZi := "Miercuri";
						when @crtDayId = 3 then set @numeZi := "Joi";
						when @crtDayId = 4 then set @numeZi := "Vineri";
						when @crtDayId = 5 then set @numeZi := "Sambata";
						else set @numeZi := "Duminica";
					end case;
					insert into program_angajati(data_program, CNP, zi_lucratoare, zi_calendaristica)
					values(@crtDay, @angCNP, false, @numeZi);
				end if;
				set @crtDay := @crtDay + interval 1 day;
			end while;
            set @ok := 1;
		else
			set @ok := 2;
		end if;
	else
		set @ok := 3;
	end if;
    return @ok;
end //
delimiter ;

drop function if exists addProgramare;
delimiter //
create function addProgramare(id_programare varchar(20), data_programare date, 
	ora_programare int, CNP_receptioner varchar(20), CNP_medic varchar(20), CNP_pacient varchar(20), 
	nume_pacient varchar(50), prenume_pacient varchar(50),
    nume_medic_recomandat varchar(50), prenume_medic_recomandat varchar(50)) 
    returns integer
    reads sql data
    deterministic
begin
	set @ok := null;
    set @medic_liber := null;
    set @parafa = null;
    select parafa into @parafa from medici where CNP = CNP_medic;
    set @r_ora_inceput = null, @r_ora_sfarsit = null, @r_zi_lucratoare = null;
    select ora_inceput, ora_sfarsit, zi_lucratoare into @r_ora_inceput, @r_ora_sfarsit, @r_zi_lucratoare
    from program_angajati where program_angajati.CNP = CNP_receptioner and program_angajati.data_program = current_date(); 
    if hour(now()) >= @r_ora_inceput and hour(now()) <= @r_ora_sfarsit and @r_zi_lucratoare = 1 then
		set @m_ora_inceput = null, @m_ora_sfarsit = null, @m_zi_lucratoare = null;
        select ora_inceput, ora_sfarsit, zi_lucratoare into @m_ora_inceput, @m_ora_sfarsit, @m_zi_lucratoare
		from program_angajati where program_angajati.CNP = CNP_medic and program_angajati.data_program = data_programare; 
		if data_programare = current_date() and ora_programare > hour(now()) and @m_zi_lucratoare = 1 or 
		data_programare > current_date() and @m_zi_lucratoare = 1 and ora_programare >= @m_ora_inceput and ora_programare <= @m_ora_sfarsit then
			set @oraP := 0, @durataP := 0;
            select programari.ora_programare, programari.durata_consultatie into @oraP, @durataP
			from programari where programari.cnp_medic = CNP_medic and programari.data_programare = data_programare
			order by programari.ora_programare desc limit 1;
            set @oraP := @oraP + @durataP / 60;
			if ora_programare > @oraP then
				set @oraPacient := 0;
				select programari.ora_programare into @oraPacient
				from programari where programari.cnp_pacient = CNP_pacient and programari.data_programare = data_programare
				order by programari.ora_programare desc limit 1;
                if ora_programare > @oraPacient then
					insert into programari(id_programare, data_programare, ora_programare, CNP_medic, parafa, CNP_pacient, nume_pacient, prenume_pacient, nume_medic_recomandat, prenume_medic_recomandat, durata_consultatie)
					values (id_programare, data_programare, ora_programare, CNP_medic, @parafa, CNP_pacient, nume_pacient, prenume_pacient, nume_medic_recomandat, prenume_medic_recomandat, 0);
					set @ok := 1;
				else
					set @ok := 2;
				end if;
			else
				set @ok := 3;
			end if;
		else
			set @ok := 4;
		end if;
	else
		set @ok := 5;
	end if;
    return @ok;
end //
delimiter ;


drop function if exists addInvestigatie;
delimiter //
create function addInvestigatie(CNP_angajat varchar(20), id_programare varchar(20), id_serviciu varchar(20))
	returns integer
	reads sql data
    deterministic
begin
	set @ok := 0;
    set @ang_ora_inceput := null, @ang_ora_sfarsit := null, @ang_zi_lucratoare := null;
    select ora_inceput, ora_sfarsit, zi_lucratoare into @ang_ora_inceput, @ang_ora_sfarsit, @ang_zi_lucratoare
    from program_angajati where program_angajati.cnp = CNP_angajat and data_program = current_date();
    if hour(now()) >= @ang_ora_inceput and hour(now()) <= @ang_ora_sfarsit and @ang_zi_lucratoare = 1 then
		set @areParafa := null;
		select parafa into @areParafa from istoric_pacienti where istoric_pacienti.id_programare = id_programare;
		if @areParafa is null then
			set @exista := null;
			select investigatii.id_serviciu into @exista from investigatii where investigatii.id_programare = id_programare and investigatii.id_serviciu = id_serviciu;
			if @exista is null then
				insert into investigatii(id_programare, id_serviciu) values(id_programare, id_serviciu);
				set @durata := 0;
                select programari.durata_consultatie into @durata from programari where programari.id_programare = id_programare;
                set @durata_serviciu := 0;
                select durata into @durata_serviciu from servicii where servicii.id_serviciu = id_serviciu;
				update programari set durata_consultatie = @durata + @durata_serviciu where programari.id_programare = id_programare;
                set @ok := 1;
			else 
				set @ok := 2;
			end if;
		else
			set @ok := 3;
		end if;
	else
		set @ok := 4;
	end if;
	return @ok;
end //
delimiter ;

drop function if exists completareInvestigatie;
delimiter //
create function completareInvestigatie(CNP_asistent varchar(20), id_programare varchar(20), id_serviciu varchar(20), concluzie varchar(500))
	returns integer
	reads sql data
    deterministic
begin
	set @ok := 0;
	set @a_ora_inceput = null, @a_ora_sfarsit = null, @a_zi_lucratoare = null;
    select ora_inceput, ora_sfarsit, zi_lucratoare into @a_ora_inceput, @a_ora_sfarsit, @a_zi_lucratoare 	
    from program_angajati where program_angajati.CNP = CNP_asistent and program_angajati.data_program = current_date(); 
    if hour(now()) >= @a_ora_inceput and hour(now()) <= @a_ora_sfarsit and @a_zi_lucratoare = 1 then
		set @areParafa := null;
		select parafa into @areParafa from istoric_pacienti where istoric_pacienti.id_programare = id_programare;
		if @areParafa is null then
			set @eCompletat := null;
			select investigatii.concluzie into @eCompletat from investigatii where investigatii.id_programare = id_programare and investigatii.id_serviciu = id_serviciu;
			if @eCompletat is null then
				set @a_nume := null, @a_prenume := null;
                select nume, prenume into @a_nume, @a_prenume from angajati where angajati.CNP = CNP_asistent;
				update investigatii set investigatii.concluzie = concluzie, nume_asistent = @a_nume, prenume_asistent = @a_prenume
				where investigatii.id_programare = id_programare and investigatii.id_serviciu = id_serviciu;
				set @ok := 1;
			else
				set @ok := 2;
			end if;
		else
			set @ok := 3;
		end if;
	else
		set @ok := 4;
	end if;
    return @ok;
end //
delimiter ;

drop function if exists delInvestigatie;
delimiter //
create function delInvestigatie(CNP_medic varchar(20), id_programare varchar(20), id_serviciu varchar(20))
	returns integer
	reads sql data
    deterministic
begin
	set @ok := 0;
	set @med_ora_inceput := null, @med_ora_sfarsit := null, @med_zi_lucratoare := null;
    select ora_inceput, ora_sfarsit, zi_lucratoare into @med_ora_inceput, @med_ora_sfarsit, @med_zi_lucratoare
    from program_angajati where program_angajati.cnp = CNP_medic and data_program = current_date();
    if hour(now()) >= @med_ora_inceput and hour(now()) <= @med_ora_sfarsit and @med_zi_lucratoare = 1 then
		set @areParafa := null;
		select parafa into @areParafa from istoric_pacienti where istoric_pacienti.id_programare = id_programare;
		if @areParafa is null then
			set @nr_inv := 0;
            select count(*) into @nr_inv from investigatii where investigatii.id_programare = id_programare;
            if @nr_inv > 1 then
				set @exista := null;
				select investigatii.id_serviciu into @exista from investigatii 
				where investigatii.id_programare = id_programare and investigatii.id_serviciu = id_serviciu;
				if @exista is not null then
					delete from investigatii where investigatii.id_programare = id_programare 
					and investigatii.id_serviciu = id_serviciu;	
					set @ok := 1;
				else
					set @ok := 2;
				end if;
            else
				set @ok := 3;
			end if;
		else
			set @ok := 4;
		end if;
	else
		set @ok := 5;
	end if;
    return @ok;
end //
delimiter ;

drop function if exists completeazaRaport;
delimiter //
create function completeazaRaport(id_programare varchar(20), nume_asistent varchar(50), prenume_asistent varchar(50),
	simptome varchar(50), diagnostic varchar(50), recomandari varchar (50), cnp_medic varchar(20))
	returns integer
	reads sql data
    deterministic
begin
    set @ok := 0;
    set @med_ora_inceput := null, @med_ora_sfarsit := null, @med_zi_lucratoare := null;
    select ora_inceput, ora_sfarsit, zi_lucratoare into @med_ora_inceput, @med_ora_sfarsit, @med_zi_lucratoare
    from program_angajati where program_angajati.cnp = cnp_medic and data_program = current_date();
    if hour(now()) >= @med_ora_inceput and hour(now()) <= @med_ora_sfarsit and @med_zi_lucratoare = 1 then
		set @areParafa := null;
		select parafa into @areParafa from istoric_pacienti where istoric_pacienti.id_programare = id_programare;
		if @areParafa is null then
			set @simptome := null, @diagnostic := null, @recomandari := null, @cnp_medic := null;
			select simptome, diagnostic, recomandari, cnp_medic
			into @simptome, @diagnostic, @recomandari, @cnp_medic 
            from istoric_pacienti where istoric_pacienti.id_programare = id_programare;
			if @simptome is null and @diagnostic is null and @recomandari is null then
				set @a_nume := null, @a_prenume := null;
                set @nr_inv := 0, @nr_inv_completate := 0;
                select count(*) into @nr_inv from investigatii where investigatii.id_programare = id_programare;
                select count(*), investigatii.nume_asistent, investigatii.prenume_asistent into @nr_inv_completate, @a_nume, @a_prenume
                from investigatii where investigatii.id_programare = id_programare and investigatii.nume_asistent is not null and investigatii.prenume_asistent is not null;
                if @nr_inv = @nr_inv_completate then
                	set @cnp_pacient := null;
					select cnp_pacient into @cnp_pacient from programari where programari.id_programare = id_programare;
                    set @parafa := null;
                    select medici.parafa into @parafa from medici where medici.cnp = cnp_medic;
                    set @istoric_curent := null;
                    select distinct group_concat(istoric_pacienti.diagnostic separator ', ') into @istoric_curent
                    from istoric_pacienti, programari where programari.cnp_pacient = @cnp_pacient
                    and programari.id_programare = istoric_pacienti.id_programare limit 1;
                    insert into istoric_pacienti(id_programare, nume_asistent, prenume_asistent, simptome, diagnostic, recomandari, istoric, cnp_medic, parafa)
					values(id_programare, nume_asistent, prenume_asistent, simptome, diagnostic, recomandari, @istoric_curent, cnp_medic, @parafa);
                   set @ok := 1;
				else
					set @ok := 2;
				end if;
			else
				set @ok := 3;
			end if;
		else
			set @ok := 4;
		end if;
	else
		set @ok := 5;
	end if;
    return @ok;
end //
delimiter ;

drop function if exists verificaSalariu;
delimiter //
create function verificaSalariu(CNP_expert varchar(20), CNP_angajat varchar(20), luna int)
	returns integer
    reads sql data
    deterministic
begin
	set @ok := 0;
    set @salariu := 0;
	set @exp_ora_inceput := null, @exp_ora_sfarsit := null, @exp_zi_lucratoare := null;
    select ora_inceput, ora_sfarsit, zi_lucratoare into @exp_ora_inceput, @exp_ora_sfarsit, @exp_zi_lucratoare
    from program_angajati where program_angajati.cnp = CNP_expert and data_program = current_date();
    if CNP_expert = CNP_angajat or (hour(now()) >= @exp_ora_inceput and hour(now()) <= @exp_ora_sfarsit and @exp_zi_lucratoare = 1) then
		set @functie := null, @negociat := null, @minim := null, @ore := null;
		select functie, salariu, nr_minim_ore into @functie, @negociat, @minim from angajati where angajati.cnp = cnp_angajat;
		select sum(ora_sfarsit - ora_inceput) into @ore from program_angajati, angajati
		where program_angajati.cnp = cnp_angajat and angajati.cnp = cnp_angajat 
        and program_angajati.zi_lucratoare = 1 and month(program_angajati.data_program) = luna;
		if @ore < @minim then
			set @salariu := 0;
            set @ok := 2;
		else
			set @salariu := @negociat * (@ore / @minim);
            set @ok := 1;
			if @functie = "medic" then
				set @venit := 0;
                select sum((select servicii.pret from servicii where servicii.id_serviciu = investigatii.id_serviciu)) into @venit
				from investigatii where investigatii.id_programare in 
				(select distinct programari.id_programare from programari, angajati, program_angajati 
				where programari.cnp_medic = CNP_angajat and month(data_programare) = luna);
                set @procent := 0;
				select procent_venit into @procent from medici where medici.cnp = CNP_angajat;
                if @venit is not null then
					set @salariu := @salariu + @venit * (@procent / 100);
				end if;
			end if;
		end if;
	else
		set @ok := 3;
	end if;
    if @ok = 1 then
		set @ok := @salariu;
	end if;
    return @ok;
end //
delimiter ;

drop function if exists verificaProfitMedic;
delimiter //
create function verificaProfitMedic(CNP_expert varchar(20), CNP_angajat varchar(20), luna int)
	returns integer
    reads sql data
	deterministic
begin
	set @profit := 0;
	set @ok := 0;
	set @exp_ora_inceput := null, @exp_ora_sfarsit := null, @exp_zi_lucratoare := null;
    select ora_inceput, ora_sfarsit, zi_lucratoare into @exp_ora_inceput, @exp_ora_sfarsit, @exp_zi_lucratoare
    from program_angajati where program_angajati.cnp = CNP_expert and data_program = current_date();
    if hour(now()) >= @exp_ora_inceput and hour(now()) <= @exp_ora_sfarsit and @exp_zi_lucratoare = 1 then
        select sum((select servicii.pret from servicii where servicii.id_serviciu = investigatii.id_serviciu)) into @venit
		from investigatii where investigatii.id_programare in 
		(select distinct programari.id_programare from programari, angajati, program_angajati 
		where programari.cnp_medic = CNP_angajat and month(data_programare) = luna);
        set @procent := 0;
		select procent_venit into @procent from medici where medici.cnp = CNP_angajat;
		set @comision := @venit * (@procent / 100);
        set @profit := @venit - @comision;
		set @ok := 1;
	else
		set @ok := 2;
	end if;
    if @ok := 1 then
		set @ok := @profit;
	end if;
    return @ok;
end //
delimiter ;
        
drop function if exists verificaProfitSpecialitate;
delimiter //
create function verificaProfitSpecialitate(CNP_expert varchar(20), specialitate varchar(50), luna int)
	returns integer
    reads sql data
    deterministic
begin 
	set @profit := 0;
	set @ok := 0;
	set @exp_ora_inceput := null, @exp_ora_sfarsit := null, @exp_zi_lucratoare := null;
    select ora_inceput, ora_sfarsit, zi_lucratoare into @exp_ora_inceput, @exp_ora_sfarsit, @exp_zi_lucratoare
    from program_angajati where program_angajati.cnp = CNP_expert and data_program = current_date();
    if hour(now()) >= @exp_ora_inceput and hour(now()) <= @exp_ora_sfarsit and @exp_zi_lucratoare = 1 then
        set @venit := 0;
		select sum((select servicii.pret from servicii where servicii.id_serviciu = investigatii.id_serviciu and servicii.specialitate = specialitate))
        into @venit from investigatii where investigatii.id_programare in (select programari.id_programare from programari where month(data_programare) = luna);
		set @profit := @venit - 1/10 * @venit;
		set @ok := 1;
	else
		set @ok := 3;
	end if;
    if @ok := 1 then
		set @ok := @profit;
	end if;
    return @ok;
end //
delimiter ;

drop function if exists verificaProfitUnitate;
delimiter //
create function verificaProfitUnitate(CNP_expert varchar(20), id_unitate int, luna int)
	returns integer
    reads sql data
    deterministic
begin
	set @ok := 0;
    set @venit := 0;
    set @profit := 0;
	set @exp_ora_inceput := null, @exp_ora_sfarsit := null, @exp_zi_lucratoare := null;
    select ora_inceput, ora_sfarsit, zi_lucratoare into @exp_ora_inceput, @exp_ora_sfarsit, @exp_zi_lucratoare
    from program_angajati where program_angajati.cnp = CNP_expert and data_program = current_date();
    if hour(now()) >= @exp_ora_inceput and hour(now()) <= @exp_ora_sfarsit and @exp_zi_lucratoare = 1 then
        select sum((select servicii.pret from servicii where servicii.id_serviciu = investigatii.id_serviciu)) into @venit
		from investigatii where investigatii.id_programare in 
		(select distinct programari.id_programare from programari, angajati, program_angajati,medici 
		where programari.cnp_medic=medici.cnp and medici.cnp=angajati.cnp and angajati.cnp=program_angajati.cnp and programari.data_programare=program_angajati.data_program
		and program_angajati.id_unitate = id_unitate and month(data_programare) = luna);
        set @profit := @venit - 1/10 * @venit;
        set @ok := 1;
	else
		set @ok := 2;
    end if;
    if @ok := 1 then
		set @ok := @profit;
	end if;
    return @ok;
end //
delimiter ;

drop function if exists verificaVenitPoliclinica;
delimiter //
create function verificaVenitPoliclinica(CNP_expert varchar(20), luna int)
	returns integer
    reads sql data
    deterministic
begin
	set @venit := 0;
	set @ok := 0;
	set @exp_ora_inceput := null, @exp_ora_sfarsit := null, @exp_zi_lucratoare := null;
    select ora_inceput, ora_sfarsit, zi_lucratoare into @exp_ora_inceput, @exp_ora_sfarsit, @exp_zi_lucratoare
    from program_angajati where program_angajati.cnp = CNP_expert and data_program = current_date();
    if hour(now()) >= @exp_ora_inceput and hour(now()) <= @exp_ora_sfarsit and @exp_zi_lucratoare = 1 then
		select sum((select servicii.pret from servicii where servicii.id_serviciu = investigatii.id_serviciu)) into @venit
		from investigatii where investigatii.id_programare in 
		(select distinct programari.id_programare from programari, angajati, program_angajati 
		where programari.cnp_medic in (select cnp from angajati) and program_angajati.cnp in (select cnp from angajati)
		and program_angajati.id_unitate = id_unitate and month(data_programare) = luna);
		set @ok := 1;
	else
		set @ok := 2;
	end if;
    if @ok = 1 then
		set @ok := @venit;
	end if;
    return @ok;
end //
delimiter ;

drop function if exists verificaCheltuieliPoliclinica;
delimiter //
create function verificaCheltuieliPoliclinica(CNP_expert varchar(20), luna int)
	returns integer
    reads sql data
    deterministic
begin
    set @salarii := 0;
	set @ok := 0;
	set @exp_ora_inceput := null, @exp_ora_sfarsit := null, @exp_zi_lucratoare := null;
    select ora_inceput, ora_sfarsit, zi_lucratoare into @exp_ora_inceput, @exp_ora_sfarsit, @exp_zi_lucratoare
    from program_angajati where program_angajati.cnp = CNP_expert and data_program = current_date();
    if hour(now()) >= @exp_ora_inceput and hour(now()) <= @exp_ora_sfarsit and @exp_zi_lucratoare = 1 then
		set @angajati := 0;
		select max(id) into @angajati from angajati;
		set @angajat_curent := 1;
		while @angajat_curent <= @angajati do
			set @exista := null;
			select id into @exista from angajati where angajati.id = @angajat_curent;
			if @exista is not null then
				set @cnp_angajat_curent := null;
                select cnp into @cnp_angajat_curent from angajati where angajati.id = @angajat_curent;
                set @profit := 0;
                select verificaSalariu(CNP_expert, @cnp_angajat_curent, luna) into @profit;
                if @profit is not null then
					set @salarii := @salarii + @profit;
                end if;
			end if;
            set @angajat_curent := @angajat_curent + 1;
		end while;
        set @ok := 1;
	else
		set @ok := 2;
	end if;
    if @ok = 1 then
		set @ok := @salarii;
	end if;
    return @ok;
end //
delimiter ;