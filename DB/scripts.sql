CREATE TABLE "Account" (
	"id"	INTEGER NOT NULL,
	"username"	TEXT NOT NULL,
	"password"	TEXT NOT NULL,
	"role"	INTEGER NOT NULL,
	"status"	TEXT,
	PRIMARY KEY("id")
)

CREATE TABLE "Bill" (
	"id"	INTEGER NOT NULL,
	"userId"	INTEGER NOT NULL,
	"cartId"	INTEGER NOT NULL,
	"date"	TEXT NOT NULL,
	"status"	TEXT,
	PRIMARY KEY("id"),
	FOREIGN KEY("cartId") REFERENCES "Cart"("id"),
	FOREIGN KEY("userId") REFERENCES "User"("id")
)

CREATE TABLE "Cart" (
	"id"	INTEGER NOT NULL,
	"userId"	INTEGER NOT NULL,
	"productId"	INTEGER NOT NULL,
	"address"	TEXT NOT NULL,
	"status"	TEXT,
	PRIMARY KEY("id"),
	FOREIGN KEY("userId") REFERENCES "User"("id"),
	FOREIGN KEY("productId") REFERENCES "Product"("id")
)

CREATE TABLE "Discount" (
	"id"	INTEGER NOT NULL,
	"productId"	INTEGER NOT NULL,
	"type"	TEXT,
	"expirationDate"	TEXT,
	"status"	TEXT,
	PRIMARY KEY("id"),
	FOREIGN KEY("productId") REFERENCES "Product"("id")
)

CREATE TABLE "Notification" (
	"id"	INTEGER NOT NULL,
	"type"	TEXT NOT NULL,
	"detail"	TEXT,
	"shortDetail"	TEXT,
	"status"	TEXT,
	PRIMARY KEY("id")
)

CREATE TABLE "Product" (
	"id"	INTEGER NOT NULL,
	"type"	TEXT NOT NULL,
	"name"	TEXT NOT NULL,
	"price"	REAL,
	"image"	TEXT,
	"detail"	TEXT,
	"star"	REAL,
	"status"	TEXT,
	PRIMARY KEY("id")
)

CREATE TABLE "ProductType" (
	"id"	INTEGER NOT NULL,
	"typeName"	TEXT,
	"status"	TEXT,
	PRIMARY KEY("id")
)

CREATE TABLE "Promo" (
	"id"	INTEGER NOT NULL,
	"productId"	INTEGER NOT NULL,
	"type"	TEXT NOT NULL,
	"fromDate"	TEXT NOT NULL,
	"toDate"	TEXT NOT NULL,
	"status"	TEXT,
	PRIMARY KEY("id")
)

CREATE TABLE "Review" (
	"id"	INTEGER NOT NULL,
	"userID"	INTEGER NOT NULL,
	"productID"	INTEGER NOT NULL,
	"content"	TEXT,
	"time"	TEXT,
	"status"	TEXT,
	PRIMARY KEY("id"),
	FOREIGN KEY("userID") REFERENCES "User"("id"),
	FOREIGN KEY("productID") REFERENCES "Product"("id")
)

CREATE TABLE "Role" (
	"id"	INTEGER NOT NULL,
	"permission"	TEXT NOT NULL,
	PRIMARY KEY("id")
)

CREATE TABLE "Store" (
	"id"	INTEGER NOT NULL,
	"name"	TEXT NOT NULL,
	"phone"	TEXT NOT NULL,
	"image"	TEXT,
	"address"	TEXT,
	"status"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
)

CREATE TABLE "User" (
	"id"	INTEGER NOT NULL,
	"userId"	INTEGER NOT NULL,
	"fullname"	TEXT NOT NULL,
	"email"	TEXT NOT NULL,
	"phone"	TEXT,
	"avt"	TEXT,
	"facebook"	TEXT,
	"google"	TEXT,
	"zalo"	TEXT,
	"status"	TEXT,
	FOREIGN KEY("userId") REFERENCES "Account"("id"),
	PRIMARY KEY("id")
)

CREATE TABLE "UserNotify" (
	"id"	INTEGER NOT NULL,
	"userId"	INTEGER NOT NULL,
	"notificationId"	INTEGER NOT NULL COLLATE UTF16CI,
	PRIMARY KEY("id"),
	FOREIGN KEY("notificationId") REFERENCES "Notification"("id"),
	FOREIGN KEY("userId") REFERENCES "User"("id")
)