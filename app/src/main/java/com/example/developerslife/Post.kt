package com.example.developerslife

class Post{
    var id: Int = 0
    var description: String
    var gifURL: String

    constructor(id: Int, description: String, gifURL: String){
        this.id= id
        this.description = description
        this.gifURL=gifURL
    }
}