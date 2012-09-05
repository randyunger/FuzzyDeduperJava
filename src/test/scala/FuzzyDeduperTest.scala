/**
 * Created by IntelliJ IDEA.
 * User: Randy
 * Date: 9/4/12
 * Time: 7:57 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.{HashSet, ArrayList}
import org.specs2.mutable
import org.specs2.mutable._

class FuzzyDeduperTest extends Specification {

  "CSV Reader" should {
    "open csv" in {
      (new CSVReader("advertisers.csv")).getNames.size must be_>(0)
    }
  }

  val sampleList = new HashSet[String]()
  sampleList.add("one")
  sampleList.add("onetwo")
  sampleList.add("network")
  sampleList.add("new york")


  "FuzzyDeduper" should {
    "exist" in {
      "hi" must not be equalTo(null)
    }

    "make phonemes" in {
//      val testList = List("onetwothree")
      val res = (new FuzzyDeduper(sampleList, 1)).makePhonemes("onetwothree", 3)
//      println(res)
      res.get(res.size-1) must be equalTo("ree")

      val res2 = (new FuzzyDeduper(sampleList, 1)).makePhonemes("onetwothree", 53)
      res2.size must be equalTo(0)
    }

    "make all phonemes" in {
      val res = (new FuzzyDeduper(sampleList, 1)).makeAllPhonemes("onetwothree", 3)
//      println(res)
      res.get(0) must be equalTo("one")
      res.get(res.size-1) must be equalTo("onetwothree")
      res.size must be equalTo(45)
    }

    "aggregate phonemes" in {
      val res = (new FuzzyDeduper(sampleList, 2)).dumpPhonemes
      println(res)
      res.size must be equalTo(15)
    }

  }




}