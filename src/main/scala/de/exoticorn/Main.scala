import javax.imageio.ImageIO
import java.io.File
import java.awt.image.{ IndexColorModel, SinglePixelPackedSampleModel, DataBuffer, DataBufferByte, Raster, BufferedImage }
import java.awt.Point

object Main extends App {
  val img = ImageIO.read(new File("test.jpg"))

  val paletteData = for (i <- 0 to 255; j <- 0 to 3) yield i.toByte
  val colorModel = new IndexColorModel(8, 256, paletteData.toArray, 0, true)
  val sampleModel = new SinglePixelPackedSampleModel(DataBuffer.TYPE_BYTE, img.getWidth(), img.getHeight(), Array(255))
  val outputRaster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, img.getWidth(), img.getHeight(), 1, new Point(0, 0))
  val dataBuffer = outputRaster.getDataBuffer().asInstanceOf[DataBufferByte]
  val pixels = img.getData().getPixels(0, 0, img.getWidth(), img.getHeight(), null: Array[Int])
  for {
    x <- 0 until img.getWidth()
    y <- 0 until img.getHeight()
  } {
    dataBuffer.getData()(x + y * img.getWidth()) = pixels((x + y * img.getWidth()) * 3).toByte
  }

  val outputImage = new BufferedImage(colorModel, outputRaster, false, null)

  ImageIO.write(outputImage, "png", new File("out.png"))
}

