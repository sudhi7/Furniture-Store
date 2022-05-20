create database furniture_store;

use furniture_store;

CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `password` char(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `category` (
`id` int NOT NULL AUTO_INCREMENT,
`name` varchar(45) NOT NULL,
 PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `category` VALUES (1, 'Beds');
INSERT INTO `category` VALUES (2, 'Center Tables');
INSERT INTO `category` VALUES (3, 'Study Tables');
INSERT INTO `category` VALUES (4, 'Office Chairs');
INSERT INTO `category` VALUES (5, 'Book Shelves');
INSERT INTO `category` VALUES (6, 'Wardrobes');
INSERT INTO `category` VALUES (7, 'Shoe Racks');
INSERT INTO `category` VALUES (8, 'Sofa Sets');
INSERT INTO `category` VALUES (9, 'TV Units');
INSERT INTO `category` VALUES (10, 'Dining Sets');
INSERT INTO `category` VALUES (11, 'Dressing Tables');
INSERT INTO `category` VALUES (12, 'Side Tables');
INSERT INTO `category` VALUES (13, 'Study Lamps');

CREATE TABLE `item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `category_id` int NOT NULL,
  `brand` varchar(45) NOT NULL,
  `height` int NOT NULL,
  `width` int NOT NULL,
  `depth` int NOT NULL,
  `weight` int NOT NULL,
  `colour` varchar(5) NOT NULL,
  `warranty` int NOT NULL,
  `material` varchar(45) NOT NULL,
  `cost` int NOT NULL,
  `imageURL` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `category_mapping` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `item` VALUES(1,'Copal Queen size Bed with Box Storage in Wenge Finish', 1, 'Trevi Furniture', 35, 62, 85, 95, 'Brown', 12, 'Engineered Wood', 18786, 'https://ii1.pepperfry.com/media/catalog/product/c/o/800x880/copal-queen-size-bed-with-box-storage-in-wenge-finish-by-trevi-furniture-copal-queen-size-bed-with-b-voafia.jpg');
INSERT INTO `item` VALUES(2,'Dolce Bedroom Set in Black Colour', 1, 'Iberba', 70, 87, 85, 240, 'Brown',12, 'Engineered Wood', 182400, 'https://ii1.pepperfry.com/media/catalog/product/d/o/800x880/dolce-bedroom-set-in-black-colour-by-iberba-dolce-bedroom-set-in-black-colour-by-iberba-ys1pbc.jpg');

INSERT INTO `item` VALUES(4, 'Arlo Upholstered Center Table', 2, 'Muebles Casa', 17, 30, 30, 9, 'Cream', 12, 'Leatherette', 8150, 'https://ii1.pepperfry.com/media/catalog/product/a/r/800x880/arlo-upholstered-center-table-by-muebles-casa-arlo-upholstered-center-table-by-muebles-casa-lzgslz.jpg');

INSERT INTO `item` VALUES(5, 'SOS Pony Study Table in Irish Cream & Black Finish', 3, 'Spacewood Office Solutions', 30, 36, 19, 8, 'Black', 36, 'Stainless Steel', 3927, 'https://ii1.pepperfry.com/media/catalog/product/s/o/800x880/sos-pony-writing-table-in-irish-cr-me-and-black-colour-by-spacewood-sos-pony-writing-table-in-irish--0ik2x6.jpg');
INSERT INTO `item` VALUES(6, 'Guide Writing Table in Frosty White Colour', 3, 'A GLOBIA CREATIONS', 47, 59, 18, 39, 'White', 12, 'Engineered Wood', 9132, 'https://ii1.pepperfry.com/media/catalog/product/g/u/800x880/guide-04-study-table-guide-04-study-table-sxdb7q.jpg');

INSERT INTO `item` VALUES(7, 'Style Ergonomic Chair in Black Colour', 4, 'VOF', 39, 23, 19, 17, 'Black', 12, 'Breathable Mesh', 3875, 'https://ii1.pepperfry.com/media/catalog/product/s/t/800x880/style-ergonomic-chair-in-black-colour-by-vof-style-ergonomic-chair-in-black-colour-by-vof-xxjewo.jpg');
INSERT INTO `item` VALUES(8, 'SpaceX High Back Executive Chair in Brown Colour', 4,'Rose Furniture', 49, 20, 20, 17, 'Brown', 12, 'Leatherette', 8639, 'https://ii1.pepperfry.com/media/catalog/product/s/p/800x880/spacex-high-back-executive-chair-in-brown-colour-by-rose-furniture-spacex-high-back-executive-chair--4jekou.jpg');

CREATE TABLE `customer_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `is_placed` boolean NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `customer_mapping` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `order_item` (
  `item_id` int NOT NULL,
  `order_id` int NOT NULL,
  `quantity` int NOT NULL DEFAULT 1,
  PRIMARY KEY (`item_id`,`order_id`),
  CONSTRAINT `item_mapping` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `order_mapping` FOREIGN KEY (`order_id`) REFERENCES `customer_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;