alter table pacientes add ativo boolean not null;
update pacientes set ativo = true;