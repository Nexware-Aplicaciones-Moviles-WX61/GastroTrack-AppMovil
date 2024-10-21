package com.example.gastrotrack_appmovil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.models.Report

class ReportAdapter(
    private var reportList: List<Report>,
    private val onDownloadClick: (Report) -> Unit
) : RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {

    inner class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDateReport: TextView = itemView.findViewById(R.id.tvDateReport)
        private val tvReportName: TextView = itemView.findViewById(R.id.tvReportName)
        private val tvReportDescription: TextView = itemView.findViewById(R.id.tvReportDescription)
        private val ivDownloadReport: ImageButton = itemView.findViewById(R.id.ivDownloadReport)

        fun bind(report: Report) {
            tvReportName.text = report.reportName
            tvReportDescription.text = report.reportDescription
            tvDateReport.text = report.reportDate

            ivDownloadReport.setOnClickListener {
                onDownloadClick(report)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_report, parent, false)
        return ReportViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.bind(reportList[position])
    }

    override fun getItemCount(): Int {
        return reportList.size
    }

}