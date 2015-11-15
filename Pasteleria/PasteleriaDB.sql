CREATE TABLE Producto(
    ProductoID BIGINT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    NombreProducto VARCHAR(50) NOT NULL,
	CantidadProducto BIGINT NOT NULL,
    DescripcionProducto VARCHAR(200) NOT NULL,
    PrecioProducto FLOAT NOT NULL
);

CREATE TABLE Cliente (
	ClienteID BIGINT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	NombreCliente VARCHAR(15) NOT NULL,
	DireccionCliente VARCHAR(100) NOT NULL,
	TelefonoCliente VARCHAR(15) NOT NULL,
	CelularCliente VARCHAR(15) NOT NULL,
	EmailCliente VARCHAR(50) NOT NULL
);

CREATE TABLE Pedido(
	PedidoID BIGINT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	StatusPedido VARCHAR (10) NOT NULL,
	ProductoID BIGINT NOT NULL,
	ClienteID BIGINT NOT NULL,
	Fecha DATE,
	FechaPedido DATE,
	AbonoPedido FLOAT NOT NULL,
	TotalPedido FLOAT
 );

ALTER TABLE Pedido
    ADD CONSTRAINT FK_Producto_Pedido FOREIGN KEY(ProductoID)
        REFERENCES Producto(ProductoID);
ALTER TABLE Pedido
    ADD CONSTRAINT FK_Cliente_Pedido FOREIGN KEY(ClienteID)
        REFERENCES Cliente(ClienteID);
ALTER TABLE Pedido
    ALTER COLUMN Fecha DEFAULT CURRENT_DATE; 
ALTER TABLE Pedido
    ALTER COLUMN FechaPedido DEFAULT CURRENT_DATE;


CREATE TABLE Lista(
    ListaID BIGINT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    ProductoID BIGINT NOT NULL,
    PedidoID BIGINT NOT NULL,
    CantidadVendida BIGINT NOT NULL,
	PrecioVenta FLOAT NOT NULL
);

ALTER TABLE Lista
    ADD CONSTRAINT FK_Producto_Lista FOREIGN KEY(ProductoID)
        REFERENCES Producto(ProductoID);
ALTER TABLE Lista
    ADD CONSTRAINT FK_Pedido_Lista FOREIGN KEY(PedidoID)
        REFERENCES Pedido(PedidoID);

CREATE TABLE Usuario(
	UsuarioID BIGINT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	NombreUsuario VARCHAR(50) NOT NULL,
	ContrasenaUsuario VARCHAR(15) NOT NULL, 
	TipoUsuario VARCHAR(30)
);
 
CREATE TABLE Factura(
	FacturaID BIGINT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	UsuarioID BIGINT NOT NULL,
	PedidoID BIGINT NOT NULL,
	DetalleFactura VARCHAR(300) NOT NULL,
	Descuento FLOAT NOT NULL,
	TotalFacturado FLOAT NOT NULL,
	FechaFactura DATE
);

ALTER TABLE Factura
    ADD CONSTRAINT FK_Usuario_Factura FOREIGN KEY(UsuarioID)
        REFERENCES Usuario(UsuarioID);
ALTER TABLE Factura
    ADD CONSTRAINT FK_Pedido_Factura FOREIGN KEY(PedidoID)
        REFERENCES Pedido(PedidoID);
ALTER TABLE Factura
    ALTER COLUMN FechaFactura DEFAULT CURRENT_DATE;
	
INSERT INTO Producto(NombreProducto, CantidadProducto, DescripcionProducto, PrecioProducto)
 VALUES('Pastel de 3 leches', 10, 'Pastel de 3 leches, Esto es un pastel, Dios', 650.45);
INSERT INTO Producto(NombreProducto, CantidadProducto, DescripcionProducto, PrecioProducto)
 VALUES('Bizcocho', 5, 'Bizcocho sin relleno', 899.99);
INSERT INTO Producto(NombreProducto, CantidadProducto, DescripcionProducto, PrecioProducto)
 VALUES('Bizcocho de Dulce de Leche', 15, 'Bizcocho relleno de crema pastelera', 1200.60);
INSERT INTO Producto(NombreProducto, CantidadProducto, DescripcionProducto, PrecioProducto)
 VALUES('Browing', 15, 'Browing sin nueces unidad', 60.00);
INSERT INTO Producto(NombreProducto, CantidadProducto, DescripcionProducto, PrecioProducto)
 VALUES('Browing', 50, 'Browing con nueces unidad', 50.00);
INSERT INTO Producto(NombreProducto, CantidadProducto, DescripcionProducto, PrecioProducto)
 VALUES('Browing', 5, 'Browing sin nueces, Molde (12 unidades)', 600.00);
INSERT INTO Producto(NombreProducto, CantidadProducto, DescripcionProducto, PrecioProducto)
 VALUES('Browing', 6, 'Browing con nueces, Molde (12 unidades)', 500);
INSERT INTO Producto(NombreProducto, CantidadProducto, DescripcionProducto, PrecioProducto)
 VALUES('Bizcocho de Crema de chocolate', 15, 'Bizcocho relleno de crema de chocolate', 1300.60);
INSERT INTO Producto(NombreProducto, CantidadProducto, DescripcionProducto, PrecioProducto)
 VALUES('Bizcocho de chocolate', 15, 'Bizcocho con masa y relleno chocolate', 1600.60);
INSERT INTO Producto(NombreProducto, CantidadProducto, DescripcionProducto, PrecioProducto)
 VALUES('Bizcocho de nutela', 15, 'Bizcocho relleno de crema de nutela', 1400.60); 

INSERT INTO Cliente(NombreCliente, DireccionCliente, TelefonoCliente, CelularCliente, EmailCliente)
 VALUES('Yanna De Leon', 'C\ las Carreras # 33, Canta la Rana, Los Alcarrizos ', '809-888-9999', '829-888-9999', 'yanna@hotmail.com');
INSERT INTO Cliente(NombreCliente, DireccionCliente, TelefonoCliente, CelularCliente, EmailCliente)
 VALUES('Liz Aguasanta', 'C/La Delicia #45, Los Mameyes', '(809)-444-8888', '(809)-333-2222','abc@gmail.com');
INSERT INTO Cliente(NombreCliente, DireccionCliente, TelefonoCliente, CelularCliente, EmailCliente)
 VALUES('Carlos Ramirez', 'C/4 #44, Los Mameyes', '(809)-488-5555', '(809)-020-0202','abc@hotmail.com');
INSERT INTO Cliente(NombreCliente, DireccionCliente, TelefonoCliente, CelularCliente, EmailCliente)
 VALUES('Heleine Nere', 'C/3 #46, Los Mameyes', '(809)-888-5555', '(809)-222-0202','abcd@hotmail.com');
INSERT INTO Cliente(NombreCliente, DireccionCliente, TelefonoCliente, CelularCliente, EmailCliente)
 VALUES('Juan Perez', 'C/4 #45, Los Mameyes', '(809)-388-5555', '(809)-520-0202','abc@yahoo.com');
INSERT INTO Cliente(NombreCliente, DireccionCliente, TelefonoCliente, CelularCliente, EmailCliente)
 VALUES(6, 'Luis Meran', 'C/4 #44, Los Minas', '(809)-444-5555', '(829)-020-0202','abcd@yahoo.com');
INSERT INTO Cliente(NombreCliente, DireccionCliente, TelefonoCliente, CelularCliente, EmailCliente)
 VALUES('Marie Ramirez', 'C/5 #44, Los Minas', '(809)-488-5588', '(829)-220-0202','abcd@gmail.com');
INSERT INTO Cliente(NombreCliente, DireccionCliente, TelefonoCliente, CelularCliente, EmailCliente)
 VALUES('Carlos Guzman', 'C/4 #46, Los Minas', '(809)-488-5599', '(829)-320-0202','carlos@hotmail.com');
INSERT INTO Cliente(NombreCliente, DireccionCliente, TelefonoCliente, CelularCliente, EmailCliente)
 VALUES('Mario Santo', 'C/4ta #44, Los Alcarrizos', '(809)-488-5555', '(809)-020-0202','abc@hotmail.com');
INSERT INTO Cliente(NombreCliente, DireccionCliente, TelefonoCliente, CelularCliente, EmailCliente)
 VALUES(10, 'Carlos Perez', 'C/4ta #44, Los Mameyes', '(809)-488-5555', '(809)-020-0202','abc@hotmail.com');

INSERT INTO Pedido(StatusPedido, ProductoID, ClienteID, Fecha, FechaPedido, AbonoPedido, TotalPedido)
 VALUES('Proceso', 3, 1, DEFAULT, DEFAULT, 0, 2);  
INSERT INTO Pedido(StatusPedido, ProductoID, ClienteID, Fecha, FechaPedido, AbonoPedido, TotalPedido)
 VALUES('Listo', 2, 2, DEFAULT, DEFAULT, 1000.00, 6); 
INSERT INTO Pedido(StatusPedido, ProductoID, ClienteID, Fecha, FechaPedido, AbonoPedido, TotalPedido)
 VALUES('Proceso', 1, 3, DEFAULT, DEFAULT, 0, 3);
INSERT INTO Pedido(StatusPedido, ProductoID, ClienteID, Fecha, FechaPedido, AbonoPedido, TotalPedido)
 VALUES('Proceso', 9, 10, DEFAULT, DEFAULT, 0, 1);
INSERT INTO Pedido(StatusPedido, ProductoID, ClienteID, Fecha, FechaPedido, AbonoPedido, TotalPedido)
 VALUES('Proceso', 4, 9, DEFAULT, DEFAULT, 0, 2);
INSERT INTO Pedido(StatusPedido, ProductoID, ClienteID, Fecha, FechaPedido, AbonoPedido, TotalPedido)
 VALUES('Proceso', 4, 3, DEFAULT, DEFAULT, 0, 3);
INSERT INTO Pedido(StatusPedido, ProductoID, ClienteID, Fecha, FechaPedido, AbonoPedido, TotalPedido)
 VALUES('Proceso', 5, 4, DEFAULT, DEFAULT, 0, 4);
INSERT INTO Pedido(StatusPedido, ProductoID, ClienteID, Fecha, FechaPedido, AbonoPedido, TotalPedido)
 VALUES('Proceso', 6, 5, DEFAULT, DEFAULT, 0, 5);
INSERT INTO Pedido(StatusPedido, ProductoID, ClienteID, Fecha, FechaPedido, AbonoPedido, TotalPedido)
 VALUES('Proceso', 7, 6, DEFAULT, DEFAULT, 0, 6);
INSERT INTO Pedido(StatusPedido, ProductoID, ClienteID, Fecha, FechaPedido, AbonoPedido, TotalPedido)
 VALUES('listo', 8, 3, DEFAULT, DEFAULT, 0, 7);
 
INSERT INTO Lista(ProductoID, PedidoID, CantidadVendida, PrecioVenta)
 VALUES(1, 1, 3, 15); 
INSERT INTO Lista(ProductoID, PedidoID, CantidadVendida, PrecioVenta)
 VALUES(3, 1, 4, 50); 
INSERT INTO Lista(ProductoID, PedidoID, CantidadVendida, PrecioVenta)
 VALUES(1, 1, 5, 30);
INSERT INTO Lista(ProductoID, PedidoID, CantidadVendida, PrecioVenta)
 VALUES(1, 1, 5, 30);
INSERT INTO Lista(ProductoID, PedidoID, CantidadVendida, PrecioVenta)
 VALUES(1, 1, 5, 30);
INSERT INTO Lista(ProductoID, PedidoID, CantidadVendida, PrecioVenta)
 VALUES(1, 1, 5, 30);
INSERT INTO Lista(ProductoID, PedidoID, CantidadVendida, PrecioVenta)
 VALUES(1, 1, 5, 30);
INSERT INTO Lista(ProductoID, PedidoID, CantidadVendida, PrecioVenta)
 VALUES(1, 1, 5, 30);
INSERT INTO Lista(ProductoID, PedidoID, CantidadVendida, PrecioVenta)
 VALUES(1, 1, 5, 30);
INSERT INTO Lista(ProductoID, PedidoID, CantidadVendida, PrecioVenta)
 VALUES(1, 1, 5, 30);


INSERT INTO Usuario(NombreUsuario, ContrasenaUsuario, TipoUsuario)
 VALUES('Yanna Guzman', '12345', 'Cajero');
INSERT INTO Usuario(NombreUsuario, ContrasenaUsuario, TipoUsuario)
 VALUES('Lis Pena', '12345', 'Telefonista');
INSERT INTO Usuario(NombreUsuario, ContrasenaUsuario, TipoUsuario)
 VALUES('Juan Ramirez', '12345', 'Cajero');
INSERT INTO Usuario(NombreUsuario, ContrasenaUsuario, TipoUsuario)
 VALUES('Juan Perez', '12345', 'Telefonista');
INSERT INTO Usuario(NombreUsuario, ContrasenaUsuario, TipoUsuario)
 VALUES('Ana Ruiz', '12345', 'Cajero');
INSERT INTO Usuario(NombreUsuario, ContrasenaUsuario, TipoUsuario)
 VALUES('Juan Santo', '12345', 'Agente de Ventas');
INSERT INTO Usuario(NombreUsuario, ContrasenaUsuario, TipoUsuario)
 VALUES('Juan Nieves', '12345', 'Cajero');
INSERT INTO Usuario(NombreUsuario, ContrasenaUsuario, TipoUsuario)
 VALUES('Lisbeth Martinez', '12345', 'Cocinera');
INSERT INTO Usuario(NombreUsuario, ContrasenaUsuario, TipoUsuario)
 VALUES('Juan Ruiz', '12345', 'Cocinera');
 INSERT INTO Usuario(NombreUsuario, ContrasenaUsuario, TipoUsuario)
 VALUES('Juan Ruiz', '12345', 'Agente de Ventas');