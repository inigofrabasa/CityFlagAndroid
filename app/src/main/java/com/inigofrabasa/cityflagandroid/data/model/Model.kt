package com.inigofrabasa.cityflagandroid.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

object Model {

    data class Applications (
        @SerializedName("feed") val feed : Feed
    )
    data class Feed (
        @SerializedName("entry") val entry : MutableList<Entry>
    )
    data class Entry (
        @SerializedName("id") val id : Id,
        @SerializedName("im:name") val name : Name,
        @SerializedName("im:image") val image : List<Image>,
        @SerializedName("summary") val summary : Summary
    )
    data class Id (
        @SerializedName("label") val label : String
    )
    data class Name (
        @SerializedName("label") val label : String
    )
    data class Image (
        @SerializedName("label") val label : String
    )
    data class Summary (
        @SerializedName("label") val label : String
    )

    @Entity(tableName = "entriesTable")
    data class RoomEntry(
        @PrimaryKey @ColumnInfo(name = "id")
        val id : String,
        val name : String,
        val summary : String,
        val image : String
    )
}