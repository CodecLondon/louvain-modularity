import org.apache.spark.sql.SparkSession
import org.specs2
import org.specs2.mock.Mockito

class LouvainTest extends specs2.mutable.Spec with Mockito {

  "Louvain" should {
    "decompress" in {
      val sparkSession = SparkSession.builder.master("local").appName("Test").getOrCreate()

      val level1 = Seq(
        (1L, 1L), (2L, 1L), (3L, 1L), (4L, 1L),
        (5L, 5L), (6L, 5L), (7L, 5L),
        (8L, 8L), (9L, 8L),
        (10L, 10L)
      )

      val level2 = Seq(
        (1L, 1L), (5L, 1L),
        (8L, 8L),
        (10L, 10L)
      )

      val level3 = Seq(
        (1L, 1L), (8L, 1L),
        (10L, 10L)
      )

      val louvain = new Louvain(sparkSession.sparkContext)

      val rddList = Seq(level1, level2, level3).map(l => sparkSession.sparkContext.parallelize(l))
      val finalResult = rddList.reverse.reduce((c, o) => louvain.revert(o, c)).collect().toSet

      val expected = Set(
        (1L, 1L), (2L, 1L), (3L, 1L), (4L, 1L), (8L, 1L), (9L, 1L), (5L, 1L), (6L, 1L), (7L, 1L),
        (10L, 10L)
      )

      finalResult ==== expected
    }
  }

}
