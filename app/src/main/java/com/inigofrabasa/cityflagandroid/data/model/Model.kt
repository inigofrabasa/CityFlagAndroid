package com.inigofrabasa.cityflagandroid.data.model

import com.google.gson.annotations.SerializedName

object Model {

    data class Applications (
        @SerializedName("feed") val feed : Feed
    )
    data class Feed (
        @SerializedName("entry") val entry : MutableList<Entry>
    )
    data class Entry (
        @SerializedName("im:name") val name : Name,
        @SerializedName("im:image") val image : List<Image>,
        @SerializedName("summary") val summary : Summary,
        @SerializedName("im:price") val price : Price,
        @SerializedName("im:contentType") val contentType : ContentType,
        @SerializedName("rights") val rights : Rights,
        @SerializedName("title") val title : Title,
        @SerializedName("link") val link : List<Link>,
        @SerializedName("id") val id : Id,
        @SerializedName("im:artist") val artist : Artist,
        @SerializedName("category") val category : Category,
        @SerializedName("im:releaseDate") val releaseDate : ReleaseDate
    )
    data class Attributes (
        @SerializedName("rel") val rel : String,
        @SerializedName("type") val type : String,
        @SerializedName("href") val href : String
    )
    data class Name (
        @SerializedName("label") val label : String
    )
    data class Image (
        @SerializedName("label") val label : String,
        @SerializedName("attributes") val attributes : Attributes
    )
    data class Summary (
        @SerializedName("label") val label : String
    )
    data class Price (
        @SerializedName("label") val label : String,
        @SerializedName("attributes") val attributes : Attributes
    )
    data class ContentType (
        @SerializedName("attributes") val attributes : Attributes
    )
    data class Rights (
        @SerializedName("label") val label : String
    )
    data class Title (
        @SerializedName("label") val label : String
    )
    data class Link (
        @SerializedName("attributes") val attributes : Attributes
    )
    data class Id (
        @SerializedName("label") val label : String
    )
    data class Artist (
        @SerializedName("label") val label : String,
        @SerializedName("attributes") val attributes : Attributes
    )
    data class Category (
        @SerializedName("attributes") val attributes : Attributes
    )
    data class ReleaseDate (
        @SerializedName("label") val label : String,
        @SerializedName("attributes") val attributes : Attributes
    )
}