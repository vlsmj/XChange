package com.blueberryprojects.xchange.featurexchange.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Balance(
    @PrimaryKey
    var currency: String = "",
    var balance: Double = 0.00,
) : Serializable