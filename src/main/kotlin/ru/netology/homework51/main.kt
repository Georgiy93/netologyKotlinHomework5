package ru.netology.homework51

import ru.netology.homework51.WallService.createSpecificPost
import java.text.SimpleDateFormat

data class Post(
    val id: Int = 0,
    val ownerID: Int = 0,
    val data: Long = 0,
    val isFavorite: Boolean = false,
    val text: String,
    val replyOwnerID: Int = 0,
    val replyPostID: Int = 0,
    val canEdit: Boolean = false,
    val friendsOnly: Boolean = false,
    val postType: String = "",
    val likes: Likes = Likes()
)


data class Likes(

    val count: Int = 0,
    val userLike: Boolean = false,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

object WallService {
    private var posts = emptyArray<Post>()
    private var id = 1
    fun clear() {
        posts = emptyArray()
        id = 1
    }


    //добавление в массив 1я задача
    fun createSpecificPost(): Post = Post(

        id = 0,

        ownerID = 1,

        data = System.currentTimeMillis(),

        isFavorite = false,

        text = "Hello",

        replyOwnerID = 1,

        replyPostID = 1,

        canEdit = true,

        friendsOnly = false,

        postType = "post",

        likes = Likes()

    )

    fun getAll(): Array<Post> {

        return posts
    }


    //добавление в массив 2я задача
    fun add(post: Post): Post {

        posts += post.copy(id = id++)

        return posts.last()

    }

    fun update(post: Post): Boolean {
        posts.withIndex().forEach {

            when (it.value.id) {

                post.id -> {
                    val old = posts[it.index]
                    posts[it.index] = post.copy(ownerID = old.ownerID, data = old.data)

                    return true
                }


                else -> Unit

            }

        }
        return false
    }
}

fun main() {
    val like = Likes()
    val post = createSpecificPost()
    var post1 = post.copy(likes = like.copy(count = like.count + 122, canPublish = false))
    var post2 = post.copy(
        ownerID = 32, data = System.currentTimeMillis(), text = "Перерыв 10 минут!", friendsOnly = true,
        likes = like.copy(count = like.count + 141, canPublish = true)
    )
    val updatePost = post.copy(
        2, ownerID = 32, data = System.currentTimeMillis(),
        isFavorite = true, replyPostID = 2, replyOwnerID = 32,
        canEdit = true,
        text = "Перерыв 20 минут!", friendsOnly = true, postType = "suggest",
        likes = like.copy(count = like.count + 151, canPublish = true)
    )

    WallService.add(post1)
    WallService.add(post2)

    val array = WallService.getAll()
    WallService.update(updatePost)
    for (post in array) {
        var dataFormat = SimpleDateFormat("Дата поста: dd:MM:yy, время поста:  HH:mm:ss")
        var data = dataFormat.format(post.data)
        println(
            post.text + ", Индетификатор поста: " + post.id +
                    ", Индетификатор автора поста: " + post.ownerID +
                    ", $data " + "Количестов лайков: " + post.likes.count + "\n"
        )

    }
}