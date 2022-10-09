package com.blueberryprojects.xchange.featurexchange.data.remote.dto

import com.blueberryprojects.xchange.featurexchange.domain.model.Rate
import com.google.gson.annotations.SerializedName

data class RateDto(
    @SerializedName("result") val result: Double,
)

fun RateDto.toRate(): Rate {
    return Rate(
        result = this.result
    )
}