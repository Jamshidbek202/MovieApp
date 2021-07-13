package com.jamshidbek.movieapp.Model

import java.io.Serializable

class MovieModel : Serializable{

    var title = ""
    var date = ""
    var actors = ""
    var desc = ""

    constructor(title: String, date: String, actors: String, desc: String) {
        this.title = title
        this.date = date
        this.actors = actors
        this.desc = desc
    }
}