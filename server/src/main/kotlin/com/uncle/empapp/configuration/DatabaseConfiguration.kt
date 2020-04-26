package com.uncle.empapp.configuration

import com.uncle.empapp.models.User
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.springframework.beans.factory.FactoryBean
import org.springframework.stereotype.Component

@Component
class DatabaseConfiguration : FactoryBean<Session> {

    private var config : Configuration = Configuration()
    private lateinit var sessionFactory : SessionFactory;

    init {
        config.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/application");
        config.setProperty("hibernate.connection.username", "postgres");
        config.setProperty("hibernate.connection.password", "");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        config.addAnnotatedClass(User::class.java)
        sessionFactory = config.buildSessionFactory()
    }

    override fun getObject(): Session? {
        return sessionFactory.openSession()
    }

    override fun getObjectType(): Class<*>? {
        return Session::class.java
    }

    override fun isSingleton() : Boolean {
        return false
    }
}