import java.io.{File, FileOutputStream, PrintWriter}
import scala.collection.mutable
import scala.io.Source
/**
  * Created by halleyfroeb on 10/18/16.
  * customer_id,date,credit_card,cvv,category
  * Furniture, Alcohol, Toiletries, Shoes, Food, Jewelry
  */
object FileIO {
  def main(args: Array[String]): Unit = {
    val purchases: mutable.MutableList[(String, String, String, String, String)] = mutable.MutableList[(String, String, String, String, String)]()

    Source
      .fromFile("purchases.csv")
      .getLines().toList
      .drop(1)
      .foreach(line => {
        val Array(customerId, date, creditCard, cvv, category) = line.split(",").map(_.trim)
        val purchase = (s"${customerId}", s"${date}", s"${creditCard}", s"${cvv}", s"${category}")
        purchases += purchase
      })

      println("Choose a Category: furniture, alcohol, toiletries, shoes, food, jewelry")
      val category = io.StdIn.readLine()
    
      val writer = new PrintWriter(new File("filtered_purchases.prn"))
      purchases.foreach(x => {
        if ({x._5}.equalsIgnoreCase(category)){
          println(s"Customer: ${x._1}, Date: ${x._2}")
          writer.write(s"Customer: ${x._1}, Date: ${x._2}\n")
        }
    })
    writer.close()
  }
}
