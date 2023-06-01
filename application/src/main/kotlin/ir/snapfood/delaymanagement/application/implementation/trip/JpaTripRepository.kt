package ir.snapfood.delaymanagement.application.implementation.trip

import ir.snapfood.delaymanagement.domain.trip.entity.TripRepository
import org.springframework.data.jpa.repository.JpaRepository

interface JpaTripRepository : TripRepository, JpaRepository<JpaTrip, Int>