create table usuarios(
    id bigserial not null,
    login varchar(100) not null,
    senha varchar(255) not null,

    primary key(id)
);

insert into usuarios values(1, 'ana.souza@voll.med', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.');