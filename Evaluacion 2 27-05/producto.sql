DELETE FROM baseusuarios.producto;
SELECT * FROM baseusuarios.producto;
use baseusuarios;

insert into producto (id_producto, nombre_producto, descripcion_producto, precio_producto, cantidad_stock, fecha_ingreso, categoria) 
values ('001', 'Perfume Rose Elegant', 'Fragancia floral con notas de rosa y jazmin', '32990 ', '30', '2025-05-01 10:15:00', 'Perfumes');
insert into producto (id_producto, nombre_producto, descripcion_producto, precio_producto, cantidad_stock, fecha_ingreso, categoria) 
values ('002', 'Crema Hidratante Aloe', 'Crema facial con extracto natural de aloe', '9990', '50', '2025-05-01 14:30:00', 'Cuidado facial');
insert into producto (id_producto, nombre_producto, descripcion_producto, precio_producto, cantidad_stock, fecha_ingreso, categoria) 
values ('003', 'Labial Mate Coral', 'Labial de larga duración color coral intenso', '6490', '70', '2025-05-10 08:00:00', 'Maquillaje');
insert into producto (id_producto, nombre_producto, descripcion_producto, precio_producto, cantidad_stock, fecha_ingreso, categoria) 
values ('004', 'Shampoo Revitalizante', 'Shampoo con keratina para cabello seco', '11990', '40', '2025-03-28 09:45:00', 'Cuidado capilar');

SELECT * FROM baseusuarios.producto;