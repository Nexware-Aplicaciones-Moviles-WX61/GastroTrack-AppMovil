package com.example.gastrotrack_appmovil.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reports")
data class Report(
    @PrimaryKey(autoGenerate = true)
    val reportId: Int? = null,
    @ColumnInfo(name = "report_name")
    val reportName: String,
    @ColumnInfo(name = "report_description")
    val reportDescription: String? = null,
    @ColumnInfo(name = "report_date")
    val reportDate: String? = null
)