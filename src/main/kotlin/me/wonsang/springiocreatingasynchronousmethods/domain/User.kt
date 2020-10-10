package me.wonsang.springiocreatingasynchronousmethods.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

// -----------------------------------------------------------------------------------------------------
// Spring uses the Jackson JSON library to convert GitHub’s JSON response into a User object.
// The @JsonIgnoreProperties annotation tells Spring to ignore any attributes not listed in the class.
// This makes it easy to make REST calls and produce domain objects.
// -----------------------------------------------------------------------------------------------------
@JsonIgnoreProperties(ignoreUnknown=true)
data class User (val name: String,
                 val blog: String)