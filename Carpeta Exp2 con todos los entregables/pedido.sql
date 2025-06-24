DELETE FROM baseusuarios.pedido;
SELECT * FROM baseusuarios.pedido;
use baseusuarios;

insert into pedido (id_pedido, id_cliente, fecha_pedido, estado, totalapagar) 
values ('26548','1236789', '2024-12-25 10:15:00', 'En camino', '541561');
insert into pedido (id_pedido, id_cliente, fecha_pedido, estado, totalapagar) 
values ('15348', '9876521', '2024-07-16 09:45:00', 'Preparando', '15261');
insert into pedido (id_pedido, id_cliente, fecha_pedido, estado, totalapagar) 
values ('65548', '13467559', '2024-08-19 14:30:00', 'Llegando', '5000');
insert into pedido (id_pedido, id_cliente, fecha_pedido, estado, totalapagar) 
values ('11548', '2581369', '2024-03-01 08:00:00', 'Preparando', '15000');

SELECT * FROM baseusuarios.pedido;