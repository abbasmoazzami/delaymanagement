package ir.snapfood.delaymanagement.application.implementation.vendor

import ir.snapfood.delaymanagement.domain.vendor.Vendor
import jakarta.persistence.*

@Entity
@Table(name = "vendors")
data class JpaVendor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Int = 0,
) : Vendor()