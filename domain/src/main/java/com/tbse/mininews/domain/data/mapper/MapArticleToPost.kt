package com.tbse.mininews.domain.data.mapper

import com.tbse.mininews.domain.model.*
import com.tbse.mininews.service.model.Article
import com.tbse.mininews.domain.model.Metadata as Metadata1

/**
 * Created by toddsmith on 2/16/22.
 */
class MapArticleToPost {
    operator fun invoke(input: Article, image: Int): Post {
        return Post(
            id = input.title,
            title = input.title,
            subtitle = input.description,
            url = input.url,
            publication = Publication(
                name = input.source.name,
                logoUrl = ""
            ),
            metadata = Metadata1(
                author = PostAuthor(input.author),
                date = input.publishedAt,
                readTimeMinutes = 5
            ),
            paragraphs = listOf(
                Paragraph(
                    type = ParagraphType.Title,
                    text = input.title
                ),
                Paragraph(
                    type = ParagraphType.Subhead,
                    text = input.description
                ),
                Paragraph(
                    type = ParagraphType.Text,
                    text = input.content
                ),
            ),
            imageId = image,
            imageThumbId = image
        )
    }
}