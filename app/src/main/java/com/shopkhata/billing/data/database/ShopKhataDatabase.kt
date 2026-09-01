package com.shopkhata.billing.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shopkhata.billing.data.database.dao.*
import com.shopkhata.billing.data.database.entity.*

@Database(
    entities = [
        ProductEntity::class,
        CustomerEntity::class,
        InvoiceEntity::class,
        InvoiceItemEntity::class,
        CustomerTransactionEntity::class,
        PaymentEntity::class,
        SupplierEntity::class,
        SupplierTransactionEntity::class,
        StockTransactionEntity::class,
        ShopSettingsEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ShopKhataDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun customerDao(): CustomerDao
    abstract fun invoiceDao(): InvoiceDao
    abstract fun invoiceItemDao(): InvoiceItemDao
    abstract fun customerTransactionDao(): CustomerTransactionDao
    abstract fun paymentDao(): PaymentDao
    abstract fun supplierDao(): SupplierDao
    abstract fun supplierTransactionDao(): SupplierTransactionDao
    abstract fun stockTransactionDao(): StockTransactionDao
    abstract fun shopSettingsDao(): ShopSettingsDao
}

class Converters {
    @androidx.room.TypeConverter
    fun fromBigDecimal(value: java.math.BigDecimal?): String? {
        return value?.toPlainString()
    }

    @androidx.room.TypeConverter
    fun toBigDecimal(value: String?): java.math.BigDecimal? {
        return value?.let { java.math.BigDecimal(it) }
    }
}