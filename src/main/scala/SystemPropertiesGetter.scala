
object SystemPropertiesGetter {
  def main(args: Array[String]): Unit = {
    getAllKeysOfSystemProperties(true)
  }

  /**
   *
   */
  private def getAllKeysOfSystemProperties(needToPrint: Boolean): Object = {
    val properties = System.getProperties
    val keys = properties.keys()
    val listOfKeys = List(keys)
    while (keys.hasMoreElements) {
      if(needToPrint) println(keys.nextElement())
    }
    keys
  }
}