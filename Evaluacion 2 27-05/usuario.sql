DELETE FROM baseusuarios.usuario;
select * from baseusuarios.usuario;
use baseusuarios;

insert into usuario (id_usuario, nombre_usuario, correo_usuario, contraseña, tipo_usuario, fecha_creacion) 
values ('1234589', 'Cristian Lizama', 'krish@email.com', 'abc153', 'guest', '2024-05-01 10:15:00');
insert into usuario (id_usuario, nombre_usuario, correo_usuario, contraseña, tipo_usuario, fecha_creacion) 
values ('134679', 'Ignacio Aravena', 'nacho@email.com', 'sonic1532', 'guest', '2024-05-20 14:30:00');
insert into usuario (id_usuario, nombre_usuario, correo_usuario, contraseña, tipo_usuario, fecha_creacion) 
values ('258147369', 'Taylor Hebert', 'tay@email.com', 'cras1535h', 'worker', '2024-05-25 08:00:00');
insert into usuario (id_usuario, nombre_usuario, correo_usuario, contraseña, tipo_usuario, fecha_creacion) 
values ('9876321', 'Joshua Mardones', 'joshua@email.com', 'pop15621', 'worker', '2024-05-10 09:45:00');

select * from baseusuarios.usuario;