/**
 * Created by IntelliJ IDEA.
 * User: Randy
 * Date: 9/4/12
 * Time: 7:57 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList
import org.specs2.mutable
import org.specs2.mutable._

class FuzzyDeduperTest extends Specification {

  "CSV Reader" should {
    "open csv" in {
      (new CSVReader("advertisers.csv")).getNames.size must be_>(0)
    }
  }

  "FuzzyDeduper" should {
    "exist" in {
      "hi" must not be equalTo(null)
    }

    "make phonemes" in {
//      val testList = List("onetwothree")
      val res = (new FuzzyDeduper(null)).makePhonemes("onetwothree", 3)
      println(res)
      res.get(res.size-1) must be equalTo("ree")

      val res2 = (new FuzzyDeduper(null)).makePhonemes("onetwothree", 53)
      res2.size must be equalTo(0)
    }

    "make all phonemes" in {
      val res = (new FuzzyDeduper(null)).makeAllPhonemes("onetwothree", 3)
      println(res)
      res.get(0) must be equalTo("one")
      res.get(res.size-1) must be equalTo("onetwothree")
      res.size must be equalTo(45)
    }

    "aggregate phonemes" in {
      val l = new ArrayList

      val res = (new FuzzyDeduper(null)).makeAllPhonemes("onetwothree", 3)

    }

  }




}