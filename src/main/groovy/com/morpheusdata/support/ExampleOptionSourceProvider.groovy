package com.morpheusdata.support

import com.morpheusdata.core.MorpheusContext
import com.morpheusdata.core.Plugin
import com.morpheusdata.model.*
import groovy.util.logging.Slf4j
import com.morpheusdata.core.OptionSourceProvider
import groovy.json.JsonSlurper

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
		return new ArrayList<String>(['matrixFilms', 'matrixCharacters', 'matrixSpecialMoves'])
	}
	
	def matrixFilms(args) {
		log.debug("ExampleOptionSourceProvider matrixFilms: ${args}")
		return this.parseJSON(DummyJsonAPI.getMatrixFilms())
	}
	
	def matrixCharacters(args) {
		log.debug("ExampleOptionSourceProvider matrixCharacters: ${args}")
		return this.parseJSON(DummyJsonAPI.getMatrixCharacters())
	}
	
	def matrixSpecialMoves(args) {
		log.debug("ExampleOptionSourceProvider matrixSpecialMoves: ${args}")
		return this.parseJSON(DummyJsonAPI.getMatrixCharacters())
	}
	
	private parseJSON(json) {
		return new JsonSlurper().parseText(json)
	}
	
}
