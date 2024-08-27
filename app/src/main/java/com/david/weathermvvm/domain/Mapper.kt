package com.david.weathermvvm.domain

interface Mapper<T, E> {
    fun map(from: T): E
}