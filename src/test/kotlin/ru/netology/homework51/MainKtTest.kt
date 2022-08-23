package ru.netology.homework51

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import ru.netology.homework51.WallService.createSpecificPost

class MainKtTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()

    }


    @Test
    fun add() {
        val like = Likes()

        val post = createSpecificPost()
        val post3 = post.copy(
            ownerID = 32, data = System.currentTimeMillis(),
            text = "Перерыв 10 минут!", friendsOnly = true,
            likes = like.copy(count = like.count + 141, canPublish = true)
        )
        WallService.add(post3)
        val array = WallService.getAll()
        assertEquals(1, array[0].id)
    }

    @Test
    fun updateTrue() {
        val like = Likes()

        val post = createSpecificPost()
        val post3 = post.copy(
            text = "Перерыв 10 минут!", friendsOnly = true,
            likes = like.copy(count = like.count + 141, canPublish = true)
        )

        val updatePost = post.copy(
            1,
            isFavorite = true, replyPostID = 2, replyOwnerID = 32,
            canEdit = true,
            text = "Перерыв 20 минут!", friendsOnly = true, postType = "suggest",
            likes = like.copy(count = like.count + 151, canPublish = true)
        )
        WallService.add(post3)
        val result = WallService.update(updatePost)
        assertTrue(result)
    }

    @Test
    fun updateFalse() {
        val like = Likes()

        val post = createSpecificPost()
        val post3 = post.copy(
            text = "Перерыв 10 минут!", friendsOnly = true,
            likes = like.copy(count = like.count + 141, canPublish = true)
        )
        WallService.add(post3)
        val updatePost = post.copy(id=2,
            isFavorite = true, replyPostID = 2, replyOwnerID = 32,
            canEdit = true,
            text = "Перерыв 20 минут!", friendsOnly = true, postType = "suggest",
            likes = like.copy(count = like.count + 151, canPublish = true)
        )

        val result = WallService.update(updatePost)
        assertFalse(result)
    }
}