/**
 * Created by IntelliJ IDEA.
 * User: Randy
 * Date: 9/4/12
 * Time: 7:57 PM
 * To change this template use File | Settings | File Templates.
 */

import org.specs2.mutable
import org.specs2.mutable._

class FuzzyDeduperTest extends Specification {

  "FuzzyDeduper" should {
    "exist" in {
      "hi" must not be equalTo(null)
    }
  }

  "CSV Reader" should {
    "open csv" in {
      (new CSVReader("advertisers.csv")).getNames.size must be_>(0)
    }
  }


}