package com.example.gastrotrack_appmovil.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gastrotrack_appmovil.models.Report

@Dao
interface ReportDAO {
    @Insert
    fun insertReport(report: Report)

    @Query("SELECT * FROM reports")
    fun getAllReports(): List<Report>
}