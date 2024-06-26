package AkkaDataStreams
import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source.actorRefWithAck
import akka.stream.scaladsl.{Flow, Sink, Source}


object AkkaStream extends App{
  implicit  val system = ActorSystem("drfg")
  implicit val materializer = ActorMaterializer()

  //1.
  val source: Source[Int, NotUsed] = Source(1 to 10)
  val flow = Flow[Int].map(x=>x+1)
  val sink = Sink.foreach[Int](println)
  val graph1 = source.to(sink)
//  graph1.run()
  //source.via(flow).to(sink).run()

  //2
  val simpleSource = Source(1 to 10)
  val simpleFlow = Flow[Int].map(_+1)
  val simpleFlow2 = Flow[Int].map(_*10)
  val simpleSink = Sink.foreach[Int](println)
  simpleSource.async
    .via(simpleFlow).async
    .via(simpleFlow2).async
    .to(simpleSink).async
    .run()



}
