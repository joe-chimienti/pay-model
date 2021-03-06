package com.mathbot.pay.lightning

import java.time.Instant

import com.mathbot.pay.json.EpochSecondInstantFormatter
import com.mathbot.pay.lightning.PayStatus.PayStatus
import play.api.libs.json.{Json, OFormat}
// https://github.com/ElementsProject/lightning/blob/master/doc/lightning-listpays.7.md
// For old payments (pre-0.7) we didn’t save the bolt11 string, so in its place are three other fields: payment_hash,destination,amount_msat
case class ListPay(
    bolt11: Option[Bolt11],
    status: PayStatus,
    amount_sent_msat: String,
    created_at: Option[Instant] = None,
    preimage: Option[String] = None,
    payment_hash: Option[String] = None,
    label: Option[String] = None
)

object ListPay extends EpochSecondInstantFormatter {
  lazy implicit val formatListPay: OFormat[ListPay] = Json.format[ListPay]
  def apply(bolt11: Bolt11, payment: Payment): ListPay = {
    ListPay(
      bolt11 = Some(bolt11),
      status = payment.status,
      amount_sent_msat = payment.amount_sent_msat,
      created_at = Some(payment.created_at),
      preimage = Some(payment.payment_preimage),
      payment_hash = Some(payment.payment_hash),
      label = payment.label
    )
  }
}
