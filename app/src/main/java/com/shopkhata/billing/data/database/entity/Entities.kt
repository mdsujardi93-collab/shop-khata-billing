package com.shopkhata.billing.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String = "",
    val imageUri: String = "",
    val mrp: BigDecimal,
    val sellingRate: BigDecimal,
    val purchaseRate: BigDecimal,
    val unit: String,
    val currentStock: BigDecimal = BigDecimal.ZERO,
    val minimumStock: BigDecimal = BigDecimal.ZERO,
    val sku: String = "",
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "customers")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val mobile: String = "",
    val address: String = "",
    val customerCode: String = "",
    val gstin: String = "",
    val openingBalance: BigDecimal = BigDecimal.ZERO,
    val notes: String = "",
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "invoices")
data class InvoiceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val invoiceNumber: String,
    val customerId: Long,
    val invoiceDate: Long,
    val subtotal: BigDecimal,
    val discount: BigDecimal = BigDecimal.ZERO,
    val additionalCharges: BigDecimal = BigDecimal.ZERO,
    val grandTotal: BigDecimal,
    val paidAmount: BigDecimal = BigDecimal.ZERO,
    val pendingAmount: BigDecimal,
    val paymentMethod: String = "Cash",
    val status: String = "Pending", // Paid, Partially Paid, Pending
    val invoiceStatus: String = "Active", // Active, Cancelled
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "invoice_items")
data class InvoiceItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val invoiceId: Long,
    val productId: Long,
    val productName: String,
    val quantity: BigDecimal,
    val unit: String,
    val mrp: BigDecimal,
    val rate: BigDecimal,
    val amount: BigDecimal,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "customer_transactions")
data class CustomerTransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val customerId: Long,
    val transactionType: String, // Invoice, Payment, Adjustment
    val amount: BigDecimal,
    val referenceId: Long = 0, // Invoice ID or Payment ID
    val runningBalance: BigDecimal,
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "payments")
data class PaymentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val customerId: Long,
    val invoiceId: Long = 0,
    val receiptNumber: String,
    val amount: BigDecimal,
    val paymentMethod: String,
    val paymentDate: Long,
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "suppliers")
data class SupplierEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val mobile: String = "",
    val address: String = "",
    val openingBalance: BigDecimal = BigDecimal.ZERO,
    val notes: String = "",
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "supplier_transactions")
data class SupplierTransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val supplierId: Long,
    val transactionType: String, // Purchase, Payment, Adjustment
    val amount: BigDecimal,
    val referenceId: Long = 0,
    val runningBalance: BigDecimal,
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "stock_transactions")
data class StockTransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: Long,
    val transactionType: String, // StockIn, StockOut, Adjustment
    val quantity: BigDecimal,
    val unit: String,
    val referenceId: Long = 0, // Invoice ID or Manual
    val notes: String = "",
    val runningStock: BigDecimal,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "shop_settings")
data class ShopSettingsEntity(
    @PrimaryKey
    val id: Int = 1,
    val shopName: String = "My Shop",
    val ownerName: String = "",
    val shopAddress: String = "",
    val mobileNumber: String = "",
    val gstin: String = "",
    val logoUri: String = "",
    val upiId: String = "",
    val bankName: String = "",
    val bankAccountNumber: String = "",
    val ifsc: String = "",
    val invoicePrefix: String = "INV-",
    val nextInvoiceNumber: Long = 1,
    val currency: String = "₹ INR",
    val allowNegativeStock: Boolean = false,
    val updatedAt: Long = System.currentTimeMillis()
)