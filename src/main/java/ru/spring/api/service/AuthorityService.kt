package ru.spring.api.service

import ru.spring.api.bean.Authority

interface AuthorityService {
    fun findAll(): List<Authority>
}