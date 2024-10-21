package com.example.gastrotrack_appmovil.communication

import com.example.gastrotrack_appmovil.models.Report

data class ReportApiResponse(
    val reportName: String,
    val reportDescription: String,
    val reportDate: String
) {
    fun toReport(): Report {
        return Report(
            reportName = reportName,
            reportDescription = reportDescription,
            reportDate = reportDate
        )
    }
}