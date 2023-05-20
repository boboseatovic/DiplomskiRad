package hr.bornaseatovic.myapplication.data.parsers

import hr.bornaseatovic.myapplication.data.model.local.ProductionDb
import hr.bornaseatovic.myapplication.data.model.presentation.*
import hr.bornaseatovic.myapplication.data.model.remote.pvGIS.PVCalculationsResponse

fun PVCalculationsResponse.toProductionDb() = ProductionDb(
    monthlyProduction = outputs.monthly.fixed.map { it.eM },
    yearlyProduction = outputs.totals.fixed.eY
)

fun ProductionDb.toProductionPresentation() = ProductionPresentation(
    monthlyProduction = monthlyProduction,
    yearlyProduction = yearlyProduction
)

