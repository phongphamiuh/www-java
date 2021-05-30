create table if not exists persistent_logins ( 
  username varchar(100) not null, 
  series varchar(64) primary key, 
  token varchar(64) not null, 
  last_used timestamp not null
);

use ecommerceproject;
delete from  user_role;
delete from  roles;
delete from  users;

INSERT INTO roles (id, name) VALUES 
(1, 'ROLE_ADMIN'),
(2, 'ROLE_ACTUATOR'),
(3, 'ROLE_USER')
;

INSERT INTO users (id, email, password, name) VALUES 
(1, 'admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'Admin'),
(3, 'user@gmail.com', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'User');


insert into user_role(user_id, role_id) values
(1,1),
(1,2),
(1,3),
(3,2)
;

insert into ecommerceproject.product_category values(1,'television');
insert into ecommerceproject.product_category values(2,'fan');
insert into ecommerceproject.product_category values(3,'refrigerator');
insert into ecommerceproject.product_category values(4,'cleaner');
insert into ecommerceproject.product_category values(5,'airconditioner');

select * from ecommerceproject.discount;

insert into ecommerceproject.discount values(1,'1',20,1);
insert into ecommerceproject.discount values(2,'1',30,1);
insert into ecommerceproject.discount values(3,'1',40,1);

select * from ecommerceproject.brand;

insert into ecommerceproject.brand values(1,'sony');
insert into ecommerceproject.brand values(2,'samsung');
insert into ecommerceproject.brand values(3,'lg');
insert into ecommerceproject.brand values(4,'tcl');
insert into ecommerceproject.brand values(5,'mobell');
insert into ecommerceproject.brand values(6,'casper');
insert into ecommerceproject.brand values(7,'ffalcon');

select * from ecommerceproject.televition_type;

insert into ecommerceproject.televition_type values(1,'Smart Tivi');
insert into ecommerceproject.televition_type values(2,'Tivi OLED');
insert into ecommerceproject.televition_type values(3,'Tivi QLED');
insert into ecommerceproject.televition_type values(4,'Tivi Android');
insert into ecommerceproject.televition_type values(5,'Tivi NanoCell');

select * from ecommerceproject.resolution;

insert into ecommerceproject.resolution values(1,'8K');
insert into ecommerceproject.resolution values(2,'4K');
insert into ecommerceproject.resolution values(3,'FULL HD');
insert into ecommerceproject.resolution values(4,'HD');

select * from ecommerceproject.television;

insert into ecommerceproject.television(id,product_line,name,sku,stock_total,price,on_sale_price,brand_id,type_id,size,resolution_id,source,description)
values(1,'NANO79TND','Smart Tivi NanoCell LG 4K 43 inch','1',10,5000000,6000000,3,1,'43',2,'Indonesia','Dep');

insert into ecommerceproject.television(id,product_line,name,sku,stock_total,price,on_sale_price,brand_id,type_id,size,resolution_id,source,description)
values(2,'NANO79TND','Smart Tivi NanoCell LG 4K 50 inch','2',10,6000000,7000000,3,1,'50',2,'Indonesia','Dep');

insert into ecommerceproject.television(id,product_line,name,sku,stock_total,price,on_sale_price,brand_id,type_id,size,resolution_id,source,description)
values(3,'NANO79TND','Smart Tivi NanoCell LG 4K 55 inch','3',3,7000000,8000000,3,1,'55',2,'Indonesia','Dep');

insert into ecommerceproject.television(id,product_line,name,sku,stock_total,price,on_sale_price,brand_id,type_id,size,resolution_id,source,description)
values(4,'NANO79TND','Smart Tivi NanoCell LG 4K 55 inch','4',3,7000000,8000000,3,1,'55',2,'Indonesia','Dep');

insert into ecommerceproject.television(id,product_line,name,sku,stock_total,price,on_sale_price,brand_id,type_id,size,resolution_id,source,description)
values(5,'TU8100','Smart Tivi Samsung 4K 50 inch','5',3,4000000,5000000,2,2,'65',3,'Việt Nam','Dep');

insert into ecommerceproject.television(id,product_line,name,sku,stock_total,price,on_sale_price,brand_id,type_id,size,resolution_id,source,description)
values(6,'P715','Android Tivi TCL 50 inch','6',4,3000000,4000000,4,4,'50',4,'Indonesia','Dep');

insert into ecommerceproject.product(product_id,category_id,id,sku,url,long_desc,date_created,last_updated,unlimited)
values(1,1,1,'1','url',null,null,null,2);

insert into ecommerceproject.product(product_id,category_id,id,sku,url,long_desc,date_created,last_updated,unlimited)
values(2,1,2,'2','url',null,null,null,2);

insert into ecommerceproject.product(product_id,category_id,id,sku,url,long_desc,date_created,last_updated,unlimited)
values(3,1,3,'3','url',null,null,null,2);

insert into ecommerceproject.product(product_id,category_id,id,sku,url,long_desc,date_created,last_updated,unlimited)
values(4,1,4,'4','url',null,null,null,2);

insert into ecommerceproject.product(product_id,category_id,id,sku,url,long_desc,date_created,last_updated,unlimited)
values(5,1,5,'5','url',null,null,null,2);

insert into ecommerceproject.product(product_id,category_id,id,sku,url,long_desc,date_created,last_updated,unlimited)
values(6,1,6,'6','url',null,null,null,2);


