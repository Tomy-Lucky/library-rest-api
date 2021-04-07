package com.example.assignment.Tamir.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ElementNotFoundException(element: String) : RuntimeException("No such element: $element")
