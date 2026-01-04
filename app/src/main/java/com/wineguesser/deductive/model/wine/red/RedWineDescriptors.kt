package com.wineguesser.deductive.model.wine.red

import com.wineguesser.deductive.model.wine.WineDescriptors

class RedWineDescriptors : WineDescriptors() {
    var color: Color? = null
    var secondaryColor: SecondaryColor? = null
    var tannin: Tannin? = null
}
