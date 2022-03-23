insert into clientes(id, nombre, apellido, email, create_at, foto) values(1, 'Juan', 'Perez', 'juan@mail.com', '2020-01-01', "");
insert into clientes(id, nombre, apellido, email, create_at, foto) values(2, 'Andrea', 'Lopez', 'andrea@mail.com', '2020-01-01', "");
insert into clientes(id, nombre, apellido, email, create_at, foto) values(3, 'Fernando', 'cusco', 'fernando@mail.com', '2020-01-01', "");
insert into clientes(id, nombre, apellido, email, create_at, foto) values(4, 'Karla', 'Cruz', 'karla@mail.com', '2020-01-01', "");
insert into clientes(id, nombre, apellido, email, create_at, foto) values(5, 'Maria', 'Quito', 'maria@mail.com', '2020-01-01', "");
insert into clientes(id, nombre, apellido, email, create_at, foto) values(6, 'Andres', 'Perez', 'andres@mail.com', '2020-01-01', "");
insert into clientes(id, nombre, apellido, email, create_at, foto) values(7, 'Jose', 'Flores', 'jose@mail.com', '2020-01-01', "");
insert into clientes(id, nombre, apellido, email, create_at, foto) values(8, 'Ester', 'Rosas', 'ester@mail.com', '2020-01-01', "");
insert into clientes(id, nombre, apellido, email, create_at, foto) values(9, 'Katty', 'Castro', 'katty@mail.com', '2020-01-01', "");
insert into clientes(id, nombre, apellido, email, create_at, foto) values(10, 'Esteban', 'Abad', 'esteban@mail.com', '2020-01-01', "");
insert into clientes(id, nombre, apellido, email, create_at, foto) values(11, 'Armando', 'Puertas', 'armando@mail.com', '2020-01-01', "");
insert into clientes(id, nombre, apellido, email, create_at, foto) values(12, 'Lalo', 'Cota', 'lalo@mail.com', '2020-01-01', "");
insert into clientes(id, nombre, apellido, email, create_at, foto) values(13, 'Tavo', 'Tijon', 'tavo@mail.com', '2020-01-01', "");


insert into productos(nombre, precio, create_at) values('Mac Book Pro 2020', 1000, Now());
insert into productos(nombre, precio, create_at) values('Monitor Asus', 800, Now());
insert into productos(nombre, precio, create_at) values('Teclado Mecanico', 200, Now());
insert into productos(nombre, precio, create_at) values('Lampara', 90, Now());
insert into productos(nombre, precio, create_at) values('Mouse Logitech', 110, Now());
insert into productos(nombre, precio, create_at) values('Case Corsair', 340, Now());
insert into productos(nombre, precio, create_at) values('Cable Red', 10, Now());
insert into productos(nombre, precio, create_at) values('Audifonos Logitech', 240, Now());
insert into productos(nombre, precio, create_at) values('Laptop Asus Tuf', 2000, Now());
insert into productos(nombre, precio, create_at) values('Fuente Poder Asus 500w', 600, Now());
insert into productos(nombre, precio, create_at) values('Mainboard Gigabyte mx 5000', 900, Now());
insert into productos(nombre, precio, create_at) values('Procesado Intel i9 9000k', 1800, Now());
insert into productos(nombre, precio, create_at) values('Procesado Amd Ryzen 7', 1500, Now());
insert into productos(nombre, precio, create_at) values('Gtx 3060 16Gb', 2500, Now());
insert into productos(nombre, precio, create_at) values('Asus Rog Phone 5', 900, Now());

insert into facturas(descripcion, observacion, cliente_id, create_at) values('Factura de prueba #1', 'Factura de prueba', 1, Now());
insert into item_factura(cantidad, factura_id, producto_id) values(3, 1, 1);
insert into item_factura(cantidad, factura_id, producto_id) values(4, 1, 3);
insert into item_factura(cantidad, factura_id, producto_id) values(2, 1, 5);
insert into item_factura(cantidad, factura_id, producto_id) values(5, 1, 7);

insert into facturas(descripcion, observacion, cliente_id, create_at) values('Factura de prueba #2', 'Factura de prueba', 1, Now());
insert into item_factura(cantidad, factura_id, producto_id) values(3, 2, 2);
insert into item_factura(cantidad, factura_id, producto_id) values(5, 2, 3);
insert into item_factura(cantidad, factura_id, producto_id) values(6, 2, 5);
insert into item_factura(cantidad, factura_id, producto_id) values(5, 2, 7);