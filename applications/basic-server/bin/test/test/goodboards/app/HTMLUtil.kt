package test.goodboards.app

import org.jsoup.nodes.Element
object HTMLUtil {

    fun extractGameElements(body: Element) : List<Element> {
        return body.select("div.game")
    }


}