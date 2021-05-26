CREATE INDEX "AccountIndex" ON "Account" (
	"id"	ASC
)

CREATE INDEX "BillIndex" ON "Bill" (
	"id"	DESC
)

CREATE INDEX "CartIndex" ON "Cart" (
	"id"	DESC
)

CREATE INDEX "DiscountIndex" ON "Discount" (
	"id"	DESC
)

CREATE INDEX "NotificationIndex" ON "Notification" (
	"id"	DESC
)

CREATE INDEX "ProductIndex" ON "Product" (
	"id"	ASC
)

CREATE INDEX "ProductTypeIndex" ON "ProductType" (
	"id"	ASC
)

CREATE INDEX "PromoIndex" ON "Promo" (
	"id"	DESC
)

CREATE INDEX "ReviewIndex" ON "Review" (
	"id"	DESC
)

CREATE INDEX "RoleIndex" ON "Role" (
	"id"	ASC
)

CREATE INDEX "StoreIndex" ON "Store" (
	"id"	ASC
)

CREATE INDEX "UserIndex" ON "User" (
	"id"	ASC
)

CREATE INDEX "UserNotifyIndex" ON "UserNotify" (
	"id"	DESC
)