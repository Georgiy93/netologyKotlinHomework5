package ru.netology.homework51

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MainKtTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()

    }


    @Test
    fun add() {
        val like = Likes()

        val post = Post()
        val post3 = post.copy(
            ownerID = 32, data = System.currentTimeMillis(),
            text = "Перерыв 10 минут!", friendsOnly = true,
            likes = like.copy(count = like.count + 141, canPublish = true)
        )

      val array = WallService.addInArray(post3)
        assertEquals(1, array[0].id)
    }

    @Test
    fun updateTrue() {
        val like = Likes()

        val post = Post()
        val post3 = post.copy(
            ownerID = 32, data = System.currentTimeMillis(), text = "Перерыв 10 минут!", friendsOnly = true,
            likes = like.copy(count = like.count + 141, canPublish = true)
        )

        val updatePost = post.copy(
            1, ownerID = 32, data = System.currentTimeMillis(),
            isFavorite = true, replyPostID = 2, replyOwnerID = 32,
            canEdit = true,
            text = "Перерыв 20 минут!", friendsOnly = true, postType = "suggest",
            likes = like.copy(count = like.count + 151, canPublish = true)
        )
        WallService.addInArray(post3)
        val result = WallService.update(updatePost)
        assertTrue(result)
    }

    @Test
    fun updateFalse() {
        val like = Likes()

        val post = Post()

        val updatePost = post.copy(
            ownerID = 32, data = System.currentTimeMillis(),
            isFavorite = true, replyPostID = 2, replyOwnerID = 32,
            canEdit = true,
            text = "Перерыв 20 минут!", friendsOnly = true, postType = "suggest",
            likes = like.copy(count = like.count + 151, canPublish = true)
        )
        val result = WallService.update(updatePost)
        assertFalse(result)
    }
}