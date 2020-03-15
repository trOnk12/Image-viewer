package dog.snow.androidrecruittest.domain.repository

import dog.snow.androidrecruittest.domain.model.User

interface IUserRepository {
    suspend fun getUser(id: Int): User
}