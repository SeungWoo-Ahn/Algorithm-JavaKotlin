package programmers.Practice.Level3

class MatchingScore {
    private data class WebPage(
        val url: String,
        val basicScore: Int,
        val links: List<String>,
    ) {
        var linkScore = 0.0

        fun increaseLinkScore(webPage: WebPage) {
            if (webPage.links.isNotEmpty()) {
                linkScore += webPage.basicScore.toDouble() / webPage.links.size
            }
        }

        fun getMatchingScore(): Double {
            return basicScore + linkScore
        }
    }

    private fun parse(word: String, page: String): WebPage {
        val target = word.lowercase()
        val tagMap = mapOf("meta" to "content", "a" to "href")
        var st = 0
        var en: Int
        var inTag = false
        var tag = ""
        var url = ""
        var basicScore = 0
        val links = mutableListOf<String>()
        while (st < page.length) {
            if (page[st] == '<') {
                inTag = true
                en = getEn(++st, page)
                tag = page.substring(st, en)
                st = en
                continue
            }
            if (page[st] == '>') {
                inTag = false
                st++
                continue
            }
            if (page[st].isAlpha()) {
                en = getEn(st, page)
                val s = page.substring(st, en)
                when {
                    inTag && tagMap[tag] == s -> {
                        st = en + 2
                        en = st + 1
                        while (page[en] != '\"') {
                            en++
                        }
                        val value = page.substring(st, en)
                        when (s) {
                            "content" -> url = value
                            "href" -> links += value
                        }
                    }
                    inTag.not() && s.lowercase() == target -> basicScore++
                }
                st = en
                continue
            }
            st++
        }
        return WebPage(url, basicScore, links)
    }

    private fun getEn(st: Int, page: String): Int {
        var en = st + 1
        while (en < page.length && page[en].isAlpha()) {
            en++
        }
        return en
    }

    private fun Char.isAlpha(): Boolean {
        return code in 'a'.code..'z'.code || code in 'A'.code..'Z'.code
    }

    fun solution(word: String, pages: Array<String>): Int {
        val urlMap = mutableMapOf<String, Int>()
        val webPages = Array(pages.size) { idx ->
            val webPage = parse(word, pages[idx])
            urlMap[webPage.url] = idx
            webPage
        }
        for (webPage in webPages) {
            for (url in webPage.links) {
                urlMap[url]?.let {
                    webPages[it].increaseLinkScore(webPage)
                }
            }
        }
        val maxScore = webPages.maxOf { it.getMatchingScore() }
        return webPages.indexOfFirst { it.getMatchingScore() == maxScore }
    }
}

fun main() {
    val word = "blind"
    val pages = arrayOf("<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>")
    val answer = MatchingScore().solution(word, pages)
    print(answer)
}