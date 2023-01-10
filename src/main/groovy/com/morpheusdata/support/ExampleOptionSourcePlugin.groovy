package com.morpheusdata.support

import com.morpheusdata.core.MorpheusContext
import com.morpheusdata.core.Plugin

class ExampleOptionSourcePlugin extends Plugin {

	@Override
	String getCode() {
		return 'example-option-source-plugin'
	}

	@Override
	void initialize() {
		this.setName('Example Option Source Plugin')
		ExampleOptionSourceProvider optionSourceProvider = new ExampleOptionSourceProvider(this, morpheus)
		pluginProviders.put(optionSourceProvider.code, optionSourceProvider)
	}

	@Override
	void onDestroy() {
	}

	MorpheusContext getMorpheusContext() {
		return morpheus
	}
}
