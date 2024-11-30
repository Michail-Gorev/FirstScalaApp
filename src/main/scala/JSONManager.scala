import org.json4s.*
import org.json4s.jackson.JsonMethods.*

class JsonManager {

  implicit val formats: Formats = org.json4s.DefaultFormats

  /**
   * Строит JSON из строки.
   *
   * @param jsonString Входная строка JSON.
   * @return JSON объект или None, если строка невалидна.
   */
  def buildJsonFromString(jsonString: String): Option[JValue] = {
    try {
      Some(parse(jsonString))
    } catch {
      case e: Exception =>
        println(s"Ошибка парсинга JSON: ${e.getMessage}")
        None
    }
  }


  /**
   * Строит строку JSON из файла.
   *
   * @param filePath Путь к файлу JSON.
   * @return Строка JSON или None, если файл не существует или невалиден.
   */
  def buildJsonStringFromFile(filePath: String): Option[String] = {
    import scala.io.Source
    try {
      val jsonString = Source.fromFile(filePath).mkString
      Some(jsonString)
    } catch {
      case e: Exception =>
        println(s"Ошибка чтения или парсинга файла: ${e.getMessage}")
        None
    }
  }


  /**
   * Проверяет наличие поля в JSON-файле.
   *
   * @param filePath Путь к файлу JSON.
   * @param fieldName Имя поля для проверки.
   * @return True, если поле существует, False в противном случае, или None при ошибке.
   */
  def checkFieldExists(filePath: String, fieldName: String): Option[Boolean] = {
    buildJsonStringFromFile(filePath).flatMap { jsonString =>
      buildJsonFromString(jsonString).map { json =>
        (json \ fieldName).toOption match {
          case Some(_) => true
          case None => false
        }
      }
    }
  }


  //Дополнительный метод для удобства работы со строкой JSON
  def checkFieldExistsFromString(jsonString: String, fieldName: String): Option[Boolean] = {
    buildJsonFromString(jsonString).map { json =>
      (json \ fieldName).toOption match {
        case Some(_) => true
        case None => false
      }
    }
  }
}

//
//object Main extends App {
//  val processor = new JsonManager()
//
//  //Пример использования
//  val jsonString = """{"name": "John Doe", "age": 30, "city": "New York"}"""
//  val jsonFromFileOption = processor.buildJsonStringFromFile("test.json") //test.json должен существовать
//  val jsonFromStringOption = processor.buildJsonFromString(jsonString)
//
//  jsonFromStringOption.foreach(json => println(compact(render(json))))
//  jsonFromFileOption.foreach(json => println(json)) //Выведет содержимое файла
//
//  println(processor.checkFieldExistsFromString(jsonString,"age")) //Some(true)
//  println(processor.checkFieldExistsFromString(jsonString,"country")) //Some(false)
//  jsonFromFileOption.flatMap(json => processor.checkFieldExistsFromString(json, "age")).foreach(println) //Выведет true или false в зависимости от наличия поля в файле
//
//
//}
