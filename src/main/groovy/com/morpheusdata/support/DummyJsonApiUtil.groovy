package com.morpheusdata.support
import groovy.json.JsonBuilder
import groovy.util.logging.Slf4j

/**
A class to represent a JSON REST API. This acts like an REST API purely for convenience purposes and to ensure the example can be widely used.
**/
@Slf4j
class DummyJsonApi {
  
  static final MATRIX_FILMS = [
    [id: 1, title: "The Matrix"],
    [id: 2, title: "The Matrix Reloaded"],
    [id: 3, title: "The Matrix Revolutions"]
  ]
  static final CHARACTERS = [
    [id: 1, name: "Neo", films: [1,2,3]],
    [id: 2, name: "Trinity", films: [1,2,3]],
    [id: 3, name: "Morpheus", films: [1,2,3]],
    [id: 4, name: "Agent Smith", films: [1,2,3]],
    [id: 5, name: "Tank", films: [1]],
    [id: 6, name: "Keymaker", films: [2]],
  ]
  static final SPECIAL_MOVES = [
    [id: 1, character: 1, name: "Whoah!", films: [1]],
    [id: 2, character: 1, name: "Backflip", films: [1,2,3]],
    [id: 3, character: 1, name: "Flying blind", films: [3]],
    [id: 4, character: 2, name: "Rooftop Jump", films: [1]],
    [id: 5, character: 2, name: "Be cool on a motorbike", films: [2]],
    [id: 6, character: 3, name: "Headbut!", films: [1,2,3]],
    [id: 7, character: 3, name: "Sweating", films: [1]],
    [id: 8, character: 4, name: "Sunglasses", films: [1,2,3]],
    [id: 9, character: 5, name: "Electrify!", films: [1]],
    [id: 10, character: 6, name: "Making keys..", films: [2]],
  ]
  
  static getMatrixFilms(query = [:]) {
    log.debug("DummyJsonApi getMatrixFilms: ${query}")
    return toJSON(MATRIX_FILMS)
  }
  
  static getMatrixCharacters(query = [:]) {
    log.debug("DummyJsonApi getMatrixCharacters: ${query}")
    def rtn = CHARACTERS
    if (query.filmId) {
      rtn = rtn?.findResults { it.films?.find{it?.toString()?.equals(query.filmId?.toString())}}
    }
    return toJSON(rtn)
  }
  
  static getMatrixSpecialMoves(query = [:]) {
    log.debug("DummyJsonApi getMatrixSpecialMoves: ${query}")
    def rtn = SPECIAL_MOVES
    if (query.filmId) {
      
    }
    if (query.characterId) {
      
    }
    return toJSON(rtn)
  }
  
  private static toJSON(obj) {
    return new JsonBuilder(obj)
  } 
}
