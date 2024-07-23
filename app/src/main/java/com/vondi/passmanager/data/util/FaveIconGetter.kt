package com.vondi.passmanager.data.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.net.URL

suspend fun getFaviconUrl(pageUrl: String): String = withContext(Dispatchers.IO) {
    return@withContext try {
        val fullPageUrl = if (pageUrl.startsWith("http://") || pageUrl.startsWith("https://")) {
            pageUrl
        } else {
            "https://$pageUrl"
        }

        val document = Jsoup.connect(fullPageUrl).get()
        val icons = document.select("link[rel~=(?i)\\bicon\\b]")

        val bestIcon = icons
            .mapNotNull { iconLink ->
                val href = iconLink.attr("href")
                val sizes = iconLink.attr("sizes")
                val size = sizes.split("x").firstOrNull()?.toIntOrNull() ?: 0
                Triple(href, size, iconLink)
            }
            .maxByOrNull { it.second }

        val bestIconHref = bestIcon?.first ?: "/favicon.ico"
        URL(URL(fullPageUrl), bestIconHref).toString()
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}