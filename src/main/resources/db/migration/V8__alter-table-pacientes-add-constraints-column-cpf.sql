DELETE FROM pacientes;
ALTER TABLE pacientes ADD CONSTRAINT cpf_unico UNIQUE (cpf);