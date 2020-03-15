package dog.snow.androidrecruittest.data.network.repository

import dog.snow.androidrecruittest.data.database.dao.UserDao
import dog.snow.androidrecruittest.data.database.entity.UserEntity
import dog.snow.androidrecruittest.data.network.model.NetworkUser
import dog.snow.androidrecruittest.data.network.service.UserService
import dog.snow.androidrecruittest.domain.model.User
import dog.snow.androidrecruittest.domain.repository.IUserRepository
import javax.inject.Inject

class UserRepository
@Inject constructor(
    private val userService: UserService,
    private val userDao: UserDao
) : IUserRepository {

    override suspend fun getUser(id: Int): User {
        val user = userDao.getUser(id)

        return if (user.isEmpty()) {
            userService(id).also {
                userDao.insertUser(UserEntity(it.id, it.name, it.username, it.email, it.phone))
            }.mapToDomain()
        } else {
            user.map { User(it.id, it.name, it.userName, it.email, it.phone) }.first()
        }
    }

}

private fun NetworkUser.mapToDomain(): User {
    return User(id = id, name = name, userName = username, email = email, phone = phone)
}
