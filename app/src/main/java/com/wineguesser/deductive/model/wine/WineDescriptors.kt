package com.wineguesser.deductive.model.wine

abstract class WineDescriptors(
    var clarity: Clarity? = null,
    var concentration: Concentration? = null,
    var rimVariation: Int = 0,
    var staining: Staining? = null,
    var gasEvidence: Int = 0,
    var intensity: Intensity? = null,
    var age: Age? = null,
    var oak: Oak? = null,
    var acidity: Acidity? = null,
    var alcohol: Alcohol? = null,
    var body: Body? = null,
    var texture: Texture? = null,
    var balanced: Int = 0,
    var finish: Finish? = null,
    var complexity: Complexity? = null,
    var fault: Fault? = null,
    var fruitCharacter: FruitCharacter? = null,
    var nonFruit: NonFruit? = null,
    var earth: Earth? = null,
    var minerality: Minerality? = null
)
