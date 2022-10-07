package com.blueberryprojects.xchange.featurexchange.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Balance(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var currency: String = "",
    var balance: Double = 0.00,
) : Serializable