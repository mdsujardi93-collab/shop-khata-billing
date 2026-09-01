package com.shopkhata.billing.data.database.dao

import androidx.room.*
import com.shopkhata.billing.data.database.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity): Long

    @Update
    suspend fun updateProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Query("SELECT * FROM products WHERE id = :id")
    fun getProductById(id: Long): Flow<ProductEntity?>

    @Query("SELECT * FROM products WHERE isActive = 1 ORDER BY name ASC")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE name LIKE :query AND isActive = 1 ORDER BY name ASC")
    fun searchProducts(query: String): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE sku LIKE :sku AND isActive = 1 LIMIT 1")
    fun getProductBySku(sku: String): Flow<ProductEntity?>

    @Query("SELECT * FROM products WHERE currentStock < minimumStock AND isActive = 1")
    fun getLowStockProducts(): Flow<List<ProductEntity>>

    @Query("UPDATE products SET currentStock = :newStock, updatedAt = :timestamp WHERE id = :productId")
    suspend fun updateStock(productId: Long, newStock: java.math.BigDecimal, timestamp: Long)

    @Query("SELECT COUNT(*) FROM products WHERE isActive = 1")
    fun getTotalProductCount(): Flow<Int>
}

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: com.shopkhata.billing.data.database.entity.CustomerEntity): Long

    @Update
    suspend fun updateCustomer(customer: com.shopkhata.billing.data.database.entity.CustomerEntity)

    @Delete
    suspend fun deleteCustomer(customer: com.shopkhata.billing.data.database.entity.CustomerEntity)

    @Query("SELECT * FROM customers WHERE id = :id")
    fun getCustomerById(id: Long): Flow<com.shopkhata.billing.data.database.entity.CustomerEntity?>

    @Query("SELECT * FROM customers WHERE isActive = 1 ORDER BY name ASC")
    fun getAllCustomers(): Flow<List<com.shopkhata.billing.data.database.entity.CustomerEntity>>

    @Query("SELECT * FROM customers WHERE name LIKE :query AND isActive = 1 ORDER BY name ASC")
    fun searchCustomers(query: String): Flow<List<com.shopkhata.billing.data.database.entity.CustomerEntity>>

    @Query("SELECT * FROM customers WHERE mobile LIKE :mobile AND isActive = 1 LIMIT 1")
    fun getCustomerByMobile(mobile: String): Flow<com.shopkhata.billing.data.database.entity.CustomerEntity?>

    @Query("SELECT COUNT(*) FROM customers WHERE isActive = 1")
    fun getTotalCustomerCount(): Flow<Int>
}

@Dao
interface InvoiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInvoice(invoice: com.shopkhata.billing.data.database.entity.InvoiceEntity): Long

    @Update
    suspend fun updateInvoice(invoice: com.shopkhata.billing.data.database.entity.InvoiceEntity)

    @Query("SELECT * FROM invoices WHERE id = :id")
    fun getInvoiceById(id: Long): Flow<com.shopkhata.billing.data.database.entity.InvoiceEntity?>

    @Query("SELECT * FROM invoices WHERE invoiceStatus = 'Active' ORDER BY invoiceDate DESC")
    fun getAllInvoices(): Flow<List<com.shopkhata.billing.data.database.entity.InvoiceEntity>>

    @Query("SELECT * FROM invoices WHERE customerId = :customerId AND invoiceStatus = 'Active' ORDER BY invoiceDate DESC")
    fun getCustomerInvoices(customerId: Long): Flow<List<com.shopkhata.billing.data.database.entity.InvoiceEntity>>

    @Query("SELECT * FROM invoices WHERE invoiceNumber = :invoiceNumber")
    fun getInvoiceByNumber(invoiceNumber: String): Flow<com.shopkhata.billing.data.database.entity.InvoiceEntity?>

    @Query("SELECT * FROM invoices WHERE invoiceDate >= :startDate AND invoiceDate <= :endDate AND invoiceStatus = 'Active' ORDER BY invoiceDate DESC")
    fun getInvoicesByDateRange(startDate: Long, endDate: Long): Flow<List<com.shopkhata.billing.data.database.entity.InvoiceEntity>>

    @Query("SELECT COUNT(*) FROM invoices WHERE invoiceStatus = 'Active'")
    fun getTotalInvoiceCount(): Flow<Int>
}

@Dao
interface InvoiceItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInvoiceItem(item: com.shopkhata.billing.data.database.entity.InvoiceItemEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInvoiceItems(items: List<com.shopkhata.billing.data.database.entity.InvoiceItemEntity>)

    @Query("SELECT * FROM invoice_items WHERE invoiceId = :invoiceId")
    fun getInvoiceItems(invoiceId: Long): Flow<List<com.shopkhata.billing.data.database.entity.InvoiceItemEntity>>

    @Query("DELETE FROM invoice_items WHERE invoiceId = :invoiceId")
    suspend fun deleteInvoiceItems(invoiceId: Long)
}

@Dao
interface CustomerTransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: com.shopkhata.billing.data.database.entity.CustomerTransactionEntity): Long

    @Query("SELECT * FROM customer_transactions WHERE customerId = :customerId ORDER BY createdAt DESC")
    fun getCustomerTransactions(customerId: Long): Flow<List<com.shopkhata.billing.data.database.entity.CustomerTransactionEntity>>
}

@Dao
interface PaymentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayment(payment: com.shopkhata.billing.data.database.entity.PaymentEntity): Long

    @Update
    suspend fun updatePayment(payment: com.shopkhata.billing.data.database.entity.PaymentEntity)

    @Query("SELECT * FROM payments WHERE id = :id")
    fun getPaymentById(id: Long): Flow<com.shopkhata.billing.data.database.entity.PaymentEntity?>

    @Query("SELECT * FROM payments WHERE customerId = :customerId ORDER BY paymentDate DESC")
    fun getCustomerPayments(customerId: Long): Flow<List<com.shopkhata.billing.data.database.entity.PaymentEntity>>

    @Query("SELECT SUM(amount) FROM payments WHERE paymentDate >= :startDate AND paymentDate <= :endDate")
    fun getTotalPaymentsByDateRange(startDate: Long, endDate: Long): Flow<java.math.BigDecimal?>
}

@Dao
interface SupplierDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSupplier(supplier: com.shopkhata.billing.data.database.entity.SupplierEntity): Long

    @Update
    suspend fun updateSupplier(supplier: com.shopkhata.billing.data.database.entity.SupplierEntity)

    @Delete
    suspend fun deleteSupplier(supplier: com.shopkhata.billing.data.database.entity.SupplierEntity)

    @Query("SELECT * FROM suppliers WHERE isActive = 1 ORDER BY name ASC")
    fun getAllSuppliers(): Flow<List<com.shopkhata.billing.data.database.entity.SupplierEntity>>

    @Query("SELECT * FROM suppliers WHERE name LIKE :query AND isActive = 1 ORDER BY name ASC")
    fun searchSuppliers(query: String): Flow<List<com.shopkhata.billing.data.database.entity.SupplierEntity>>
}

@Dao
interface SupplierTransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: com.shopkhata.billing.data.database.entity.SupplierTransactionEntity): Long

    @Query("SELECT * FROM supplier_transactions WHERE supplierId = :supplierId ORDER BY createdAt DESC")
    fun getSupplierTransactions(supplierId: Long): Flow<List<com.shopkhata.billing.data.database.entity.SupplierTransactionEntity>>
}

@Dao
interface StockTransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStockTransaction(transaction: com.shopkhata.billing.data.database.entity.StockTransactionEntity): Long

    @Query("SELECT * FROM stock_transactions WHERE productId = :productId ORDER BY createdAt DESC")
    fun getProductStockTransactions(productId: Long): Flow<List<com.shopkhata.billing.data.database.entity.StockTransactionEntity>>
}

@Dao
interface ShopSettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSettings(settings: com.shopkhata.billing.data.database.entity.ShopSettingsEntity)

    @Update
    suspend fun updateSettings(settings: com.shopkhata.billing.data.database.entity.ShopSettingsEntity)

    @Query("SELECT * FROM shop_settings WHERE id = 1")
    fun getSettings(): Flow<com.shopkhata.billing.data.database.entity.ShopSettingsEntity?>
}