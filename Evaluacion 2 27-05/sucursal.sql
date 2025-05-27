DELETE FROM baseusuarios.sucursal;
SELECT * FROM baseusuarios.sucursal;
use baseusuarios;

insert into sucursal (id_sucursal, direccion_sucursal, horarios_sucursal, nombre_sucursal) 
values ('1', 'Av. Providencia 1234, Santiago', 'Lunes a Viernes 9:00 - 18:00', 'Sucursal Providencia');
insert into sucursal (id_sucursal, direccion_sucursal, horarios_sucursal, nombre_sucursal) 
values ('2', 'Av. Los Pajaritos 567, Maipú', 'Lunes a Sábado 10:00 - 19:00', 'Sucursal Maipú');
insert into sucursal (id_sucursal, direccion_sucursal, horarios_sucursal, nombre_sucursal) 
values ('3', 'Calle O’Higgins 321, Viña del Mar', 'Lunes a Viernes 8:30 - 17:30', 'Sucursal Viña del Mar');
insert intosucursal (id_sucursal, direccion_sucursal, horarios_sucursal, nombre_sucursal) 
values ('4', 'Av. Alemania 765, Temuco', 'Lunes a Viernes 9:00 - 18:30', 'Sucursal Temuco');

select * from baseusuarios.sucursal;