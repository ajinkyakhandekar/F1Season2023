package com.ajinkya.formula1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.domain.model.Constructor

@Entity(tableName = Constant.CONSTRUCTOR_TABLE)
data class ConstructorEntity(
    @PrimaryKey
    var position: String = "",
    var points: String = "",
    var constructorName: String = ""
) {
    fun mapConstructor(): Constructor {
        return Constructor(
            position = position,
            points = points,
            constructorName = constructorName
        )
    }
}
