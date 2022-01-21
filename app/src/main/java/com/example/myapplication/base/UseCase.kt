package com.example.myapplication.base

interface UseCase<in Param, out Type> {
	fun execute(param: Param): Type
}
