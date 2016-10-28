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
    val purchases = mutable.MutableList[(String, String, String, String, String)]()

    Source
      .fromFile("purchases.csv")
      .getLines().toList
      .drop(1)
      .foreach(line => {
        val purchase: (String, String, String, String, String) = {
          val Array(customerId, date, creditCard, cvv, category) = line.split(",").map(_.trim)
          (customerId, date, creditCard, cvv, category)
        }
        purchases += purchase
      })

    println("Choose a Category: furniture, alcohol, toiletries, shoes, food, jewelry")
    val category = io.StdIn.readLine()

    val writer = new PrintWriter(new File("filtered_purchases.prn"))
    purchases
      .filter(x => x._5.equalsIgnoreCase(category))
      .foreach(x => {
        println(s"Customer: ${x._1}, Date: ${x._2}")
        writer.write(s"Customer: ${x._1}, Date: ${x._2}\n")
      })
    writer.close()
  }
}