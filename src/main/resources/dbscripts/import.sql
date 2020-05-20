INSERT INTO product (product_id, code, description, name, price) VALUES ('1', '1', 'Telefon Dual SIM cu sistem de operare Android, ecran tactil 6.28 inch, memorie internă 128 GB, cameră dublă cu rezoluţia 16 și 20 megapixeli, GPS, senzor de amprentă digitală, susține tehnologia 4G', 'Samsung Galaxy', 9000);
INSERT INTO product (product_id, code, description, name, price) VALUES ('2', '2', 'Căşti cu microfon, cu fir, culoare neagră', 'SVEN', 250);
INSERT INTO product (product_id, code, description, name, price) VALUES ('3', '3', 'Laptop cu ecran IPS de 13.3 inch, procesor Intel Core i5, memorie 8 GB DDR3, solid state drive 256 GB, greutate 1.37 kg', 'Apple MacBook Pro', 12000);
INSERT INTO product (product_id, code, description, name, price) VALUES ('4', '4', 'Tabletă cu ecran tactil 10.1 inch și sistem operare Android 6.0, rezoluție ecran 1280 x 800 pixeli, modem 4G incorporat, procesor MediaTek MT8735 de 1.3 GHz, spaţiu stocare de 16 GB şi slot microSD, rezoluție cameră 5 megapixeli, până la 10 ore autonomie', 'Acer Iconia One', 5000);
INSERT INTO product (product_id, code, description, name, price) VALUES ('5', '5', 'Căşti cu microfon, cu fir, culoare negră', 'ACME', 200);
INSERT INTO product (product_id, code, description, name, price) VALUES ('6', '6', 'Laptop cu ecran IPS de 15.6 inch, procesor Intel Core i5-8300H, memorie 16 GB DDR4, hard disk 1 TB, solid state drive 128 GB, placă video NVIDIA GeForce GTX 1050 Ti de 4 GB, greutate 2.4 kg', 'Acer Aspire', 14000);

INSERT INTO category (id, code, description, name) VALUES ('1', '1', 'Electronice', 'Produse electronice');
INSERT INTO category (id, code, description, name) VALUES ('2', '2', 'Electromontaj', 'Produse de electromontaj');

UPDATE product SET image_url = 'products/product_1.png' WHERE (product_id = '1');
UPDATE product SET image_url = 'products/product_2.png' WHERE (product_id = '2');
UPDATE product SET image_url = 'products/product_3.png' WHERE (product_id = '3');
UPDATE product SET image_url = 'products/product_4.png' WHERE (product_id = '4');
UPDATE product SET image_url = 'products/product_5.png' WHERE (product_id = '5');
UPDATE product SET image_url = 'products/product_6.png' WHERE (product_id = '6');