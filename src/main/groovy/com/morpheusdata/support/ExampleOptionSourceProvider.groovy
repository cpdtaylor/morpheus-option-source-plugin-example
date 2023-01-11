package com.morpheusdata.support

import com.morpheusdata.core.MorpheusContext
import com.morpheusdata.core.Plugin
import com.morpheusdata.model.*
import groovy.util.logging.Slf4j
import com.morpheusdata.core.OptionSourceProvider
import groovy.json.JsonSlurper
import com.morpheusdata.support.util.DummyJsonApi

@Slf4j
class ExampleOptionSourceProvider implements OptionSourceProvider {

	Plugin plugin
	MorpheusContext morpheusContext

	ExampleOptionSourceProvider(Plugin plugin, MorpheusContext context) {
		this.plugin = plugin
		this.morpheusContext = context
	}

	@Override
	MorpheusContext getMorpheus() {
		return this.morpheusContext
	}

	@Override
	Plugin getPlugin() {
		return this.plugin
	}

	@Override
	String getCode() {
		return 'exmaple-option-source-provider'
	}

	@Override
	String getName() {
		return 'Example Option Source Provider'
	}

	@Override
	List<String> getMethodNames() {
		return new ArrayList<String>(['matrixFilms', 'matrixCharacters', 'matrixSpecialMoves',"nonDependentMatrixCharacters","nonDependentMatrixSpecialMoves"])
	}
	
	def matrixFilms(args) {
		log.debug("ExampleOptionSourceProvider matrixFilms: ${args}")
		return this.parseJSON(DummyJsonApi.getMatrixFilms())
	}
	
	def matrixCharacters(args) {
		log.debug("ExampleOptionSourceProvider matrixCharacters: ${args}")
		if(args?."config.matrixFilm") {
			return this.parseJSON(DummyJsonApi.getMatrixCharacters([filmId: args."config.matrixFilm"]))
		} else {
			return [] //don't waste a "http REST API" call unless user has selected a film
		}
	}
	
	def matrixSpecialMoves(args) {
		log.debug("ExampleOptionSourceProvider matrixSpecialMoves: ${args}")
		if(args?."config.matrixFilm" && args?."config.matrixCharacter") {
			return this.parseJSON(DummyJsonApi.getMatrixSpecialMoves([filmId: args."config.matrixFilm", characterId: args?."config.matrixCharacter"]))
		} else {
			return [] //don't waste a "http REST API" call unless user has selected a film and character
		}
	}
	
	def nonDependentMatrixCharacters(args) {
		return this.parseJSON(DummyJsonApi.getMatrixCharacters())
	}
	
	def nonDependentMatrixSpecialMoves(args) {
		return this.parseJSON(DummyJsonApi.getMatrixSpecialMoves())
	}
	
	private parseJSON(json) {
		return new JsonSlurper().parseText(json)
	}
	
}
