package module1.homework


import scala.util.Random

class BallsExperiment {

  private val box: Seq[Int] = List(0,1,0,1,0,1)
  def isFirstBlackSecondWhite: Boolean = {
    val idxOfFirstBall = Random.nextInt(box.size)
    val isFirstBlack = box(idxOfFirstBall) == 0
    val reducedBox = box.patch(idxOfFirstBall, Nil, 1)
    val idxOfSecondBall = Random.nextInt(reducedBox.size)
    val isSecondWhite = reducedBox(idxOfSecondBall) == 1
    isFirstBlack && isSecondWhite
  }
}

object BallsTest {
  def main(args: Array[String]): Unit = {
    val count = 10000
    val listOfExperiments: List[BallsExperiment] = List.fill(count)(new BallsExperiment)
    val countOfExperiments =  listOfExperiments.map(_.isFirstBlackSecondWhite)
    val countOfPositiveExperiments: Float = countOfExperiments.count(_ == true)
    println(countOfPositiveExperiments / count)
  }
}