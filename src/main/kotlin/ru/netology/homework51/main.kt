package ru.netology.homework51

import java.text.SimpleDateFormat

data class Post(
    val id: Int,
    val ownerID: Int,
    val data: Long,
    val isFavorite: Boolean,
    val text: String,
    val replyOwnerID: Int,
    val replyPostID: Int,
    val canEdit: Boolean,
    val friendsOnly: Boolean,
    val postType: String,
    val likes: Likes
) {
    constructor() : this(
        0, 1,
        System.currentTimeMillis(),
        false,
        "Hello",
        1,
        1, true, false, "post",
        Likes()
    )
}

data class Likes(

    val count: Int,
    val userLike: Boolean,
    val canLike: Boolean,
    val canPublish: Boolean
) {
    constructor() : this(
        0,
        false, true,
        true
    )
}

object WallService {
    private var posts = emptyArray<Post>()
    private var id = 1
    fun clear() {
        posts = emptyArray()
       id=1
    }


    //добавление в массив 1я задача
    fun addInArray(post: Post): Array<Post> {

        posts += add(post)

        return posts
    }




    //добавление в массив 2я задача
    fun add(post: Post): Post {

        posts += post.copy(id = id)

        id++

        return posts.last()

    }

    fun update(post: Post): Boolean {
        var result=false
        posts.withIndex().forEach {

            when (it.value.id) {
                post.id -> {
                    posts[it.index] = post
                    result = true
                }

                else -> result = false
            }

        }
        return result
    }
}

fun main() {
    val like = Likes()
    val post = Post()
    val post1 = post.copy(likes = like.copy(count = like.count + 122, canPublish = false))
    val post2 = post.copy(
        ownerID = 32, data = System.currentTimeMillis(), text = "Перерыв 10 минут!", friendsOnly = true,
        likes = like.copy(count = like.count + 141, canPublish = true)
    )
    val updatePost = post.copy(
        3, ownerID = 32, data = System.currentTimeMillis(),
        isFavorite = true, replyPostID = 2, replyOwnerID = 32,
        canEdit = true,
        text = "Перерыв 20 минут!", friendsOnly = true, postType = "suggest",
        likes = like.copy(count = like.count + 151, canPublish = true)
    )

    WallService.addInArray(post1)
    val array = WallService.addInArray(post2)
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