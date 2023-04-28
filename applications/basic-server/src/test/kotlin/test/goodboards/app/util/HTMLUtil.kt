package test.goodboards.app.util

import org.jsoup.nodes.Element
object HTMLUtil {

    fun extractGameElements(body: Element) : List<Element> {
        return body.select("div.game")
    }

    fun extractNewsElements(body: Element) : List<Element> {
        return body.select("div[id=table-news]")
    }


}