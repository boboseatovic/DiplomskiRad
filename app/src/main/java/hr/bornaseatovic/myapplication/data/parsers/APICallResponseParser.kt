package hr.bornaseatovic.myapplication.data.parsers

import hr.bornaseatovic.myapplication.data.model.presentation.*
import hr.bornaseatovic.myapplication.data.model.remote.PVCalculationsResponse


fun PVCalculationsResponse.toPVCalculationsPresentation() = PVCalculationsPresentation(
    monthly = Monthly(
        fixed = this.outputs.monthly.fixed.map {
            Fixed(
                eD = it.eD,
                eM = it.eM,
                hiD = it.hiD,
                hiM = it.hiM,
                month = it.month,
                sDM = it.sDM
            )
        }
    ),
    totals = Totals(
        fixed = FixedX(
            eD = this.outputs.totals.fixed.eD,
            eM = this.outputs.totals.fixed.eM,
            eY = this.outputs.totals.fixed.eY,
            hiD = this.outputs.totals.fixed.hiD,
            hiM = this.outputs.totals.fixed.hiM,
            hiY = this.outputs.totals.fixed.hiY,
            lAoi = this.outputs.totals.fixed.lAoi,
            lSpec = this.outputs.totals.fixed.lSpec,
            lTg = this.outputs.totals.fixed.lTg,
            lTotal = this.outputs.totals.fixed.lTotal,
            sDM = this.outputs.totals.fixed.sDM,
            sDY = this.outputs.totals.fixed.sDY
        )
    )
)