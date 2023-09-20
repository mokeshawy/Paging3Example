package com.example.paging3example.features.fragments.home_fargment.data.response


import com.google.gson.annotations.SerializedName

data class GamesResponse(
    @SerializedName("date")
    val date: String,
    @SerializedName("home_team")
    val homeTeam: HomeTeam,
    @SerializedName("home_team_score")
    val homeTeamScore: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("period")
    val period: Int,
    @SerializedName("postseason")
    val postseason: Boolean,
    @SerializedName("season")
    val season: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("visitor_team")
    val visitorTeam: VisitorTeam,
    @SerializedName("visitor_team_score")
    val visitorTeamScore: Int
)